package db.core;

import org.bson.BsonInt32;

public class SortBuilder extends Builder {

    public SortBuilder ascending(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        document.append(fieldName, new BsonInt32(1));
        return this;
    }

    public SortBuilder descending(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        document.append(fieldName, new BsonInt32(-1));
        return this;
    }

}
