package db.dao;

import org.bson.conversions.Bson;

public class PersonDao {

    private static String collectionName = "person";

    public long count(){
        return 1;
    }

    public long count(Bson filter){
        return 1;
    }

    public void update(){}

    public void remove(){}

    public void find(){}

    public void drop(){
        DaoHelper.getCollection(collectionName).drop();
    }

}
