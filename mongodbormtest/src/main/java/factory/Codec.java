package factory;

import org.bson.BsonBinaryReader;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.Document;
import org.bson.io.BsonInput;
import org.bson.io.ByteBufferBsonInput;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Codec {

    public void work(){
        File f = new File("a.txt");
        FileInputStream fip = null;
        InputStreamReader reader = null;
        String json = "";
        try {
            fip = new FileInputStream(f);
            reader = new InputStreamReader(fip, "UTF-8");
            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append((char) reader.read());
                // 转成char加到StringBuffer对象中
            }
            json = sb.toString();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc =  Document.parse(json);

    }

}
