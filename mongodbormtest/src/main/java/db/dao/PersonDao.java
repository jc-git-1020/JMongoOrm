package db.dao;

import com.mongodb.client.MongoCollection;
import db.model.Person;

public class PersonDao {

    private static String collectionName = "person";

    private MongoCollection<Person> collection = DaoHelper.getCollection(collectionName,Person.class);

    public long count(){
        return collection.count();
    }

    public void update(){}

    public void remove(){}

    public void find(){}



}
