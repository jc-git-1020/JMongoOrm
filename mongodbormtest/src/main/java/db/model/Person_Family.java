package db.model;

import db.core.Model;

public class Person_Family extends Model {

    public String mother;
    public String father;

    public Person_Family() {
    }

    public Person_Family(String mother, String father) {
        this.mother = mother;
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

//    @Override
//    public Document toDocument() {
//        Document doc = new Document();
//        doc.append("mother",new BsonString(mother));
//        doc.append("father",new BsonString(father));
//        return doc;
//    }

}
