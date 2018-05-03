package db.model.all_types_model;

import db.core.*;
import org.bson.*;
import org.bson.types.*;

import java.util.ArrayList;
import java.util.Date;

public class All_Types_Model extends Model {
    @MongoObjectId
    private ObjectId id;
    @MongoSimple(name = "CodeField")
    private Code CodeField;
    @MongoSimple(name = "StringField")
    private String StringField;
    @MongoSimple(name = "ArrayField")
    private ArrayList<Integer> ArrayField;
    @MongoSimple(name = "BinaryField")
    private Binary BinaryField;
    @MongoSimple(name = "BoolField")
    private Boolean BoolField;
    @MongoSimple(name = "CodeWithScopeField")
    private CodeWithScope CodeWithScopeField;
    @MongoSimple(name = "DateField")
    private Date DateField;
    //TODO ReferenceField 放弃？ DBRef
    private String NullField;
    @MongoSimple(name = "DoubleField")
    private Double DoubleField;
    @MongoSimple(name = "Int32Field")
    private Integer Int32Field;
    @MongoSimple(name = "Int64Field")
    private Long Int64Field;
    @MongoSimple(name = "Decimal128Field")
    private Decimal128 Decimal128Field;
    @MongoObject(name = "ObjectField")
    private All_Types_Model.ObjectField ObjectField;
    @MongoSimple(name = "RegExField")
    private BsonRegularExpression RegExField;
    @MongoSimple(name = "TimestampField")
    private BsonTimestamp TimestampField;
    @MongoSimple(name = "MinKeyField")
    private MinKey MinKeyField;
    @MongoSimple(name = "MaxKeyField")
    private MaxKey MaxKeyField;
    @MongoObjects(name = "ObjectsArray")
    private ArrayList<ObjectsArray_Item> ObjectsArray;

    public All_Types_Model() {
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

    public void setArrayField(ArrayList<Integer> arrayField) {
        ArrayField = arrayField;
    }

    public Binary getBinaryField() {
        return BinaryField;
    }

    public void setBinaryField(Binary binaryField) {
        BinaryField = binaryField;
    }

    public Boolean isBoolField() {
        return BoolField;
    }

    public void setBoolField(Boolean boolField) {
        BoolField = boolField;
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

    public Decimal128 getDecimal128Field() {
        return Decimal128Field;
    }

    public void setDecimal128Field(Decimal128 decimal128Field) {
        Decimal128Field = decimal128Field;
    }

    public All_Types_Model.ObjectField getObjectField() {
        return ObjectField;
    }

    public void setObjectField(All_Types_Model.ObjectField objectField) {
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

    public ArrayList<ObjectsArray_Item> getObjectsArray() {
        return ObjectsArray;
    }

    public void setObjectsArray(ArrayList<ObjectsArray_Item> objectsArray) {
        ObjectsArray = objectsArray;
    }

    public Boolean isNew() {
        return id == null;
    }
    
/////////////////////////内部类分割线///////////////////////////////////////

    public class ObjectField extends Model {

        @MongoSimple(name = "Key")
        private String Key;

        public ObjectField() {
        }

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

    }

    public class ObjectsArray_Item extends Model {
        @MongoSimple(name = "Key")
        private String key;

        public ObjectsArray_Item() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

}
