package helpers;


import utils.LogHelper;
import utils.VerificationHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Map;

public class RestHelper extends BaseConfiguration {
    private static final Logger log = LogManager.getLogger(helpers.RestHelper.class.getName());
    Response response;
    public Response callRestApi(String apiURI, Map<String, String> requestHeaders, String requestBody, String contentType, String apiAction, Map<String, String> parameters) {
        String URI = apiURI;
        Response response = null;
        RequestSpecification request;
        try {
            request = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .log().all();

            switch (apiAction.trim().toLowerCase()) {
                case "get": {
                    if (parameters != null) {
                        response = request.queryParams(parameters).get(URI);
                    } else
                        response = request.get(URI);
                    break;
                }
                case "post": {
                    response = request.body(requestBody).post(URI);
                    break;
                }
                case "put": {
                    response = request.body(requestBody).put();
                    break;
                }
                case "delete": {
                    response = request.delete();
                    break;
                }
                default: {
                    response = request.get();
                }
            }
            LogHelper.info(log, "============================= RESPONSE START ===============================================");
            LogHelper.info(log, "Response Status Code is - " + response.getStatusCode());
            LogHelper.info(log, "Response Body - " + response.asString());
            LogHelper.info(log,"Response Body - " + response.prettyPrint());
            LogHelper.info(log,"Response Body - " + response.getBody().toString());
            LogHelper.info(log, "============================= RESPONSE END ===============================================");
            return response;

        } catch (Throwable t) {
            String AssertMessage = "EXCEPTION Occurred in callRestApi method. " + t.getMessage();
          //  VerificationHelper.FailVerification(AssertMessage);
            LogHelper.error(log, "STACKTRACE Exception occurred in callRestApi ---- " + Arrays.toString(t.getStackTrace()));
            return response;
        }
    }


}
