package db.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//todo 参考PojoCodecProvider
public final class DaoHelper {

    private final static String host = "localhost";
    private final static String databaseName = "test";

    private final static MongoClient mongoClient = new MongoClient(host);
    private final static MongoDatabase database = mongoClient.getDatabase(databaseName);


    public static MongoCollection getCollection(String name) {
        return database.getCollection(name);
    }


}
