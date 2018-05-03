package db.core;

import org.bson.Document;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectHelper {

    private final static HashMap<String, Class<Model>> clCache = new HashMap<>();
    private final static HashMap<String, Map<String, Field>> fieldCache = new HashMap<>();

    public static Model create(String clName, Map map) {

        Class<Model> cl = clCache.getOrDefault(clName, null);

        Model model = getNewInstance(clName, cl);

        Map<String, Field> n2fMap = getName2FieldMap(cl);

        setModelParam(map, n2fMap, model);

        return model;
    }

    private static void setModelParam(Map map, Map<String, Field> n2fMap, Model model) {
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!n2fMap.containsKey(key)) continue;
            Field field = n2fMap.get(key);

            Object arg = null;
            if (value instanceof Document) {
                arg = create(field.getName(), (Document) value);
            } else if (value instanceof ArrayList) {
                ArrayList list = (ArrayList) value;
                if (list.size() == 0 || list.get(0).getClass() != Document.class) arg = value;
                else {
                    ArrayList<Model> items = new ArrayList<>();
                    Type type = field.getGenericType();
                    if (type instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) type;
                        Class genericClazz = (Class) pt.getActualTypeArguments()[0];//获取泛型参数类型
                        list.forEach(doc -> {
                            Model childModel = create(genericClazz.getName(), (Document) doc);
                            items.add(childModel);
                        });
                        arg = items;
                    }
                }
            } else arg = value;

            try {
                field.set(model, arg);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Model getNewInstance(String clName, Class<Model> cl) {
        Model model = null;
        try {
            if (cl == null) {
                cl = (Class<Model>) Class.forName(clName);
                clCache.put(clName, cl);
            }
            model = cl.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return model;
    }

    /*
     * 获取字段名映射
     * */
    private static Map<String, Field> getName2FieldMap(Class<Model> cl) {
        if (fieldCache.containsKey(cl.getName())) return fieldCache.get(cl.getName());
        Map<String, Field> n2fMap = new HashMap<>();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            MongoObjectId objectId = field.getAnnotation(MongoObjectId.class);
            if (objectId != null) {
                n2fMap.put(objectId.name(), field);
                continue;
            }
            MongoSimple simple = field.getAnnotation(MongoSimple.class);
            if (simple != null) {
                n2fMap.put(simple.name(), field);
                continue;
            }
            MongoObject object = field.getAnnotation(MongoObject.class);
            if (object != null) {
                n2fMap.put(object.name(), field);
                continue;
            }
            MongoObjects objects = field.getAnnotation(MongoObjects.class);
            if (objects != null) {
                n2fMap.put(objects.name(), field);
            }
        }
        fieldCache.put(cl.getName(), n2fMap);
        return n2fMap;
    }

}
