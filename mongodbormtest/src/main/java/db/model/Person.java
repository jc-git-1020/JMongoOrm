package db.model;

import org.bson.types.ObjectId;

public class Person {
    private ObjectId id;
    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public void setAge(int age) {
        this.age = age;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    private int age;
    private Family family;

    public Person() {

    }

    public Person(String name, int age, Family family) {
        this.id = new ObjectId();
        this.name = name;
        this.age = age;
        this.family = family;
    }

    private class Family {
        private String Monther;
        private String Fahter;

        public Family() {
        }

        public Family(String monther, String fahter) {
            Monther = monther;
            Fahter = fahter;
        }
    }

}
