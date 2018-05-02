package db.core;

public class AnnotationHelper {

//    public static Object field2value(Object obj, Field field) {
//
//        try {
//            MongoObjectId objectId = field.getAnnotation(MongoObjectId.class);
//            if (objectId != null) {
//                ObjectId oid = (ObjectId) field.get(obj);
//                return oid == null ? new BsonNull() : new BsonObjectId(oid);
//            }
//            MongoSimple simple = field.getAnnotation(MongoSimple.class);
//            if (simple != null) {
//                Object object = field.get(obj);
//                return object == null ? new BsonNull() : object;
//            }
//            MongoObject object = field.getAnnotation(MongoObject.class);
//            if (object != null) {
//                Object model = field.get(obj);
//                return model == null ? new BsonNull() : ((Model) model).toDocument();
//            }
//            MongoObjects objects = field.getAnnotation(MongoObjects.class);
//            if (objects != null) {
//                ArrayList<Model> models = (ArrayList<Model>) field.get(this);
//                return model == null ? new BsonNull() : models2Documents(models);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }



}
