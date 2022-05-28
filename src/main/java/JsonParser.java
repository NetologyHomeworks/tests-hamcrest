import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    JsonParser() {
    }

    String readJsonFromFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufRead = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = bufRead.readLine()) != null) {
                result.append(str).append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return result.toString();
    }

    boolean writeJsonToFile(String jsonData, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonData);
            file.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    List<Object> jsonToObjectList(String jsonData, Object myObject) {
        List<Object> result = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonData);
            Gson gson = new GsonBuilder().create();
            for (Object obj : jsonArray) {
                result.add(gson.fromJson(obj.toString(), myObject.getClass()));
            }
        } catch (ParseException | UnsupportedOperationException | NullPointerException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return result;
    }

    String listObjectToJson(List<Object> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list, new TypeToken<List<Object>>() {}.getType());
    }
}
