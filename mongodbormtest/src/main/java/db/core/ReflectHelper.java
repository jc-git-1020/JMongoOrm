package db.core;

import org.bson.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ReflectHelper {

    public static Model create(String className, Map map) {

        String clFulName = Config.MODEL_PACKAGE_PATH.concat(".").concat(className);
        Class<Model> cl = null;
        try {
            cl = (Class<Model>) Class.forName(clFulName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(key == "_id")
                key = "id";
            String paramName = StringHelper.capitalize(key);
            if(value instanceof BsonDouble){
                invokeSetMethod(cl,paramName,((BsonDouble) value).getValue());
            }else if(value instanceof BsonString){
                invokeSetMethod(cl,paramName,((BsonString)value).getValue());
            }else if(value instanceof BsonDocument){
                String paramClName = className.concat("_").concat(paramName);
                String paramClFulName = Config.MODEL_PACKAGE_PATH.concat(".").concat(paramClName);
                Model childModel = create(paramClFulName,(BsonDocument)value);
                invokeSetMethod(cl,paramName,childModel);
            }else if(value instanceof BsonArray){

            }else if(value instanceof BsonBinary){

            }else if(value instanceof BsonObjectId){
                invokeSetMethod(cl,paramName,((BsonObjectId) value).getValue());
            }else if(value instanceof BsonBoolean){

            }else if(value instanceof BsonDateTime){

            }else if(value instanceof BsonNull){

            }else if(value instanceof BsonRegularExpression){

            }else if(value instanceof BsonJavaScript){

            }else if(value instanceof BsonJavaScriptWithScope){

            }else if(value instanceof BsonInt32){
                invokeSetMethod(cl,paramName,((BsonInt32) value).getValue());
            }
        }

        Model model = null;
        try {
            model = (Model) cl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return model;
    }

    private static <T extends Model> void invokeSetMethod(Class<T> cl,String paramName,Object... args){
        String methodName = "set".concat(paramName);
        try {
            Method setName = cl.getMethod("setName", String.class);
            setName.invoke(cl, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
