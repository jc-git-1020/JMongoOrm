package db.model.all_types_model;

import db.core.Model;
import org.bson.Document;


public class All_Types_Model_ObjectField extends Model {

    private String Key;

    public All_Types_Model_ObjectField() {
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("Key",Key);
        return doc;
    }

}
