package stepdefs;


import com.fasterxml.jackson.databind.ObjectMapper;
import context.TestContext;
import controllers.TodosController;
import helpers.BaseConfiguration;
import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LogHelper;
import utils.VerificationHelper;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static io.restassured.path.json.JsonPath.from;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class TodoAPIsSteps {



    private static final Logger log = LogManager.getLogger(TodoAPIsSteps.class.getName());
    TodosController todosController = new TodosController();
    TestContext context;
    CommonHelper commonHelper = new CommonHelper();
    BaseConfiguration baseConfiguration= new BaseConfiguration();

    public TodoAPIsSteps(TestContext context) {
        this.context = context;

    }


    @Given("as a user I want to add a todo")
    public void as_a_user_I_want_add_a_todo() {
        LogHelper.info(log, "Step -as a user I want to add a todo");
    }

    @Given("as a user I want to access todo Apis")
    public void as_a_user_I_want_to_access_todo_Apis() {
        LogHelper.info(log, "Step -as a user I want to access todo Apis");
    }

    @Given("as a user I want to update a todo")
    public void as_a_user_I_want_to_update_todo_Apis() {
        LogHelper.info(log, "Step -as a user I want to update a todo");
    }

    @Given("as a user I want to delete todo Apis")
    public void as_a_user_I_want_to_delete_todo_Apis() {
        LogHelper.info(log, "Step -as a user I want to delete todo Apis");
    }

    @When("I should get the above todo created")
    public void I_should_get_the_above_todo_created() {
        LogHelper.info(log, "Step -I should get the above todo created");
        Integer createdToDoId=Integer.valueOf(context.getSharedData().get("randomTodoId").toString());
        ArrayList<Integer> list= context.getResponse().then()
                .assertThat()
                .extract()
                .path("id");
        for (Integer temp : list) {
            System.out.println(temp);
            assertThat(list).contains(createdToDoId);
            }
        }

    @Then("I should get the todo updated json body (.+)")
    public void I_should_get_the_todo_updated_above(String expectedBodyJSONFile) {
        LogHelper.info(log, "Step - I should get the todo updated json body");
        LogHelper.info(log, "JSON response is ---" + context.getResponse().getBody().asString());
        JSONParser parser = new JSONParser();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String expectedJsonSchemaFilePath=baseConfiguration.getJsonInputFilesPath(expectedBodyJSONFile);
            String expectedJsonString=FileUtils.readFileToString(new File(expectedJsonSchemaFilePath), StandardCharsets.UTF_8);
            String actualJsonString=context.getResponse().getBody().asString();
            assertEquals(mapper.readTree(expectedJsonString),mapper.readTree(actualJsonString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @When("I make (.+) request to the (.+)")
    public void I_make_request_to_the_EndPoint(String apiOperation, String resource) {
        LogHelper.info(log, "Step -as a user I want to access todo Apis");
        Response response = todosController.getResponseOfRestAPI(apiOperation,resource);
        context.setResponse(response);
    }


    @When("I make POST request to resource (.+) with Body (.+)")
    public void I_make_a_POST_request_to_the_Resource_with_Body(String resource, String jsonFileName) {
        int randomTodoId = commonHelper.generateRandomNumber(210,300);
        context.addSharedData("randomTodoId",String.valueOf(randomTodoId));
        LogHelper.info(log, "Step -I make POST request to the '"+resource+"' with Body '"+jsonFileName+"'");
        Response response = todosController.posttoDo(resource,jsonFileName, String.valueOf(context.getSharedData().get("randomTodoId")));
        context.setResponse(response);

    }


    @When("I make PUT request to resource (.+) with Body (.+)")
    public void I_make_a_PUT_request_to_the_Resource_with_Body(String resource, String jsonFileName) {
        LogHelper.info(log, "Step -I make PUT request to the '" + resource + "' with Body '" + jsonFileName + "'");
        Response response = todosController.puttoDo(resource, jsonFileName);
        context.setResponse(response);
    }

    @And("I should get (.+) todos")
    public void I_should_get_todos(int expectedTodoCounts) {
        LogHelper.info(log, "Step -I should get expected todo '"+expectedTodoCounts+"'");
        String responseBodyString=context.getResponse().getBody().asString();
        assertThat(from(responseBodyString).getList("$").size()).isEqualTo(expectedTodoCounts);
    }


    @Then("I should get the response code (.+)")
    public void I_should_get_the_response_code(String expectedResponseCode) {
        LogHelper.info(log, "Step -I should get the response code '"+expectedResponseCode+"'");
        String actualResponseCode=String.valueOf(context.getResponse().statusCode());
        assertThat(expectedResponseCode.equals(actualResponseCode));
      //  VerificationHelper.EqualsVerification("Response code must equal to '"+expectedResponseCode+"' and actual response Code is '"+actualResponseCode+"'", expectedResponseCode,actualResponseCode);
    }

}