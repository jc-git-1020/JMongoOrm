package db.model;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class Person implements Bson {
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

        public String getMonther() {
            return Monther;
        }

        public void setMonther(String monther) {
            Monther = monther;
        }

        public String getFahter() {
            return Fahter;
        }

        public void setFahter(String fahter) {
            Fahter = fahter;
        }

        public Family() {
        }

        public Family(String monther, String fahter) {
            Monther = monther;
            Fahter = fahter;
        }
    }

    //todo 补充doc的生成
    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return null;
    }

}
