package context;

import io.restassured.response.Response;

import java.util.HashMap;

public class TestContext {

    private HashMap<Object, Object> sharedData;
    Response contextResponse;
    public TestContext(){sharedData = new HashMap<>();}
    public Response getResponse() {
        return contextResponse;
    }
    public void setResponse(Response response) {
        contextResponse=response;
    }

    public HashMap<Object, Object> getSharedData() {
        return sharedData;
    }
    public void addSharedData(Object key, Object value) {
        sharedData.put(key, value);
    }
 }
