package db.core;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public abstract class Builder implements Bson {

    BsonDocument document;

    public Builder() {
        document = new BsonDocument();
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return document;
    }

    public Builder clear() {
        document.clear();
        return this;
    }

    public String toJson() {
        return document.toJson();
    }
}
