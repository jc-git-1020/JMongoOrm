package test.example;

import db.common.FilterBuilder;
import db.dao.PersonDao;

public class test1 {

    public static void main( String[] args )
    {

        PersonDao dao = new PersonDao();
        FilterBuilder fb = new FilterBuilder();
        fb.eq("name","aaa");



        System.out.println( "Hello World!" );


    }


}
