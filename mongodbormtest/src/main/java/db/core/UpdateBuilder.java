package db.core;

import com.mongodb.client.model.PushOptions;
import org.bson.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.Decimal128;

import java.util.List;

import static com.mongodb.assertions.Assertions.notNull;

public class UpdateBuilder extends Builder {

    private BsonDocument setDoc = new BsonDocument();
    private BsonDocument unsetDoc = new BsonDocument();
    private BsonDocument setOnInsertDoc = new BsonDocument();
    private BsonDocument renameDoc = new BsonDocument();
    private BsonDocument incDoc = new BsonDocument();
    private BsonDocument mulDoc = new BsonDocument();
    private BsonDocument minDoc = new BsonDocument();
    private BsonDocument maxDoc = new BsonDocument();
    private BsonDocument currentDateDoc = new BsonDocument();
    private BsonDocument addToSetDoc = new BsonDocument();
    private BsonDocument pushDoc = new BsonDocument();
    private BsonDocument pullDoc = new BsonDocument();
    private BsonDocument pullAllDoc = new BsonDocument();
    private BsonDocument popDoc = new BsonDocument();
    private BsonDocument bitwiseDoc = new BsonDocument();

    public UpdateBuilder set(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        setDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder unset(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        unsetDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder setOnInsert(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        setOnInsertDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder rename(final String fieldName, final String newFieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        StringHelper.notNullOrEmpty("newFieldName", newFieldName);
        renameDoc.append(fieldName, new BsonString(newFieldName));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final int number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        incDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final long number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        incDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final double number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        incDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder inc(final String fieldName, final Decimal128 number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        incDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final int number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        mulDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final long number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        mulDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final double number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        mulDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder mul(final String fieldName, final Decimal128 number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        mulDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final int number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        minDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final long number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        minDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final double number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        minDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder min(final String fieldName, final Decimal128 number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        minDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final int number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        maxDoc.append(fieldName, new BsonInt32(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final long number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        maxDoc.append(fieldName, new BsonInt64(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final double number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        maxDoc.append(fieldName, new BsonDouble(number));
        return this;
    }

    public UpdateBuilder max(final String fieldName, final Decimal128 number) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("number", number);
        maxDoc.append(fieldName, new BsonDecimal128(number));
        return this;
    }

    public UpdateBuilder currentDate(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        currentDateDoc.append(fieldName, new BsonBoolean(true));
        return this;
    }

    public UpdateBuilder currentTimestamp(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        currentDateDoc.append(fieldName, new BsonDocument("$type", new BsonString("timestamp")));
        return this;
    }

    public UpdateBuilder addToSet(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        notNull("value", value);
        addToSetDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder addEachToSet(final String fieldName, final List<BsonValue> values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        addToSetDoc.append(fieldName, new BsonArray(values));
        return this;
    }

    public UpdateBuilder push(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        pushDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder pushEach(final String fieldName, final List<BsonValue> values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        pushDoc.append(fieldName, new BsonDocument("$each", new BsonArray(values)));
        return this;
    }

    public UpdateBuilder pushEach(final String fieldName, final List<BsonValue> values, final PushOptions options) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
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
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        pullDoc.append(fieldName, value);
        return this;
    }

    public UpdateBuilder pullAll(final String fieldName, final List<BsonValue> values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        pullAllDoc.append(fieldName, new BsonArray(values));
        return this;
    }

    public UpdateBuilder popFirst(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        popDoc.append(fieldName, new BsonInt32(1));
        return this;
    }

    public UpdateBuilder popLast(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        popDoc.append(fieldName, new BsonInt32(-1));
        return this;
    }

    public UpdateBuilder bitwiseAnd(final String fieldName, final int value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$and", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseAnd(final String fieldName, final long value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$and", new BsonInt64(value)));
        return this;
    }

    public UpdateBuilder bitwiseOr(final String fieldName, final int value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$or", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseOr(final String fieldName, final long value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$or", new BsonInt64(value)));
        return this;
    }

    public UpdateBuilder bitwiseXor(final String fieldName, final int value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$xor", new BsonInt32(value)));
        return this;
    }

    public UpdateBuilder bitwiseXor(final String fieldName, final long value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        bitwiseDoc.append(fieldName, new BsonDocument("$xor", new BsonInt64(value)));
        return this;
    }

    @Override
    public UpdateBuilder clear() {
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
        return (UpdateBuilder) super.clear();
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$unset", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);
        addIfNotEmpty("$set", setDoc);

        //todo 生成doc

        return super.toBsonDocument(tDocumentClass, codecRegistry);
    }

    private void clearIfNotNull(BsonDocument doc) {
        if (doc != null)
            doc.clear();
    }

    private void addIfNotEmpty(String operator, BsonDocument doc) {
        if (!doc.isEmpty())
            targetDoc.append(operator, doc);
    }

}
