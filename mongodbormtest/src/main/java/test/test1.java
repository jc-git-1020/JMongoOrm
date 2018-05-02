package test;

import db.model.Person;

public class test1 {
    public static void main(String[] args) {

        Person person = new Person();
        person.setName("aa");
        person.setAge(11);

        System.out.println(person.toJson());

    }

}
