package db.model;

import db.core.Model;
import org.bson.BsonInt32;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Person extends Model {
    private ObjectId id;
    private String name;
    private int age;
    private Person_Family family;

    public Person() {
    }

    public boolean isNew(){
        return id == null;
    }

    public Person(ObjectId id, String name, int age, Person_Family family) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.family = family;
    }

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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person_Family getFamily() {
        return family;
    }

    public void setFamily(Person_Family family) {
        this.family = family;
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        if(!isNew())
            doc.append("_id",new BsonObjectId(id));
        doc.append("name",new BsonString(name));
        doc.append("age",new BsonInt32(age));
        doc.append("family",family.toDocument());
        return doc;
    }

}
