package db.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import db.model.Person;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

//MongoCollectionImpl
public class PersonDao {

    private final static String collectionName = "person";
    private final MongoCollection<Person> collection;
    public final Filter filter;

    public PersonDao() {
        collection = DaoHelper.getCollection(collectionName,Person.class);
        filter = new Filter();
    }

    public long count(){
        return collection.count();
    }

    public long count(final Bson filter){
        return collection.count(filter);
    }

    public UpdateResult updateOne(final Bson filter, final Bson update){
        return collection.updateOne(filter,update);
    }

    public UpdateResult updateMany(final Bson filter, final Bson update){
        return collection.updateMany(filter,update);
    }

    public Person findOneAndDelete(final Bson filter){
        return collection.findOneAndDelete(filter);
    }

    public Person findOneAndReplace(final Bson filter,final Person person){
        return collection.findOneAndReplace(filter,person);
    }

    public Person findOneAndUpdate(final Bson filter, final Bson update){
        return collection.findOneAndUpdate(filter,update);
    }

//    public void drop(){
//        collection.drop();
//    }

    public void remove(){}

    //参考FindIterableImpl
    private class Filter{

        FindIterable<Person> iterable;

        public Filter() {
            iterable = collection.find(new BsonDocument());
        }

        public Filter find(final Bson filter){
            iterable = collection.find(filter);
            return this;
        }

        public Filter limit(final int limit){
            iterable = iterable.limit(limit);
            return this;
        }

        public Filter skip(final int skip){
            iterable = iterable.skip(skip);
            return this;
        }

        public Filter projection(final Bson projection){
            iterable = iterable.projection(projection);
            return this;
        }

        public Filter sort(final Bson sort){
            iterable = iterable.sort(sort);
            return this;
        }

        public Person first(){
            return iterable.first();
        }

        public List<Person> list(){
            List<Person> list = new ArrayList<Person>();
            MongoCursor<Person> cursor = iterable.iterator();
            try {
                while (cursor.hasNext()){
                    Person person = cursor.next();
                    list.add(person);
                }
            } catch (Exception e) {
                cursor.close();
                e.printStackTrace();
            }
            return list;
        }
    }

}
