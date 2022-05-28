import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonParserTests {
    JsonParser sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new JsonParser();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testReadJsonResultStringForEmployee() {
        String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Izzy\",\n" +
                "    \"lastName\": \"Ham\",\n" +
                "    \"country\": \"ISR\",\n" +
                "    \"age\": 35\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Alex\",\n" +
                "    \"lastName\": \"Barnyshev\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 48\n" +
                "  }\n" +
                "]\n";

        assertThat(sut.readJsonFromFile("test.json"), is(expected));
    }

    @Test
    public void testReadJsonResultStringWrongFile() {
        assertThat(sut.readJsonFromFile("data.json"), nullValue());
    }

    @Test
    public void testJsonToObjectListForEmployee() {
        List<Object> list = sut.jsonToObjectList(sut.readJsonFromFile("test.json"), new Employee());
        var expected = "Employee{id=1, firstName='Izzy', lastName='Ham', country='ISR', age=35}\n" +
                "Employee{id=2, firstName='Alex', lastName='Barnyshev', country='RU', age=48}\n";
        var result = list.stream().map(obj -> (Employee) obj).map(item -> item.toString() + "\n").collect(Collectors.joining());
        assertThat(result, is(expected));
    }

    @Test
    public void testJsonToObjectListWithWrongClass() {
        assertThat(sut.jsonToObjectList(sut.readJsonFromFile("test.json"), Employee.class), nullValue());
    }

    @Test
    public void testJsonToObjectListWithWrongFile() {
        assertThat(sut.jsonToObjectList(sut.readJsonFromFile("data.json"), new Employee()), nullValue());
    }

    @Test
    public void testJsonToObjectListWithWrongFileAndClass() {
        assertThat(sut.jsonToObjectList(sut.readJsonFromFile("data.json"), Employee.class), nullValue());
    }

    @Test
    public void testReadJsonFromFileContainsId() {
        assertThat(sut.readJsonFromFile("test.json"), containsString("id"));
    }

    @Test
    public void testJsonParserGivenClassCorrect() {
        assertThat(sut, instanceOf(JsonParser.class));
    }
}
