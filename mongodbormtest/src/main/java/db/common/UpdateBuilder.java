package db.common;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public class UpdateBuilder implements Bson {

    BsonDocument doc;
    BsonArray setArr;

    public UpdateBuilder() {
        doc = new BsonDocument();
        setArr = new BsonArray();
    }

    public UpdateBuilder set(final String fieldName, final BsonValue value){
        setArr.add(getSetUpdate(fieldName,value));
        return this;
    }



    public BsonDocument getSetUpdate(final String fieldName, final BsonValue value){
        BsonDocument update = new BsonDocument();
        update.append(fieldName,value);
        return update;
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return doc;
    }
}
