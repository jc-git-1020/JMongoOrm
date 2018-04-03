package db.dao;

public class PersonDao {

    private static String collectionName = "person";

    public void create(){}

    public void update(){}

    public void remove(){}

    public void find(){}

    public void drop(){
        DaoHelper.getCollection(collectionName).drop();
    }

}
