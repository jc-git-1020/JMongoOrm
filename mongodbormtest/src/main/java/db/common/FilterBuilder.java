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

    public <TItem> FilterBuilder eq(final String fieldName, final TItem value) {
        if (!checkClass(value))
            throw new RuntimeException("目前不支持该类型");
        doc.append(fieldName, (BsonValue) value);
        return this;
    }

    public <TItem> FilterBuilder ne(final String fieldName, final TItem value) {
        if (!checkClass(value))
            throw new RuntimeException("目前不支持该类型");
        BsonDocument temp = new BsonDocument();
        temp.append("$ne", (BsonValue) value);
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

    private <TItem> boolean checkClass(final TItem value) {

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

