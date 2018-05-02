package db.core;

import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectHelper {

    private final static HashMap<String, Class<Model>> clCache = new HashMap<>();

    public static Model create(String clName, Map map) {

        Class<Model> cl = clCache.getOrDefault(clName, null);
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

        Map<String, String> n2nMap = new HashMap<>();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            MongoObjectId objectId = field.getAnnotation(MongoObjectId.class);
            if (objectId != null) {
                n2nMap.put(objectId.name(), field.getName());
                continue;
            }
            MongoSimple simple = field.getAnnotation(MongoSimple.class);
            if (simple != null) {
                n2nMap.put(simple.name(), field.getName());
                continue;
            }
            MongoObject object = field.getAnnotation(MongoObject.class);
            if (object != null) {
                n2nMap.put(object.name(), field.getName());
                continue;
            }
            MongoObjects objects = field.getAnnotation(MongoObjects.class);
            if (objects != null) {
                n2nMap.put(objects.name(), field.getName());
                continue;
            }
        }

        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("_id"))
                key = "id";
            String paramName = StringHelper.capitalize(key);
            Object arg;
            if (value instanceof Document) {
                StringBuffer buffer = new StringBuffer();
                String paramClName = buffer.append(clName).append("_").append(paramName).toString();
                arg = create(paramClName, (Document) value);
            } else if (value instanceof ArrayList) {
                ArrayList list = (ArrayList) value;
                if (list.size() == 0 || list.get(0).getClass() != Document.class)
                    arg = value;
                else {
                    ArrayList<Model> items = new ArrayList<>();
                    StringBuffer buffer = new StringBuffer();
                    String paramClName = buffer.append(clName).append("_").append(paramName).append("_Item").toString();
                    list.forEach(doc -> {
                        Model childModel = create(paramClName, (Document) doc);
                        items.add(childModel);
                    });
                    arg = items;
                }
            } else
                arg = value;

            invokeSetMethod(cl, model, paramName, arg);
        }

        return model;
    }

    private static void invokeSetMethod(Class<Model> cl, Model model, String paramName, Object arg) {
        String methodName = "set".concat(paramName);
        try {
            Method method = cl.getMethod(methodName, arg.getClass());
            method.invoke(model, arg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
