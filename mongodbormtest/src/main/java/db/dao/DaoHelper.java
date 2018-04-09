package db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//todo 参考PojoCodecProvider
public final class DaoHelper {

    private final static String host = "localhost";
    private final static String databaseName = "test";

    private final static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private final static MongoClient mongoClient = new MongoClient(host, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    private final static MongoDatabase database = mongoClient.getDatabase(databaseName);


    public static MongoCollection getCollection(String name,Class c) {
        return database.getCollection(name,c);
    }



}
