package db.core;

import com.mongodb.DBRef;
import org.bson.*;
import org.bson.types.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

//TODO cl 和 method 做缓存，使用配置项指定是否使用缓存
public class ReflectHelper {

    public static Model create(String clName, Map map) {

        Class<Model> cl = null;
        Model model = null;
        try {
            cl = (Class<Model>) Class.forName(clName);
            model = (Model) cl.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(key.equals("_id") )
                key = "id";
            String paramName = StringHelper.capitalize(key);
            Object arg = null;
            if(value instanceof Document){
                String paramClName = new StringBuffer().append(clName).append("_").append(paramName).toString();
                arg = create(paramClName,(Document)value);
            }else if(value instanceof ArrayList){
                ArrayList list = (ArrayList) value;
                if(list.size() == 0 || list.get(0).getClass() != Document.class)
                    arg = value;
                else{
                    ArrayList<Model> items = new ArrayList();
                    String paramClName = new StringBuffer().append(clName).append("_").append(paramName).append("_Item").toString();
                    list.forEach(doc -> {
                        Model childModel = create(paramClName,(Document)doc);
                        items.add(childModel);
                    });
                    arg = items;
                }
            }else
                arg = value;

            invokeSetMethod(cl,model,paramName,arg);
        }

        return model;
    }

    private static <T extends Model> void invokeSetMethod(Class<T> cl,Model model, String paramName,Object arg){
        String methodName = "set".concat(paramName);
        try {
            Method method = cl.getMethod(methodName, arg.getClass());
            method.invoke(model,arg);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
