package test;


import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


/**
 * Hello world!
 *
 */
public class test
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );


    }

    public static void test2(){

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Person> collection = database.getCollection("people", Person.class);



    }



    public static void test(){

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Person> collection = database.getCollection("people", Person.class);

        // make a document and insert it
        Person ada = new Person("Ada Byron", 20, new Address(new Demo(0),"St James Square", "London", "W1"));
        collection.insertOne(ada);

        // get it (since it's the only one in there since we dropped the rest earlier on)
        Person somebody = collection.find().first();
        System.out.println(somebody);

        // now, lets add some more people so we can explore queries and cursors
        List<Person> people = asList(
                new Person("Charles Babbage", 45, new Address(new Demo(1),"5 Devonshire Street", "London", "W11")),
                new Person("Alan Turing", 28, new Address(new Demo(2),"Bletchley Hall", "Bletchley Park", "MK12")),
                new Person("Timothy Berners-Lee", 61, new Address(new Demo(3),"Colehill", "Wimborne", null))
        );

        collection.insertMany(people);
        System.out.println("total # of people " + collection.count());

        System.out.println("");
        // lets get all the documents in the collection and print them out
        Block<Person> printBlock = new Block<Person>() {
            @Override
            public void apply(final Person person) {
                System.out.println(person);
            }
        };

        collection.find().forEach(printBlock);

        System.out.println("");
        // now use a query to get 1 document out
        Document doc = new Document("_id",1);
        somebody = collection.find(eq("address.city", "Wimborne")).first();
        System.out.println(somebody);
        int t = somebody.address.demo.flag;

        System.out.println("");
        // now lets find every over 30
        collection.find(gt("age", 30)).forEach(printBlock);

        System.out.println("");
        // Update One
        collection.updateOne(eq("name", "Ada Byron"), combine(set("age", 23), set("name", "Ada Lovelace")));

        System.out.println("");
        // Update Many
        UpdateResult updateResult = collection.updateMany(not(eq("zip", null)), set("zip", null));
        System.out.println(updateResult.getModifiedCount());

        System.out.println("");
        // Replace One
        Bson s = eq("name", "Ada Lovelace");
        updateResult = collection.replaceOne(eq("name", "Ada Lovelace"), ada);
        System.out.println(updateResult.getModifiedCount());

        // Delete One
        collection.deleteOne(eq("address.city", "Wimborne"));

        // Delete Many
        DeleteResult deleteResult = collection.deleteMany(eq("address.city", "London"));
        System.out.println(deleteResult.getDeletedCount());

    }


    public static final class Person {
        private ObjectId id;
        private String name;
        private int age;
        private Address address;

        public Person() {
        }

        public Person(String name,int age,Address address){
            this.name = name;
            this.age =age;
            this.address = address;
        }

        public ObjectId getId() {
            return id;
        }

        public void setId(final ObjectId id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(final int age) {
            this.age = age;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(final Address address) {
            this.address = address;
        }

        // Rest of implementation
    }

    public static final class Address {
        private String street;

        private Demo demo;

        public Address(Demo demo,String street,  String city, String zip) {
            this.street = street;
            this.demo = demo;
            this.city = city;
            this.zip = zip;
        }

        private String city;
        private String zip;

        public Address() {
        }


        public String getStreet() {
            return street;
        }

        public void setStreet(final String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(final String city) {
            this.city = city;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(final String zip) {
            this.zip = zip;
        }

        // Rest of implementation
    }

    private static class Demo{
        public Demo(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        private int flag ;
    }

}



