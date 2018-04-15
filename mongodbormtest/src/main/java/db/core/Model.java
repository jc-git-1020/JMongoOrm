package db.core;

import org.bson.Document;

import java.util.ArrayList;

public abstract class Model {

    public Model() {
    }

    public abstract Document toDocument();

    public String toJson(){
        return toDocument().toJson();
    }

    protected <E extends Model> ArrayList<Document> models2Documents(ArrayList<E> models){
        ArrayList<Document> list = new ArrayList<>();
        models.forEach(model -> list.add(model.toDocument()));
        return list;
    }
}
