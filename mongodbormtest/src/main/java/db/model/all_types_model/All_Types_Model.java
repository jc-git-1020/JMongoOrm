package db.model.all_types_model;

import db.core.Model;
import org.bson.*;
import org.bson.types.*;

import java.util.ArrayList;
import java.util.Date;

public class All_Types_Model implements Model {
    private ObjectId id;
    private Code CodeField;
    private String StringField;
    private ArrayList ArrayField;
    private Binary BinaryField;
    private boolean BooleaField;
    private CodeWithScope CodeWithScopeField;
    private Date DateField;
    //TODO ReferenceField 放弃？ DBRef
    private String NullField;
    private double DoubleField;
    private int Int32Field;
    private long Int64Field;
    private Decimal128 Decimal128Field;
    private BsonDocument ObjectField;
    private BsonRegularExpression RegExField;
    private BsonTimestamp TimestampField;
    private MinKey MinKeyField;
    private MaxKey MaxKeyField;
    private BsonArray ObjectsArray;

    public All_Types_Model() {
    }

    public All_Types_Model(ObjectId id, Code codeField, String stringField, ArrayList arrayField, Binary binaryField, boolean booleaField, CodeWithScope codeWithScopeField, Date dateField, String nullField, double doubleField, int int32Field, long int64Field, Decimal128 decimal128Field, BsonDocument objectField, BsonRegularExpression regExField, BsonTimestamp timestampField, MinKey minKeyField, MaxKey maxKeyField, BsonArray objectsArray) {
        this.id = id;
        CodeField = codeField;
        StringField = stringField;
        ArrayField = arrayField;
        BinaryField = binaryField;
        BooleaField = booleaField;
        CodeWithScopeField = codeWithScopeField;
        DateField = dateField;
        NullField = nullField;
        DoubleField = doubleField;
        Int32Field = int32Field;
        Int64Field = int64Field;
        Decimal128Field = decimal128Field;
        ObjectField = objectField;
        RegExField = regExField;
        TimestampField = timestampField;
        MinKeyField = minKeyField;
        MaxKeyField = maxKeyField;
        ObjectsArray = objectsArray;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Code getCodeField() {
        return CodeField;
    }

    public void setCodeField(Code codeField) {
        CodeField = codeField;
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

    public void setArrayField(ArrayList arrayField) {
        ArrayField = arrayField;
    }

    public Binary getBinaryField() {
        return BinaryField;
    }

    public void setBinaryField(Binary binaryField) {
        BinaryField = binaryField;
    }

    public boolean isBooleaField() {
        return BooleaField;
    }

    public void setBooleaField(boolean booleaField) {
        BooleaField = booleaField;
    }

    public CodeWithScope getCodeWithScopeField() {
        return CodeWithScopeField;
    }

    public void setCodeWithScopeField(CodeWithScope codeWithScopeField) {
        CodeWithScopeField = codeWithScopeField;
    }

    public Date getDateField() {
        return DateField;
    }

    public void setDateField(Date dateField) {
        DateField = dateField;
    }

    public String getNullField() {
        return NullField;
    }

    public void setNullField(String nullField) {
        NullField = nullField;
    }

    public double getDoubleField() {
        return DoubleField;
    }

    public void setDoubleField(double doubleField) {
        DoubleField = doubleField;
    }

    public int getInt32Field() {
        return Int32Field;
    }

    public void setInt32Field(int int32Field) {
        Int32Field = int32Field;
    }

    public long getInt64Field() {
        return Int64Field;
    }

    public void setInt64Field(long int64Field) {
        Int64Field = int64Field;
    }

    public Decimal128 getDecimal128Field() {
        return Decimal128Field;
    }

    public void setDecimal128Field(Decimal128 decimal128Field) {
        Decimal128Field = decimal128Field;
    }

    public BsonDocument getObjectField() {
        return ObjectField;
    }

    public void setObjectField(BsonDocument objectField) {
        ObjectField = objectField;
    }

    public BsonRegularExpression getRegExField() {
        return RegExField;
    }

    public void setRegExField(BsonRegularExpression regExField) {
        RegExField = regExField;
    }

    public BsonTimestamp getTimestampField() {
        return TimestampField;
    }

    public void setTimestampField(BsonTimestamp timestampField) {
        TimestampField = timestampField;
    }

    public MinKey getMinKeyField() {
        return MinKeyField;
    }

    public void setMinKeyField(MinKey minKeyField) {
        MinKeyField = minKeyField;
    }

    public MaxKey getMaxKeyField() {
        return MaxKeyField;
    }

    public void setMaxKeyField(MaxKey maxKeyField) {
        MaxKeyField = maxKeyField;
    }

    public BsonArray getObjectsArray() {
        return ObjectsArray;
    }

    public void setObjectsArray(BsonArray objectsArray) {
        ObjectsArray = objectsArray;
    }

    public boolean isNew(){
        return id == null;
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        if(!isNew())
            doc.append("_id",new BsonObjectId(id));

        return doc;

    }
}
