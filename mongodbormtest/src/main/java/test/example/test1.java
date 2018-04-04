package test.example;

import db.dao.PersonDao;

public class test1 {

    public static void main( String[] args )
    {

        PersonDao dao = new PersonDao();
        System.out.print(dao.count());


        System.out.println( "Hello World!" );


    }


}
