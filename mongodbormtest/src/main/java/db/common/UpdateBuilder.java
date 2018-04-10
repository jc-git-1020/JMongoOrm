package db.common;

import com.mongodb.client.model.PushOptions;
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
    BsonDocument pullDoc;
    BsonDocument pullAllDoc;
    BsonDocument popDoc;
    BsonDocument bitwiseDoc;

    public UpdateBuilder() {
        doc = new BsonDocument();
    }

    public UpdateBuilder set(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(setDoc);
        setDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder unset(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(unsetDoc);
        unsetDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder setOnInsert(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(setOnInsertDoc);
        setOnInsertDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder rename(final String fieldName, final String newFieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        Utilities.stringNotNullOrEmpty("newFieldName", newFieldName);
        newIfNull(renameDoc);
        renameDoc.append(fieldName, new BsonString(newFieldName));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final int number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(incDoc);
        incDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final long number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(incDoc);
        incDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final double number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(incDoc);
        incDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final Decimal128 number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(incDoc);
        incDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final int number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(mulDoc);
        mulDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final long number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(mulDoc);
        mulDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final double number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(mulDoc);
        mulDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final Decimal128 number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(mulDoc);
        mulDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final int number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(minDoc);
        minDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final long number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(minDoc);
        minDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final double number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(minDoc);
        minDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final Decimal128 number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(minDoc);
        minDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final int number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(maxDoc);
        maxDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final long number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(maxDoc);
        maxDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final double number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(maxDoc);
        maxDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final Decimal128 number) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        newIfNull(maxDoc);
        maxDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder currentDate(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(currentDateDoc);
        currentDateDoc.append(fieldName, new BsonBoolean(true));
        return this;
    }

    public UpdateBuilder currentTimestamp(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(currentDateDoc);
        currentDateDoc.append(fieldName, new BsonDocument("$type", new BsonString("timestamp")));
        return this;
    }

    public UpdateBuilder addToSet(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(addToSetDoc);
        notNull("value", value);
        addToSetDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder addEachToSet(final String fieldName, final List<BsonValue> values) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(addToSetDoc);
        addToSetDoc.append(fieldName, new BsonArray(values));
        return this;
    }

    public UpdateBuilder push(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(pushDoc);
        pushDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder pushEach(final String fieldName, final List<BsonValue> values) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(pushDoc);
        pushDoc.append(fieldName, new BsonDocument("$each", new BsonArray(values)));
        return this;
    }

    public UpdateBuilder pushEach(final String fieldName, final List<BsonValue> values, final PushOptions options) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(pushDoc);
        BsonDocument temp = new BsonDocument();
        temp.append("$each", new BsonArray(values));
        if (options.getPosition() != null)
            temp.append("$position", new BsonInt32(options.getPosition()));
        if (options.getSlice() != null)
            temp.append("$slice", new BsonInt32(options.getSlice()));
        if (options.getSort() != null)
            temp.append("$sort", new BsonInt32(options.getSort()));
        else if (options.getSortDocument() != null)
            temp.append("$sort", (BsonValue) options.getSortDocument());
        pushDoc.append(fieldName, temp);
        return this;
    }

    public UpdateBuilder pull(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(pullDoc);
        pullDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder pullAll(final String fieldName, final List<BsonValue> values) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(pullAllDoc);
        pullAllDoc.append(fieldName, new BsonArray(values));
        return this;
    }

    public UpdateBuilder popFirst(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(popDoc);
        popDoc.append(fieldName, new BsonInt32(1));
        return this;
    }

    public UpdateBuilder popLast(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(popDoc);
        popDoc.append(fieldName, new BsonInt32(-1));
        return this;
    }

    public UpdateBuilder bitwiseAnd(final String fieldName, final int value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$and", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseAnd(final String fieldName, final long value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$and", new BsonInt64(value)));
        return this;
    }

    public UpdateBuilder bitwiseOr(final String fieldName, final int value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$or", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseOr(final String fieldName, final long value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$or", new BsonInt64(value)));
        return this;
    }

    public UpdateBuilder bitwiseXor(final String fieldName, final int value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$xor", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseXor(final String fieldName, final long value) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        newIfNull(bitwiseDoc);
        bitwiseDoc.append(fieldName, new BsonDocument("$xor", new BsonInt64(value)));
        return this;
    }

    public UpdateBuilder clear() {
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
        clearIfNotNull(pullDoc);
        clearIfNotNull(pullAllDoc);
        clearIfNotNull(popDoc);
        clearIfNotNull(bitwiseDoc);
        return this;
    }

    private BsonDocument newIfNull(BsonDocument doc) {
        if (doc != null)
            doc = new BsonDocument();
        return doc;
    }

    private void clearIfNotNull(BsonDocument doc) {
        if (doc != null)
            doc.clear();
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return doc;
    }

    public String toJson() {
        return doc.toJson();
    }

    @Override
    public String toString() {
        return doc.toJson();
    }
}
