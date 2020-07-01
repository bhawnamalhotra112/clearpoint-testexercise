package helpers;

import utils.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class BaseConfiguration {

    private static final Logger log = LogManager.getLogger(helpers.BaseConfiguration.class.getName());
    CommonHelper commonHelper = new CommonHelper();
    public String getEnvironment(){
        try{
            LogHelper.info(log, "In getEnvironment");
            String env=null;
                String filePath = commonHelper.getOSCompatibleAbsoluteFromRelativePath("src//test//resources//configs//common.properties");
                InputStream input = new FileInputStream(filePath);
                Properties prop = new Properties();
                prop.load(input);
                env = prop.getProperty("app.env");
                LogHelper.info(log, "app.env is null so fetching the env property from common.properties ---"+ env );
                return env;
        } catch (IOException ex) {
            ex.printStackTrace();
            LogHelper.error(log, "Exception in  getEnvironment---"+ ex.getMessage() );
            return "EXCEPTION in getEnvironment";
        }

    }


    public String getApplicationProperty(String key){
        try{
            LogHelper.info(log, "In getApplicationProperty");
                String filePath = commonHelper.getOSCompatibleAbsoluteFromRelativePath("src//test//resources//configs//application-" + getEnvironment() + ".properties");
                InputStream input = new FileInputStream(filePath);
                Properties prop = new Properties();
                prop.load(input);
                return prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
            return "EXCEPTION in getApplicationProperty";
        }

    }


    public String getCommonProperties(String key){
        try{
            LogHelper.info(log, "In getCommonProperties");
            String filePath = commonHelper.getOSCompatibleAbsoluteFromRelativePath("src//test//resources//configs//common.properties");
            InputStream input = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "EXCEPTION in getCommonProperties";
        }

    }

    public String getJsonInputFilesPath(String jsonFileName){
        String filePath = commonHelper.getOSCompatibleAbsoluteFromRelativePath("src//test//java//testdata//input//"+jsonFileName);
        return filePath;
    }

}
