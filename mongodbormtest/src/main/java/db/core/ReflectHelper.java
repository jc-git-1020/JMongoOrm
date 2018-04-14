package db.core;

import org.bson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ReflectHelper {

    public static Model create(String className, Map map) {

        Class<Model> cl = null;
        Model model = null;
        try {
            cl = (Class<Model>) Class.forName(className);
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
            if(value instanceof Double){
                invokeSetMethod(cl,model,paramName,((BsonDouble) value).getValue());
                continue;
            }else if(value instanceof String){
                invokeSetMethod(cl,model,paramName,value.toString());
                continue;
            }else if(value instanceof Document){
                String paramClName = className.concat("_").concat(paramName);
                Model childModel = create(paramClName,(Document)value);
                invokeSetMethod(cl,model,paramName,childModel);
                continue;
            }else if(value instanceof BsonArray){
                continue;
            }else if(value instanceof BsonBinary){
                continue;
            }else if(value instanceof ObjectId){
                invokeSetMethod(cl,model,paramName,((ObjectId) value));
                continue;
            }else if(value instanceof Boolean){
                continue;
            }else if(value instanceof BsonDateTime){
                continue;
            }else if(value instanceof BsonNull){
                continue;
            }else if(value instanceof BsonRegularExpression){
                continue;
            }else if(value instanceof BsonJavaScript){
                continue;
            }else if(value instanceof BsonJavaScriptWithScope){
                continue;
            }else if(value instanceof Integer){
                invokeSetMethod(cl,model,paramName,((Integer) value));
                continue;
            }
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
