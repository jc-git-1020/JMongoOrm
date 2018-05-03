package test.example;

import db.core.FilterBuilder;
import db.core.UpdateBuilder;
import db.dao.All_Types_ModelDao;
import db.model.All_Types_Model;
import db.model.Person;
import org.bson.BsonInt32;
import org.bson.BsonString;

import java.util.ArrayList;
import java.util.Date;

public class QuickTour {

    static All_Types_ModelDao dao = new All_Types_ModelDao();

    public static void main(String[] args) {
        System.out.println("start");

        insert("first", 1);

        delete("StringField", "first", "Int32Field", 1);

        insert("second", 2);
        insert("third", 3);

        System.out.println("over");
    }

    private static void insert(String str, Integer i) {
        All_Types_Model model = new All_Types_Model();
        model.setStringField(str);
        model.setInt32Field(i);
        ArrayList<Integer> is = new ArrayList<>();
        is.add(1);
        is.add(2);
        is.add(3);
        model.setArrayField(is);
        model.setBoolField(true);
        model.setDateField(new Date());
        model.setDoubleField(1.1);
        model.setInt64Field(11111111111111111L);
        ArrayList<String> family = new ArrayList<>();
        family.add("b");
        family.add("c");
        Person person = new Person("a", 1, family);
        model.setPerson(person);
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person);
        model.setPersons(persons);
        System.out.println(model.toJson());
        dao.insertOne(model);
    }

    private static void delete(String stringField, String str, String intergerField, Integer i) {
        //支持链式编程
        FilterBuilder fb = new FilterBuilder();
        fb.eq(stringField, new BsonString(str)).eq(intergerField, new BsonInt32(i));
        dao.deleteOne(fb);
    }

    private static void update(String stringField, String str, String stringField2, String str2, String intergerField, Integer i) {
        UpdateBuilder ub = new UpdateBuilder();

    }

    private static void query() {

    }

}
