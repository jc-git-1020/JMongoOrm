package db.core;

import org.bson.BsonInt32;

public class ProjectBuilder extends Builder {

    public ProjectBuilder project(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        document.append(fieldName, new BsonInt32(1));
        return this;
    }

    public ProjectBuilder exclude(final String fieldName) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        document.append(fieldName, new BsonInt32(0));
        return this;
    }

}
