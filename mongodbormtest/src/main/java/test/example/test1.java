package test.example;

import db.core.FilterBuilder;
import db.dao.PersonDao;
import db.model.Person;
import db.model.Person_Family;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test1 {

    public static void main( String[] args )
    {

        PersonDao dao = new PersonDao();

        BsonDocument doc = new BsonDocument();

        Person_Family family = new Person_Family("m","f");
        Person person = new Person();
        person.setAge(1);
        person.setName("aaaa");
        person.setFamily(family);
        dao.insertOne(person);

        FilterBuilder fb = new FilterBuilder();
        fb.eq("name",new BsonString("aaaa"));
        Person p = dao.filter.find(fb).first();

        System.out.println( p.toString());
        System.out.println( "Hello World!" );


    }


}
