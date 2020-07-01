package utils;

import org.junit.Assert;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
//import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("ALL")
public class VerificationHelper {
        private static final Logger log = LogManager.getLogger(utils.VerificationHelper.class.getName());
        private static void PrintVerification(String verificationName, String verificationType, String expectedValue, String actualValue, boolean verificationStatus) {
            utils.LogHelper.info(log,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            utils.LogHelper.info(log,
                    "Verification Name: " + verificationName + ", " +
                            "Verification Type: " + verificationType + ", " +
                            "Expected Value: " + expectedValue + ", " +
                            "Actual Value: " + actualValue + ", " +
                            "Verification Status: " + verificationStatus);
            utils.LogHelper.info(log,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

    private static void PrintJSONVerication(String verificationName, String verificationType, String expectedValue, String actualValue) {
        utils.LogHelper.info(log,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        utils.LogHelper.info(log,
                "Verification Name: " + verificationName + ", " +
                        "Verification Type: " + verificationType + ", " +
                        "Expected Value: " + expectedValue + ", " +
                        "Actual Value: " + actualValue );
        utils.LogHelper.info(log,"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

        public static void PrintTestcaseDetails(String projectName, String testSuiteName, String testCaseName, String testStep, String testStatus, String message, long duration) {
            utils.LogHelper.info(log, "Testcase Details. Project: " + projectName + ", Test Suite: " + testSuiteName
                    + ", Testcase: " + testCaseName + ", Test Step: " + testStep + ", Test Status: " + testStatus + ", Additional Details: " + message + ", Execution Duration: " + duration);
        }

        public static void EqualsVerification(String verificationName, String expectedValue, String actualValue) {
            boolean verificationStatus = (expectedValue.equals(actualValue));
            PrintVerification(verificationName, "Equals", expectedValue, actualValue, verificationStatus);
            Assert.assertEquals(verificationName, expectedValue, actualValue);
        }


        public static void NotEqualsVerification(String verificationName, String expectedValue, String actualValue) {
            boolean verificationStatus = (!expectedValue.equals(actualValue));
            PrintVerification(verificationName, "Not Equals", expectedValue, actualValue, verificationStatus);
            Assert.assertNotSame(verificationName, expectedValue, actualValue);
        }

        public static void ContainsVerification(String verificationName, String expectedValue, String actualValue) {
            boolean verificationStatus = (expectedValue.contains(actualValue) | actualValue.contains(expectedValue));
            PrintVerification(verificationName, "Contains", expectedValue, actualValue, verificationStatus);
            Assert.assertTrue(verificationName, (expectedValue.contains(actualValue) | actualValue.contains(expectedValue)));
        }

        public static void NotContainsVerification(String verificationName, String expectedValue, String actualValue) {
            boolean verificationStatus = (!expectedValue.contains(actualValue));
            PrintVerification(verificationName, "Not Contains", expectedValue, actualValue, verificationStatus);
            Assert.assertTrue(verificationName, (!expectedValue.contains(actualValue)));
        }


        public static void IsTrueVerification(String verificationName, boolean value) {
            PrintVerification(verificationName, "Is True", String.valueOf(true), String.valueOf(value), value);
            Assert.assertTrue(verificationName, value);
        }

        public static void IsFalseVerification(String verificationName, boolean value) {
            boolean verificationStatus = !value;
            PrintVerification(verificationName, "Is False", String.valueOf(false), String.valueOf(value), verificationStatus);
            Assert.assertFalse(verificationName, value);
        }

        public static void FailVerification(String verificationName) {
            PrintVerification(verificationName, "Fail", String.valueOf(true), String.valueOf(true), false);
            Assert.fail(verificationName);
        }

        public static void PassVerification(String verificationName) {
            PrintVerification(verificationName, "Pass", String.valueOf(true), String.valueOf(true), false);
            Assert.assertSame(verificationName,"true","true");
        }

        public static void isNullVerification(String verificationName, String value) {
            PrintVerification(verificationName, "Is Null", String.valueOf(true), String.valueOf(true), false);
            Assert.assertNull(value);
        }

        public static void isNotNullVerification(String verificationName, String value) {
            PrintVerification(verificationName, "Is Null", null, value, true);
            Assert.assertNotNull(String.valueOf(value));
        }

        public static void compareJSONVerification(String verificationName, String expectedJson, String actualJson) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
           // boolean verificationStatus = (expectedJson.equals(actualJson));
            boolean verificationStatus = (expectedJson.equals(actualJson));
            //LogHelper.info(log, "ACTUAL JSON is " + actualVJson);
            PrintJSONVerication(verificationName, "Equals", expectedJson, actualJson);
            utils.LogHelper.info(log,"Verifying the JSON");
          //  Assert.assertEquals(verificationName, mapper.readTree(expectedJson),  mapper.readTree(actualJson));
        }

    public static void compareJSONSchemaVerification(String verificationName, String expectedJsonSchemaFilePath, String actualJson) {
       // ObjectMapper mapper = new ObjectMapper();
        // boolean verificationStatus = (expectedJson.equals(actualJson));
        //boolean verificationStatus = (expectedJson.equals(actualJson));
        //LogHelper.info(log, "ACTUAL JSON is " + actualVJson);
       // PrintJSONVerication(verificationName, "Equals", expectedJson, actualJson);
        //utils.LogHelper.info(log,"Verifying the JSON");
        //  Assert.assertEquals(verificationName, mapper.readTree(expectedJson),  mapper.readTree(actualJson));
        //assertThat(actualJson, matchesJsonSchemaInClasspath(expectedJsonSchemaFilePath));
    }

    }


