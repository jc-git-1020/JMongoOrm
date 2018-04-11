package test.example;

import db.common.FilterBuilder;
import db.dao.PersonDao;
import db.model.Person;
import db.model.Person_Family;
import org.bson.BsonDocument;
import org.bson.BsonString;

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
        //Person p = dao.filter.find(fb).first();

        //System.out.println( p.getName());
        System.out.println( "Hello World!" );


    }


}
