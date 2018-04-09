package db.common;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public class ProjectBuilder implements Bson {

    BsonDocument doc;

    public ProjectBuilder() {
        doc = new BsonDocument();
    }

    public ProjectBuilder project(final String fieldName) {
        doc.append(fieldName, new BsonInt32(1));
        return this;
    }

    public ProjectBuilder exclude(final String fieldName) {
        doc.append(fieldName, new BsonInt32(0));
        return this;
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return doc;
    }
}
