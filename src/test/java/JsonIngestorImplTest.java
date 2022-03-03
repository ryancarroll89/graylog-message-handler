import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonIngestorImplTest extends TestCase {

    @Test
    public void testReadFile() {
        JsonIngestorImpl jsonIngestor = new JsonIngestorImpl();

        // Mock a BufferedReader to pass into jsonIngestor readFile()
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        try {
            Mockito.when(bufferedReader.readLine()).thenReturn("{\"short_message\":\"Hello there\", \"host\":\"example.org\", \"facility\":\"test\", \"_foo\":\"bar\"}", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = jsonIngestor.readFile(bufferedReader);

        // Manually create expected output object
        JSONObject jsonObject = new JSONObject("{\"short_message\":\"Hello there\", \"host\":\"example.org\", \"facility\":\"test\", \"_foo\":\"bar\"}");

        // Check if the readFile output matches the expected JSON
        assertEquals(jsonArray.get(0).toString(), jsonObject.toString());
    }
}