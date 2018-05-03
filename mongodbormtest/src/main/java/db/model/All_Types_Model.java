package db.model;

import db.core.*;
import org.bson.*;
import org.bson.types.*;

import java.util.ArrayList;
import java.util.Date;

public class All_Types_Model extends Model {

    @MongoObjectId
    private ObjectId id;
    @MongoSimple(name = "StringField")
    private String StringField;
    @MongoSimple(name = "ArrayField")
    private ArrayList<Integer> ArrayField;
    @MongoSimple(name = "BoolField")
    private Boolean BoolField;
    @MongoSimple(name = "DateField",ignoreIfNull = true)
    private Date DateField;
    @MongoSimple(name = "DoubleField")
    private Double DoubleField;
    @MongoSimple(name = "Int32Field")
    private Integer Int32Field;
    @MongoSimple(name = "Int64Field")
    private Long Int64Field;
    @MongoSimple(name = "TimestampField")
    private BsonTimestamp TimestampField;
    @MongoObject(name = "person")
    private Person person;
    @MongoObjects(name = "persons")
    private ArrayList<Person> persons;

    public All_Types_Model() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getStringField() {
        return StringField;
    }

    public void setStringField(String stringField) {
        StringField = stringField;
    }

    public ArrayList getArrayField() {
        return ArrayField;
    }

    public void setArrayField(ArrayList<Integer> arrayField) {
        ArrayField = arrayField;
    }

    public Boolean isBoolField() {
        return BoolField;
    }

    public void setBoolField(Boolean boolField) {
        BoolField = boolField;
    }

    public Date getDateField() {
        return DateField;
    }

    public void setDateField(Date dateField) {
        DateField = dateField;
    }

    public Double getDoubleField() {
        return DoubleField;
    }

    public void setDoubleField(Double doubleField) {
        DoubleField = doubleField;
    }

    public Integer getInt32Field() {
        return Int32Field;
    }

    public void setInt32Field(Integer int32Field) {
        Int32Field = int32Field;
    }

    public Long getInt64Field() {
        return Int64Field;
    }

    public void setInt64Field(Long int64Field) {
        Int64Field = int64Field;
    }

    public BsonTimestamp getTimestampField() {
        return TimestampField;
    }

    public void setTimestampField(BsonTimestamp timestampField) {
        TimestampField = timestampField;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

}
