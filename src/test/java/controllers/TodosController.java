package controllers;
import helpers.BaseConfiguration;
import helpers.CommonHelper;
import helpers.RestHelper;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LogHelper;
import java.io.FileReader;

public class TodosController extends BaseConfiguration {
    private static final Logger log = LogManager.getLogger(TodosController.class.getName());
    RestHelper restHelper = new RestHelper();
   // CommonHelper commonHelper = new CommonHelper();
    BaseConfiguration baseConfiguration = new BaseConfiguration();

    public Response getResponseOfRestAPI(String requestOpertation, String resource) {
        Response response;
        String URI;
        LogHelper.info(log, "In getToDoApi");
        URI = getApplicationProperty("api.endpoint") + resource;
        response = restHelper.callRestApi(URI, null, null, null, requestOpertation, null);
        return response;
    }


    public Response posttoDo(String resource, String postJsonObject, String randomOrderId) {
        Response response;
        String URI;
        LogHelper.info(log, "In getToDoApi");
        URI = getApplicationProperty("api.endpoint") + resource;
        String inputJsonSchemaFilePath = baseConfiguration.getJsonInputFilesPath(postJsonObject);
        String updatedJsonBody = modifyJsonBody(inputJsonSchemaFilePath, "id", String.valueOf(randomOrderId));
        response = restHelper.callRestApi(URI, null, updatedJsonBody, null, "POST", null);
        return response;
    }

    public Response puttoDo(String resource, String postJsonObject) {
        Response response;
        String URI;
        LogHelper.info(log, "In putToDo");
        URI = getApplicationProperty("api.endpoint") + resource;
        String inputJsonSchemaFilePath = baseConfiguration.getJsonInputFilesPath(postJsonObject);
        String JsonBody = getJsonBody(inputJsonSchemaFilePath);
        response = restHelper.callRestApi(URI, null, JsonBody, null, "PUT", null);
        return response;
    }
    public String getToDoApiJsonSchema(String requestOpertation, String resource) {
        Response response;
        String responseCode;
        String URI;

        LogHelper.info(log, "In getToDoApi");
        URI = getApplicationProperty("api.endpoint") + resource;
        response = restHelper.callRestApi(URI, null, null, null, requestOpertation, null);
        String jsonSchema = response.body().toString();
        return jsonSchema;
    }

    public String modifyJsonBody(String JSONFilePath, String nodeName, String newNodeValue) {

        Object object;
        try {
            JSONParser jsonParser = new JSONParser();
            object = jsonParser.parse(new FileReader(JSONFilePath));
            JSONObject jsonObject = (JSONObject) object;
            jsonObject.replace(nodeName, Integer.valueOf(newNodeValue));
            String json = jsonObject.toJSONString();
            System.out.println("---------------------THE NEW JSON OBJECT IS_________________");
            System.out.println(json);
            return json;

        } catch (Exception e) {
            LogHelper.error(log, "Exception in OrderAPISimplePayLoad ---- " + e.getMessage());
            System.out.println("Exception in OrderAPISimplePayLoad ---- " + e.getMessage());
            return "Exception in OrderAPISimplePayLoad";
        }

    }
    public String getJsonBody(String JSONFilePath) {

        Object object;
        try {
            JSONParser jsonParser = new JSONParser();
            object = jsonParser.parse(new FileReader(JSONFilePath));
            JSONObject jsonObject = (JSONObject) object;
            String json = jsonObject.toJSONString();
            System.out.println("---------------------THE NEW JSON OBJECT IS_________________");
            System.out.println(json);
            return json;

        } catch (Exception e) {
            LogHelper.error(log, "Exception in OrderAPISimplePayLoad ---- " + e.getMessage());
            System.out.println("Exception in OrderAPISimplePayLoad ---- " + e.getMessage());
            return "Exception in OrderAPISimplePayLoad";
        }

    }



}