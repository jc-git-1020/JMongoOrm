package db.common;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import static java.util.Arrays.asList;

public class FilterBuilder implements Bson {

    BsonDocument doc;

    public FilterBuilder() {
        doc = new BsonDocument();
    }

    public FilterBuilder eq(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, value);
        return this;
    }

    public FilterBuilder ne(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$ne",value));
        return this;
    }

    public FilterBuilder gt(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$gt",value));
        return this;
    }

    public FilterBuilder lt(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$lt",value));
        return this;
    }

    public FilterBuilder gte(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$gte",value));
        return this;
    }

    public FilterBuilder lte(final String fieldName, final BsonValue value) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$lte",value));
        return this;
    }

    public FilterBuilder in(final String fieldName, final BsonValue... values) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$in",values));
        return this;
    }

    public FilterBuilder in(final String fieldName, final Iterable<BsonValue> values) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$in",values));
        return this;
    }

    public FilterBuilder nin(final String fieldName, final BsonValue... values) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$nin",values));
        return this;
    }

    public FilterBuilder nin(final String fieldName, final Iterable<BsonValue> values) {
        Utilities.stringNotNullOrEmpty("fieldName",fieldName);
        doc.append(fieldName, getOperatorFilter("$nin",values));
        return this;
    }

    public FilterBuilder and(final Iterable<BsonValue> filters) {
        doc.append("$and", toBsonArray(filters));
        return this;
    }

    public FilterBuilder and(final BsonValue... filters) {
        doc.append("$and", toBsonArray(filters));
        return this;
    }

    public FilterBuilder or(final Iterable<BsonValue> filters) {
        doc.append("$or", toBsonArray(filters));
        return this;
    }

    public FilterBuilder or(final BsonValue... filters) {
        doc.append("$or", toBsonArray(filters));
        return this;
    }

    public FilterBuilder not(final BsonValue filter) {
        doc.append("$not", filter);
        return this;
    }

    public FilterBuilder nor(final BsonValue... filters) {
        doc.append("$nor", toBsonArray(filters));
        return this;
    }

    public FilterBuilder nor(final Iterable<BsonValue> filters) {
        doc.append("$nor", toBsonArray(filters));
        return this;
    }

    public FilterBuilder clear(){
        doc.clear();
        return this;
    }

    private void notNullOrEmpty(final String fieldName){
        if(fieldName == null || fieldName.isEmpty() )
            throw new IllegalArgumentException("fieldName can not be null or empty");
    }

    private BsonDocument getOperatorFilter(final String operatorName, final BsonValue value){
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, value);
        return doc;
    }

    private BsonDocument getOperatorFilter(final String operatorName, final BsonValue... values){
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, toBsonArray(values));
        return doc;
    }

    private BsonDocument getOperatorFilter(final String operatorName, final Iterable<BsonValue> values){
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, toBsonArray(values));
        return doc;
    }



    private BsonArray toBsonArray(final BsonValue... values){
        return new BsonArray(asList(values));
    }

    private BsonArray toBsonArray(final Iterable<BsonValue> values){
        BsonArray array = new BsonArray();
        values.forEach(array :: add);
        return array;
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

