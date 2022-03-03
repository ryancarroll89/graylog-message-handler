import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

public class GelfFormatterImplTest extends TestCase {

    @Test
    public void testAppendPrefixes() {
        // Create unformatted JSON object
        JSONObject jsonObject = new JSONObject("{\"short_message\":\"Hello there\", \"host\":\"example.org\", \"facility\":\"test\", \"foo\":\"bar\"}");
        // Manually create formatted JSON object
        JSONObject jsonObjectWithManualPrefixes = new JSONObject("{\"_short_message\":\"Hello there\", \"_host\":\"example.org\", \"_facility\":\"test\", \"_foo\":\"bar\"}");

        // Use GelfFormatterImpl to format unformatted JSON object
        GelfFormatterImpl gelfFormatter = new GelfFormatterImpl();
        gelfFormatter.appendPrefixes(jsonObject);

        // Check if formatted and manual JSON objects are the same
        assertEquals(jsonObject.toString(), jsonObjectWithManualPrefixes.toString());
    }

    @Test
    public void testAddGelfFields() {
        // Create unformatted JSON object
        JSONObject jsonObject = new JSONObject("{\"facility\":\"test\", \"foo\":\"bar\"}");
        // Manually create formatted JSON object
        JSONObject jsonObjectWithManualPrefixes = new JSONObject("{\"version\":\"1.1\", \"host\":\"example.org\", \"short_message\":\"A short message that helps you identify what is going on 0\"," +
                " \"full_message\":\"Backtrace here\\\\n\\\\nmore stuff\", \"timestamp\":\"1\", \"level\":\"1\", \"facility\":\"test\", \"foo\":\"bar\"}");

        // Use GelfFormatterImpl to format unformatted JSON object
        GelfFormatterImpl gelfFormatter = new GelfFormatterImpl();
        gelfFormatter.addGelfFields(jsonObject, 0);

        // Check if formatted and manual JSON objects are the same
        assertEquals(jsonObject.keySet(), jsonObjectWithManualPrefixes.keySet());
    }
}