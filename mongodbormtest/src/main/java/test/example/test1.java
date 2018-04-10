package test.example;

import db.common.FilterBuilder;
import db.dao.PersonDao;
import db.model.Person;
import org.bson.BsonString;

public class test1 {

    public static void main( String[] args )
    {
        PersonDao dao = new PersonDao();

        Person person = new Person();
        person.setAge(1);
        person.setName("aaaa");
        Person mother = new Person();
        mother.setAge(2);
        mother.setName("bbb");
        Person father = new Person();
        father.setAge(3);
        father.setName("ccc");
        Person.Family family = new Person.Family(mother,father);
        dao.insertOne(person);

        FilterBuilder fb = new FilterBuilder();
        fb.eq("name",new BsonString("aaaa"));
        Person p = dao.filter.find(fb).first();

        System.out.println( p.getName());
        System.out.println( "Hello World!" );


    }


}
