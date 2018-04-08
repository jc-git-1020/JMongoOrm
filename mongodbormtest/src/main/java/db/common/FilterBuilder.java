package db.common;

import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.LinkedList;

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
        BsonDocument temp = new BsonDocument();
        temp.append("$ne", value);
        doc.append(fieldName, temp);
        return this;
    }

    public <TItem> FilterBuilder gt(final String fieldName, final TItem value) {
        this.add(Filters.gt(fieldName, value));
        return this;
    }

    public <TItem> FilterBuilder lt(final String fieldName, final TItem value) {
        this.add(Filters.lt(fieldName, value));
        return this;
    }

    public <TItem> FilterBuilder gte(final String fieldName, final TItem value) {
        this.add(Filters.gte(fieldName, value));
        return this;
    }

    public <TItem> FilterBuilder lte(final String fieldName, final TItem value) {
        this.add(Filters.lte(fieldName, value));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final TItem... values) {
        this.add(Filters.in(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final Iterable<TItem> values) {
        this.add(Filters.in(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final TItem... values) {
        this.add(Filters.nin(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final Iterable<TItem> values) {
        this.add(Filters.nin(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder and(final Iterable<Bson> filters) {
        this.add(Filters.and(filters));
        return this;
    }

    public <TItem> FilterBuilder and(final Bson... filters) {
        this.add(Filters.and(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final Iterable<Bson> filters) {
        this.add(Filters.or(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final Bson... filters) {
        this.add(Filters.or(filters));
        return this;
    }

    public <TItem> FilterBuilder not(final Bson filter) {
        this.add(Filters.not(filter));
        return this;
    }

    public <TItem> FilterBuilder nor(final Bson... filters) {
        this.add(Filters.nor(filters));
        return this;
    }

    public <TItem> FilterBuilder nor(final Iterable<Bson> filters) {
        this.add(Filters.nor(filters));
        return this;
    }

    //参考BsonValueCodecProvider
    private <TItem> boolean checkClass(final TItem value) {

        //BsonType.UNDEFINED
        //参考BsonValueCodecProvider
        Class c = value.getClass();
        if (c == BsonString.class) {

        } else if (c == BsonInt32.class) {

        } else {

        }


        return false;
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return doc;
    }
}

