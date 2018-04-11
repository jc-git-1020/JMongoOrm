package db.common;

import org.bson.Document;

public abstract class Model {

    public Model(Document doc) {

    }

    public abstract Document toDocument();

}
