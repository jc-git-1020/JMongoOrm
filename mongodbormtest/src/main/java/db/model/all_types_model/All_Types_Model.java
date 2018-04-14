package db.model.all_types_model;

import db.core.Model;
import org.bson.*;
import org.bson.types.ObjectId;

public class All_Types_Model implements Model {
    private ObjectId id;
    private BsonJavaScript CodeField;
    private String StringField;
    private BsonArray ArrayField;
    private BsonBinary BinaryField;
    private boolean BooleaField;
    private BsonJavaScriptWithScope CodeWithScopeField;
    private BsonDateTime DateField;
    //TODO ReferenceField 放弃？
    private BsonNull NullField;
    private double DoubleField;
    private int Int32Field;
    private long Int64Field;
    private BsonDecimal128 Decimal128Field;
    private BsonDocument ObjectField;
    private BsonRegularExpression RegExField;
    private BsonTimestamp TimestampField;
    private BsonMinKey MinKeyField;
    private BsonMaxKey MaxKeyField;
    private BsonArray ObjectsArray;

    public All_Types_Model() {
    }

    public All_Types_Model(ObjectId id, BsonJavaScript codeField, String stringField, BsonArray arrayField, BsonBinary binaryField, boolean booleaField, BsonJavaScriptWithScope codeWithScopeField, BsonDateTime dateField, BsonNull nullField, double doubleField, int int32Field, long int64Field, BsonDecimal128 decimal128Field, BsonDocument objectField, BsonRegularExpression regExField, BsonTimestamp timestampField, BsonMinKey minKeyField, BsonMaxKey maxKeyField, BsonArray objectsArray) {
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

    public BsonJavaScript getCodeField() {
        return CodeField;
    }

    public void setCodeField(BsonJavaScript codeField) {
        CodeField = codeField;
    }

    public String getStringField() {
        return StringField;
    }

    public void setStringField(String stringField) {
        StringField = stringField;
    }

    public BsonArray getArrayField() {
        return ArrayField;
    }

    public void setArrayField(BsonArray arrayField) {
        ArrayField = arrayField;
    }

    public BsonBinary getBinaryField() {
        return BinaryField;
    }

    public void setBinaryField(BsonBinary binaryField) {
        BinaryField = binaryField;
    }

    public boolean isBooleaField() {
        return BooleaField;
    }

    public void setBooleaField(boolean booleaField) {
        BooleaField = booleaField;
    }

    public BsonJavaScriptWithScope getCodeWithScopeField() {
        return CodeWithScopeField;
    }

    public void setCodeWithScopeField(BsonJavaScriptWithScope codeWithScopeField) {
        CodeWithScopeField = codeWithScopeField;
    }

    public BsonDateTime getDateField() {
        return DateField;
    }

    public void setDateField(BsonDateTime dateField) {
        DateField = dateField;
    }

    public BsonNull getNullField() {
        return NullField;
    }

    public void setNullField(BsonNull nullField) {
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

    public BsonDecimal128 getDecimal128Field() {
        return Decimal128Field;
    }

    public void setDecimal128Field(BsonDecimal128 decimal128Field) {
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

    public BsonMinKey getMinKeyField() {
        return MinKeyField;
    }

    public void setMinKeyField(BsonMinKey minKeyField) {
        MinKeyField = minKeyField;
    }

    public BsonMaxKey getMaxKeyField() {
        return MaxKeyField;
    }

    public void setMaxKeyField(BsonMaxKey maxKeyField) {
        MaxKeyField = maxKeyField;
    }

    public BsonArray getObjectsArray() {
        return ObjectsArray;
    }

    public void setObjectsArray(BsonArray objectsArray) {
        ObjectsArray = objectsArray;
    }

    @Override
    public Document toDocument() {
        return null;
    }
}
