package db.common;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.LinkedList;

public class FilterBuilder extends LinkedList<Bson> implements Bson {

    public <TItem> FilterBuilder eq(final String fieldName, final TItem value){
        this.add(eq(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder ne(final String fieldName, final TItem value){
        this.add(ne(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder gt(final String fieldName, final TItem value){
        this.add(gt(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder lt(final String fieldName, final TItem value){
        this.add(lt(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder gte(final String fieldName, final TItem value){
        this.add(gte(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder lte(final String fieldName, final TItem value){
        this.add(lte(fieldName,value));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final TItem... values){
        this.add(in(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder in(final String fieldName, final Iterable<TItem> values){
        this.add(in(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final TItem... values){
        this.add(nin(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder nin(final String fieldName, final Iterable<TItem> values){
        this.add(nin(fieldName, values));
        return this;
    }

    public <TItem> FilterBuilder and(final Iterable<Bson> filters){
        this.add(and(filters));
        return this;
    }

    public <TItem> FilterBuilder and(final Bson... filters){
        this.add(and(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final Iterable<Bson> filters){
        this.add(or(filters));
        return this;
    }

    public <TItem> FilterBuilder or(final Bson... filters){
        this.add(or(filters));
        return this;
    }

    public <TItem> FilterBuilder not(final Bson filter){
        this.add(not(filter));
        return this;
    }

    public <TItem> FilterBuilder not(final Bson... filters){
        this.add(not(filters));
        return this;
    }

    public <TItem> FilterBuilder not(final Iterable<Bson> filters){
        this.add(not(filters));
        return this;
    }


    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {



        return null;
    }
}

