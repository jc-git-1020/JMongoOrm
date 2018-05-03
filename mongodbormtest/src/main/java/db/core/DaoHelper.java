package db.core;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class DaoHelper {
    private final static MongoClient mongoClient = new MongoClient(Config.HOST);
    private final static MongoDatabase database = mongoClient.getDatabase(Config.DATABASE_NAME);

    public static MongoCollection getCollection(String name) {
        return database.getCollection(name);
    }
}
