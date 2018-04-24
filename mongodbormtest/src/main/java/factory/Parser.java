package factory;

import org.bson.BsonReader;
import org.bson.BsonType;

public class Parser {

    public void test(){
        BsonReader reader  = null;
        reader.readStartDocument();
        String name = reader.readName();
        while (!name.isEmpty()){
            BsonType type = reader.getCurrentBsonType();

        }

        reader.readName();      // read the name "a"

        reader.readString();    // read string "MongoDB"
        reader.readName();      // read the name "b"
        reader.readStartArray();
        reader.readStartDocument();
        reader.readName();   // read the name "c"
        reader.readInt32();  // read the integer 1
        reader.readEndDocument();
        reader.readEndArray();
        reader.readEndDocument();

    }
}
