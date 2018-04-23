package db.core;

import org.bson.Document;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class ReflectHelper {

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
