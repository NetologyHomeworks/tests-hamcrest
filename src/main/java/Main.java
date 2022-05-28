import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        String json = jsonParser.listObjectToJson(Arrays.asList(
                new Employee(1, "Izzy", "Ham", "ISR", 35),
                new Employee(2, "Alex", "Barnyshev", "RU", 48)));
        jsonParser.writeJsonToFile(json, "test.json");
    }
}
