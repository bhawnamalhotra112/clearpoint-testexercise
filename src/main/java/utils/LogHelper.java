package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {



        private static final Logger Log = LogManager.getLogger(utils.LogHelper.class.getName());

        public static void startTestCase(String sTestCaseName) {
            info(Log, "=========================================== TEST CASE STARTS ===========================================");
            info(Log, sTestCaseName.toUpperCase());
            info(Log, "========================================================================================================");
        }

        public static void endTestCase(String sTestCaseName) {
            info(Log, "=========================================== TEST CASE ENDS =============================================");
            info(Log, sTestCaseName.toUpperCase());
            info(Log, "========================================================================================================");
        }



        public static void info(Logger Log, String message) {

            Log.info(message);
        }

        @SuppressWarnings("unused")
        public static void debug(Logger Log, String message) {

            Log.debug(message);
        }

        public static void error(Logger Log, String message) {

            Log.error(message);
        }

}
