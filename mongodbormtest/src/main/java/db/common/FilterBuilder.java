package db.common;

import org.bson.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.LinkedList;

import static java.util.Arrays.asList;

public class FilterBuilder extends LinkedList<Bson> implements Bson {

    BsonDocument doc;

    public FilterBuilder() {
        doc = new BsonDocument();
    }

    public FilterBuilder eq(final String fieldName, final BsonValue value) {
        doc.append(fieldName, value);
        return this;
    }

    public FilterBuilder ne(final String fieldName, final BsonValue value) {
        doc.append(fieldName, getOperatorFilter("$ne",value));
        return this;
    }

    public <TItem> FilterBuilder gt(final String fieldName, final BsonValue value) {
        doc.append(fieldName, getOperatorFilter("$gt",value));
        return this;
    }

    public <TItem> FilterBuilder lt(final String fieldName, final BsonValue value) {
        doc.append(fieldName, getOperatorFilter("$lt",value));
        return this;
    }

    public <TItem> FilterBuilder gte(final String fieldName, final BsonValue value) {
        doc.append(fieldName, getOperatorFilter("$gte",value));
        return this;
    }

    public <TItem> FilterBuilder lte(final String fieldName, final BsonValue value) {
        doc.append(fieldName, getOperatorFilter("$lte",value));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final BsonValue... values) {
        doc.append(fieldName, getOperatorFilter("$in",values));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final Iterable<BsonValue> values) {
        doc.append(fieldName, getOperatorFilter("$in",values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final BsonValue... values) {
        doc.append(fieldName, getOperatorFilter("$nin",values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final Iterable<BsonValue> values) {
        doc.append(fieldName, getOperatorFilter("$nin",values));
        return this;
    }

    public <TItem> FilterBuilder and(final Iterable<BsonValue> filters) {
        doc.append("$and", toBsonArray(filters));
        return this;
    }

    public <TItem> FilterBuilder and(final BsonValue... filters) {
        doc.append("$and", toBsonArray(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final Iterable<BsonValue> filters) {
        doc.append("$or", toBsonArray(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final BsonValue... filters) {
        doc.append("$or", toBsonArray(filters));
        return this;
    }

    public <TItem> FilterBuilder not(final BsonValue filter) {
        doc.append("$not", filter);
        return this;
    }

    public <TItem> FilterBuilder nor(final BsonValue... filters) {
        doc.append("$nor", toBsonArray(filters));
        return this;
    }

    public <TItem> FilterBuilder nor(final Iterable<BsonValue> filters) {
        doc.append("$nor", toBsonArray(filters));
        return this;
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
}

