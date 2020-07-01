package helpers;


import utils.LogHelper;
import utils.VerificationHelper;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.json.JSONObject;*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class RestHelper extends BaseConfiguration {
    private static final Logger log = LogManager.getLogger(helpers.RestHelper.class.getName());
    Response response;
    public Response callRestApi(String apiURI, Map<String, String> requestHeaders, String requestBody, String contentType, String apiAction, Map<String, String> parameters) {
        //oAuth2token=getTokenOAuthTwoForEquiFax(null,null,null,null,null);
        // Map<String, Object> responseMap = new HashMap<>();
        //String endpoint = setEndpoint(resource);
        String URI = apiURI;
        Response response = null;
        RequestSpecification request;
        //    boolean thrown = false;
        try {

            /*RestAssured.baseURI = URI;
            if(MapUtils.isNotEmpty(requestHeaders)){
                request = RestAssured.given()
                        .headers(requestHeaders).contentType(contentType)
                        .log().all();
            } else{
                //noinspection UnusedAssignment
                request = RestAssured.given()
                        .headers(
                                "Authorization","Bearer " + equiFaxOAuth2token
                                //        ,"Accept",ContentType.JSON
                        )
                        .log().all();
                request = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .log().all();

            }*/
            request = RestAssured.given()
                    .contentType(ContentType.JSON)
                 //   .headers("Authorization", "Bearer " + equiFaxOAuth2token, "Accept", ContentType.JSON)
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
            // JsonPath jsonPathValidator = response.jsonPath();
            //LogHelper.info(log, jsonPathValidator.get("organisationNumber"));
            LogHelper.info(log,"Response Body - " + response.prettyPrint());
            LogHelper.info(log,"Response Body - " + response.getBody().toString());
            // LogHelper.info(log,"Response Header msgid - " + response.header("msgid"));
            LogHelper.info(log, "============================= RESPONSE END ===============================================");
            return response;

        } catch (Throwable t) {
            //    thrown = true;
            String AssertMessage = "EXCEPTION Occurred in callEquiFaxAPIwithOauth2Token method. " + t.getMessage();
            VerificationHelper.FailVerification(AssertMessage);
            //assertFalse(AssertMessage,thrown);
            LogHelper.error(log, "STACKTRACE Exception occurred in callEquiFaxAPIwithOauth2Token ---- " + Arrays.toString(t.getStackTrace()));
            return response;
        }
    }


}
