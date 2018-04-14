package db.core;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//MongoCollectionImpl
public abstract class Dao <T extends Model> {

    private final MongoCollection<Document> collection;
    public final Filter filter;

    public Dao() {
        collection = DaoHelper.getCollection(this.getCollectionName());
        filter = new Filter();
    }

    abstract String getCollectionName();

    abstract Class<T> getModelClass();

    public long count() {
        return collection.count();
    }

    public long count(final Bson filter) {
        return collection.count(filter);
    }

    public UpdateResult updateOne(final Bson filter, final Bson update) {
        return collection.updateOne(filter, update);
    }

    public UpdateResult updateMany(final Bson filter, final Bson update) {
        return collection.updateMany(filter, update);
    }

    public Document findOneAndDelete(final Bson filter) {
        return collection.findOneAndDelete(filter);
    }

    public Document findOneAndReplace(final Bson filter, final T model) {
        return collection.findOneAndReplace(filter, model.toDocument());
    }

    public Document findOneAndUpdate(final Bson filter, final Bson update) {
        return collection.findOneAndUpdate(filter, update);
    }

    public List<T> distinct(final String fieldName) {
        DistinctIterable<Document> iterable = collection.distinct(fieldName, Document.class);
        return toModelList(iterable);
    }

    public List<T> distinct(final String fieldName, final Bson filter) {
        DistinctIterable<Document> iterable = collection.distinct(fieldName, filter, Document.class);
        return toModelList(iterable);
    }

    public List<BsonDocument> aggregate(final List<BsonDocument> pipeline) {
        AggregateIterable<BsonDocument> iterable = collection.aggregate(pipeline, BsonDocument.class);
        return toList(iterable);
    }

    public void insertOne(final T model) {
        collection.insertOne(model.toDocument());
    }

    public void insertMany(final List<T> models) {
        List<Document> list = new ArrayList();
        for (T model:models) {
            list.add(model.toDocument());
        }
        collection.insertMany(list);
    }

    public DeleteResult deleteOne(final Bson filter) {
        return collection.deleteOne(filter);
    }

    public DeleteResult deleteMany(final Bson filter) {
        return collection.deleteMany(filter);
    }

    public UpdateResult replaceOne(final Bson filter, final Document replacement) {
        return collection.replaceOne(filter, replacement);
    }

    //todo bulk

    private List<T> toModelList(MongoIterable<Document> iterable) {
        List<T> list = new ArrayList<T>();
        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                Document item = cursor.next();
                list.add(create(item));
            }
        } catch (Exception e) {
            cursor.close();
            e.printStackTrace();
        }
        return list;
    }

    private <E> List<E> toList(MongoIterable<E> iterable){
        List<E> list = new ArrayList<E>();
        MongoCursor<E> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                E item = cursor.next();
                list.add(item);
            }
        } catch (Exception e) {
            cursor.close();
            e.printStackTrace();
        }
        return list;
    }

    private T create(Map map){
        return (T)ReflectHelper.create(this.getModelClass().getName(),map);
    }

    //参考FindIterableImpl
    public class Filter {

        FindIterable<Document> iterable;

        public Filter() {
            iterable = collection.find(new BsonDocument());
        }

        public Filter find(final Bson filter) {
            iterable = collection.find(filter);
            return this;
        }

        public Filter limit(final int limit) {
            iterable = iterable.limit(limit);
            return this;
        }

        public Filter skip(final int skip) {
            iterable = iterable.skip(skip);
            return this;
        }

        public Filter projection(final Bson projection) {
            iterable = iterable.projection(projection);
            return this;
        }

        public Filter sort(final Bson sort) {
            iterable = iterable.sort(sort);
            return this;
        }

        public T first() {
            Document doc = iterable.first();
            return create(doc);
        }

        public List<T> list() {
            return toModelList(iterable);
        }
    }

}
