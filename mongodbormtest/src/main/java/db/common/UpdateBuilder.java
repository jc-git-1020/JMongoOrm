package db.common;

import org.bson.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.Decimal128;

import java.util.List;

import static com.mongodb.assertions.Assertions.notNull;

public class UpdateBuilder implements Bson {

    BsonDocument doc;
    BsonDocument setDoc;
    BsonDocument unsetDoc;
    BsonDocument setOnInsertDoc;
    BsonDocument renameDoc;
    BsonDocument incDoc;
    BsonDocument mulDoc;
    BsonDocument minDoc;
    BsonDocument maxDoc;
    BsonDocument currentDateDoc;
    BsonDocument addToSetDoc;
    BsonDocument pushDoc;

    public UpdateBuilder() {
        doc = new BsonDocument();
    }

    public UpdateBuilder set(final String fieldName, final BsonValue value){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(setDoc);
        setDoc.append(fieldName,value);
        return this;
    }

    public UpdateBuilder unset(final String fieldName, final BsonValue value){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(unsetDoc);
        unsetDoc.append(fieldName,value);
        return this;
    }

    public UpdateBuilder setOnInsert(final String fieldName, final BsonValue value){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(setOnInsertDoc);
        setOnInsertDoc.append(fieldName,value);
        return this;
    }

    public UpdateBuilder rename(final String fieldName, final String newFieldName){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        Utilities.stringNotNullOrEmpty("newFieldName",newFieldName);
        newOneIfNull(renameDoc);
        renameDoc.append(fieldName,new BsonString(newFieldName));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final int number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(incDoc);
        incDoc.append(fieldName,new BsonInt32(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final long number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(incDoc);
        incDoc.append(fieldName,new BsonInt64(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final double number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(incDoc);
        incDoc.append(fieldName,new BsonDouble(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final Decimal128 number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(incDoc);
        incDoc.append(fieldName,new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final int number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(mulDoc);
        mulDoc.append(fieldName,new BsonInt32(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final long number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(mulDoc);
        mulDoc.append(fieldName,new BsonInt64(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final double number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(mulDoc);
        mulDoc.append(fieldName,new BsonDouble(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final Decimal128 number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(mulDoc);
        mulDoc.append(fieldName,new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final int number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(minDoc);
        minDoc.append(fieldName,new BsonInt32(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final long number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(minDoc);
        minDoc.append(fieldName,new BsonInt64(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final double number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(minDoc);
        minDoc.append(fieldName,new BsonDouble(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final Decimal128 number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(minDoc);
        minDoc.append(fieldName,new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final int number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(maxDoc);
        maxDoc.append(fieldName,new BsonInt32(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final long number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(maxDoc);
        maxDoc.append(fieldName,new BsonInt64(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final double number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(maxDoc);
        maxDoc.append(fieldName,new BsonDouble(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final Decimal128 number){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        notNull("number", number);
        newOneIfNull(maxDoc);
        maxDoc.append(fieldName,new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder currentDate(final String fieldName){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(currentDateDoc);
        currentDateDoc.append(fieldName,new BsonBoolean(true));
        return this;
    }

    public UpdateBuilder currentTimestamp(final String fieldName){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(currentDateDoc);
        currentDateDoc.append(fieldName,new BsonDocument("$type", new BsonString("timestamp")));
        return this;
    }

    public UpdateBuilder addToSet(final String fieldName, final BsonValue value){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(addToSetDoc);
        notNull("value",value);
        addToSetDoc.append(fieldName,value);
        return this;
    }

    public UpdateBuilder addEachToSet(final String fieldName, final List<BsonValue> values){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(addToSetDoc);
        addToSetDoc.append(fieldName,new BsonArray(values));
        return this;
    }

    public UpdateBuilder push(final String fieldName, final BsonValue value){
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        newOneIfNull(pushDoc);
        pushDoc.append(fieldName,value);
        return this;
    }



    public UpdateBuilder clear(){
        doc.clear();
        clearIfNotNull(setDoc);
        clearIfNotNull(setDoc);
        clearIfNotNull(unsetDoc);
        clearIfNotNull(setOnInsertDoc);
        clearIfNotNull(renameDoc);
        clearIfNotNull(incDoc);
        clearIfNotNull(mulDoc);
        clearIfNotNull(minDoc);
        clearIfNotNull(maxDoc);
        clearIfNotNull(currentDateDoc);
        clearIfNotNull(addToSetDoc);
        clearIfNotNull(pushDoc);
        return this;
    }

    private BsonDocument newOneIfNull(BsonDocument doc){
        if(doc != null)
            doc = new BsonDocument();
        return doc;
    }

    private void clearIfNotNull(BsonDocument doc){
        if(doc != null)
            doc.clear();
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return doc;
    }

    public String toJson(){
        return doc.toJson();
    }

    @Override
    public String toString() {
        return doc.toJson();
    }
}
