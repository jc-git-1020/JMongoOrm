package factory;

import org.bson.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Codec {

    public void work() {
        File f = new File("defind.json");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = Document.parse(json);

        Set<Map.Entry<String, Object>> entrySet = doc.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();

        }

    }

}
