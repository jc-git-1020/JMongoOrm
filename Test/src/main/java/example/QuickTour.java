package example;

import db.core.FilterBuilder;
import db.core.ProjectBuilder;
import db.core.UpdateBuilder;
import db.dao.All_Types_ModelDao;
import db.model.All_Types_Model;
import db.model.Person;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonTimestamp;

import java.util.ArrayList;
import java.util.List;

public class QuickTour {

    static All_Types_ModelDao dao = new All_Types_ModelDao();

    public static void main(String[] args) {
        System.out.println("start");

        clear();

        insert("first", 1);

        delete("StringField", "first", "Int32Field", 1);

        insert("second", 2);
        insert("third", 3);
        update("StringField", "second", "StringField", "second second", "Int32Field", 2);

        query("StringField", "third", "Int32Field", 3);

        query2();

        System.out.println("over");
    }

    private static void insert(String str, Integer i) {
        System.out.println("insert");
        All_Types_Model model = new All_Types_Model();
        model.setStringField(str);
        model.setInt32Field(i);
        ArrayList<Integer> is = new ArrayList<>();
        is.add(1);
        is.add(2);
        is.add(3);
        model.setArrayField(is);
        model.setBoolField(true);
        //model.setDateField(new Date());
        model.setDoubleField(1.1);
        model.setInt64Field(11111111111111111L);
        model.setTimestampField(new BsonTimestamp(11111));
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

    private static void clear(){
        System.out.println("clear");
        long count = dao.deleteMany(new BsonDocument()).getDeletedCount();
        System.out.println(count);
    }

    private static void delete(String stringField, String str, String intergerField, Integer i) {
        System.out.println("delete");
        //支持链式编程
        FilterBuilder fb = new FilterBuilder();
        fb.eq(stringField, new BsonString(str)).eq(intergerField, new BsonInt32(i));
        long count = dao.deleteOne(fb).getDeletedCount();
        System.out.println(count);
    }

    private static void update(String stringField, String str, String stringField2, String str2, String intergerField, Integer i) {
        System.out.println("update");
        FilterBuilder fb = new FilterBuilder();
        fb.eq(stringField, new BsonString(str));
        //支持链式编程
        UpdateBuilder ub = new UpdateBuilder();
        ub.set(stringField2, new BsonString(str2)).set(intergerField, new BsonInt32(i));

        long count = dao.updateOne(fb, ub).getModifiedCount();
        System.out.println(count);
    }

    private static void query(String stringField, String str, String intergerField, Integer i) {
        System.out.println("query");

        FilterBuilder fb = new FilterBuilder();
        fb.eq(stringField, new BsonString(str)).eq(intergerField, new BsonInt32(i));
        All_Types_Model model = dao.filter.find(fb).first();
        System.out.println(model.toJson());
    }

    private static void query2() {
        System.out.println("query2");

        FilterBuilder fb = new FilterBuilder();
        ProjectBuilder pb = new ProjectBuilder();
        pb.project("StringField");
        List<All_Types_Model> list = dao.filter.find(fb).projection(pb).list();
        ArrayList<All_Types_Model> models = new ArrayList<>(list);

        System.out.println(models.size());
    }

}
