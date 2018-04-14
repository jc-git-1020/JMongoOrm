package db.model.all_types_model;

import db.core.Model;
import org.bson.Document;

public class All_Types_Model_Item implements Model {
    private String key;

    public All_Types_Model_Item() {
    }

    public All_Types_Model_Item(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Document toDocument() {
        return null;
    }
}
