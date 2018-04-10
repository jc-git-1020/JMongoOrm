package db.common;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public class SortBuilder implements Bson {

    BsonDocument doc;

    public SortBuilder() {
        doc = new BsonDocument();
    }

    public SortBuilder ascending(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        doc.append(fieldName, new BsonInt32(1));
        return this;
    }

    public SortBuilder descending(final String fieldName) {
        Utilities.stringNotNullOrEmpty("fieldName", fieldName);
        doc.append(fieldName, new BsonInt32(-1));
        return this;
    }

    public SortBuilder clear() {
        doc.clear();
        return this;
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
