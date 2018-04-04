package db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DaoHelper {

    private static String host = "localhost";
    private static String databaseName = "test";

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static MongoClient mongoClient = new MongoClient(host, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    private static MongoDatabase database = mongoClient.getDatabase(databaseName);


    public static MongoCollection getCollection(String name,Class c) {
        return database.getCollection(name,c);
    }



}
