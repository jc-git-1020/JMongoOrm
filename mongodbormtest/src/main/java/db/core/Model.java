package db.core;

import org.bson.*;
import org.bson.types.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public abstract class Model {

    public Model() {
    }

    public final Document toDocument() {
        Document doc = new Document();

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                checkFieldType(field);
                field.setAccessible(true);
                MongoObjectId objectId = field.getAnnotation(MongoObjectId.class);
                if (objectId != null) {
                    ObjectId oid = (ObjectId) field.get(this);
                    if (oid != null) doc.append(objectId.name(), new BsonObjectId(oid));
                    continue;
                }
                MongoSimple simple = field.getAnnotation(MongoSimple.class);
                if (simple != null) {
                    Object o = field.get(this);
                    doc.append(simple.name(), o == null ? new BsonNull() : o);
                    continue;
                }
                MongoObject object = field.getAnnotation(MongoObject.class);
                if (object != null) {
                    Object model = field.get(this);
                    doc.append(object.name(), model == null ? new BsonNull() : ((Model) model).toDocument());
                    continue;
                }
                MongoObjects objects = field.getAnnotation(MongoObjects.class);
                if (objects != null) {
                    ArrayList<Model> models = (ArrayList<Model>) field.get(this);
                    doc.append(objects.name(), models == null ? new BsonNull() : models2Documents(models));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public String toJson() {
        return toDocument().toJson();
    }

    protected <E extends Model> ArrayList<Document> models2Documents(ArrayList<E> models) {
        ArrayList<Document> list = new ArrayList<>();
        models.forEach(model -> list.add(model.toDocument()));
        return list;
    }

    private boolean checkFieldType(Field field) {
        Class type = field.getType();
        if (type == ObjectId.class) return true;
        if (type == Integer.class) return true;
        if (type == Double.class) return true;
        if (type == Long.class) return true;
        if (type == String.class) return true;
        if (type == Code.class) return true;
        if (type == ArrayList.class) return true;
        if (type == Binary.class) return true;
        if (type == Boolean.class) return true;
        if (type == CodeWithScope.class) return true;
        if (type == Date.class) return true;
        if (type == Decimal128.class) return true;
        if (type == BsonRegularExpression.class) return true;
        if (type == BsonTimestamp.class) return true;
        if (type == MinKey.class) return true;
        if (type == MaxKey.class) return true;
        if (type.getSuperclass() == Model.class) return true;
        throw new RuntimeException(field.getName() + " 字段类型不支持，请查看相关文档");
    }

}
