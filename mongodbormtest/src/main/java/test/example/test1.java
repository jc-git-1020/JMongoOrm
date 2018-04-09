package test.example;

import db.common.FilterBuilder;
import db.dao.PersonDao;
import db.model.Person;
import org.bson.BsonString;

public class test1 {

    public static void main( String[] args )
    {

        PersonDao dao = new PersonDao();
        FilterBuilder fb = new FilterBuilder();
        fb.eq("name",new BsonString("aaaa"));
        Person p = dao.filter.find(fb).first();

        System.out.println( p.getName());
        System.out.println( "Hello World!" );


    }


}
