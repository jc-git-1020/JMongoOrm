package db.model;

import db.core.Model;
import db.core.MongoSimple;

import java.util.ArrayList;

public class Person extends Model {

    @MongoSimple(name = "name")
    private String name;
    @MongoSimple(name = "age")
    private Integer age;
    @MongoSimple(name = "family")
    private ArrayList<String> family;

    public Person(){ }

    public Person(String name, int age, ArrayList<String> family) {
        this.name = name;
        this.age = age;
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<String> getFamily() {
        return family;
    }

    public void setFamily(ArrayList<String> family) {
        this.family = family;
    }

}
