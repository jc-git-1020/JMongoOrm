package factory;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.Code;
import org.bson.types.CodeWithScope;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Parser {

    private String fieldTemplate;
    private String setterGetterTemplate;
    private StringBuilder fieldSb = new StringBuilder();
    private StringBuilder methodsSb = new StringBuilder();
    private StringBuilder toDocumentSb = new StringBuilder();
    private Map<String, Class> typesMap = new HashMap<>();

    public Parser() {
        //加载模板
        File f;
        FileInputStream fip;
        InputStreamReader reader;
        try {
            f = new File("FieldTemplate");
            fip = new FileInputStream(f);
            reader = new InputStreamReader(fip, "UTF-8");
            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            fieldTemplate = sb.toString();
            sb.delete(0, sb.length() - 1);
            reader.close();

            f = new File("setterGetterTemplate");
            fip = new FileInputStream(f);
            reader = new InputStreamReader(fip, "UTF-8");
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            setterGetterTemplate = sb.toString();
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        //初始化字段类型映射
        typesMap.put("Code", String.class);
        typesMap.put("String", Code.class);
        typesMap.put("ArrayList", ArrayList.class);
        typesMap.put("Binary", Binary.class);
        typesMap.put("CodeWithScope", CodeWithScope.class);
        typesMap.put("Date", Date.class);
    }

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

            int i = 0;
        }
    }

    private void parseField(String field, Object value) {
        if (value instanceof String) {
            if (!typesMap.containsKey(value))
                throw new RuntimeException("该字段类型不支持，请参考相关文档");
            String temp = fieldTemplate
                    .replace("字段类型", (String) value)
                    .replace("字段名", field)
                    .replace("注释", field);
            if (notNullOrEmpty(temp)) fieldSb.append(temp);
        } else if (value instanceof Document) {
            Document doc = (Document) value;
            String annotation;
            if (doc.containsKey("annotation")) annotation = doc.getString("annotation");
            else annotation = field;
            notNullOrEmpty(annotation);
            if (!doc.containsKey("type")) throw new RuntimeException("没有指定字段类型，请参考相关文档");
            String type = doc.getString("type");
            notNullOrEmpty(type);
            if(type == "Object"){

            }else if(type == "ArrayField"){

            }else{

            }
        }
    }

    private boolean notNullOrEmpty(String value) {
        if (value != null && value.length() > 0) return true;
        throw new RuntimeException("字段值不能为空，请参考相关文档");
    }

}
