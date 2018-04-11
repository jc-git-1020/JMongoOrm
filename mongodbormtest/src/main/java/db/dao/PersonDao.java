package db.dao;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import db.model.Person;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

//MongoCollectionImpl
public class PersonDao {

    private final static String collectionName = "person";
    private final MongoCollection<Document> collection;
    public final Filter filter;

    public PersonDao() {
        collection = DaoHelper.getCollection(collectionName);
        filter = new Filter();
    }

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

    public Document findOneAndReplace(final Bson filter, final Document person) {
        return collection.findOneAndReplace(filter, person);
    }

    public Document findOneAndUpdate(final Bson filter, final Bson update) {
        return collection.findOneAndUpdate(filter, update);
    }

    public List<BsonDocument> distinct(final String fieldName) {
        DistinctIterable<BsonDocument> iterable = collection.distinct(fieldName, BsonDocument.class);
        return toList(iterable);
    }

    public List<BsonDocument> distinct(final String fieldName, final Bson filter) {
        DistinctIterable<BsonDocument> iterable = collection.distinct(fieldName, filter, BsonDocument.class);
        return toList(iterable);
    }

    public List<BsonDocument> aggregate(final List<BsonDocument> pipeline) {
        AggregateIterable<BsonDocument> iterable = collection.aggregate(pipeline, BsonDocument.class);
        return toList(iterable);
    }

    public void insertOne(final Person person) {
        collection.insertOne(person.toDocument());
    }

    public void insertMany(final List<Person> persons) {
        List<Document> list = new ArrayList();
        for (Person person:persons) {
            list.add(person.toDocument());
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

    private <TResult> List<TResult> toList(MongoIterable<TResult> iterable) {
        List<TResult> list = new ArrayList<TResult>();
        MongoCursor<TResult> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                TResult item = cursor.next();
                list.add(item);
            }
        } catch (Exception e) {
            cursor.close();
            e.printStackTrace();
        }
        return list;
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

        public Document first() {
            return iterable.first();
        }

        public List<Document> list() {
            return toList(iterable);
        }
    }

}
