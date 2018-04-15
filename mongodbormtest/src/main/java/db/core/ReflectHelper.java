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

        //类型转换
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(key.equals("_id") )
                key = "id";
            String paramName = StringHelper.capitalize(key);
            if(value instanceof Double){
                invokeSetMethod(cl,model,paramName,((Double) value));
            }else if(value instanceof String){
                invokeSetMethod(cl,model,paramName,value.toString());
            }else if(value instanceof Document){
                //todo
//                String paramClName = className.concat("_").concat(paramName);
//                Model childModel = create(paramClName,(Document)value);
//                invokeSetMethod(cl,model,paramName,childModel);
            }else if(value instanceof ArrayList){
                ArrayList list = (ArrayList) value;
                if(list.size() == 0 || list.get(0).getClass() != Document.class)
                    invokeSetMethod(cl,model,paramName,((ArrayList) value));
                else{
                    list.forEach(new Consumer<Document>() {
                        @Override
                        public void accept(Document o) {

                        }
                    });
                }
            }else if(value instanceof Binary){
                invokeSetMethod(cl,model,paramName,((Binary) value));
            }else if(value instanceof ObjectId){
                invokeSetMethod(cl,model,paramName,((ObjectId) value));
            }else if(value instanceof Boolean){
                invokeSetMethod(cl,model,paramName,((Boolean) value));
            }else if(value instanceof Date){
                invokeSetMethod(cl,model,paramName,((Date) value));
            }else if(value instanceof BsonRegularExpression){
                invokeSetMethod(cl,model,paramName,((BsonRegularExpression) value));
            }else if(value instanceof Code){
                invokeSetMethod(cl,model,paramName,((Code) value));
            }else if(value instanceof CodeWithScope){
                invokeSetMethod(cl,model,paramName,((CodeWithScope) value));
            }else if(value instanceof Integer){
                invokeSetMethod(cl,model,paramName,((Integer) value));
            }else if(value instanceof Long){
                invokeSetMethod(cl,model,paramName,((Long) value));
            }else if(value instanceof Decimal128){
                invokeSetMethod(cl,model,paramName,((Decimal128) value));
            }else if(value instanceof BsonTimestamp){
                invokeSetMethod(cl,model,paramName,((BsonTimestamp) value));
            } else if(value instanceof DBRef){
                //invokeSetMethod(cl,model,paramName,((DBRef) value));
            }else if(value instanceof MinKey){
                invokeSetMethod(cl,model,paramName,((MinKey) value));
            }else if(value instanceof MaxKey){
                invokeSetMethod(cl,model,paramName,((MaxKey) value));
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
