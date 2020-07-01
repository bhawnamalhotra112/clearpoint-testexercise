package helpers;

import utils.LogHelper;
import utils.VerificationHelper;
import gherkin.deps.com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;*/

import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CommonHelper {

    private static final Logger log = LogManager.getLogger(helpers.CommonHelper.class.getName());

    public String convertXeroReponseToString(List xeroResponse){
        LogHelper.info(log,"In convertXeroReponseToJson");
        try{
            //String json = new Gson().toJson(xeroResponse.toArray());
            String json = new Gson().toJson(xeroResponse);
            LogHelper.info(log,json);
            LogHelper.info(log,"convertXeroReponseToJson - PASS");
            return json;
        }catch(Exception e){
            LogHelper.error(log,"Exception In convertXeroReponseToJson - "+ e.getMessage());
            VerificationHelper.FailVerification("Exception in convertXeroReponseToString");
            return "Exception in convertXeroReponseToString";
        }
    }
    /*public String readValuefromXeroReponse(String xeroResponse, String key){
        LogHelper.info(log,"In readValuefromXeroReponse");
        try{
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(xeroResponse);
            //JSONObject jsonObject = new JSONObject(xeroResponse);
            String value= (String) json.get("apIKey");
            LogHelper.info(log,"value is ---> "+ value);
                    //.get("apIKey");
            //LogHelper.info(log,json);
            LogHelper.info(log,"readValuefromXeroReponse - PASS");
            return value;
        }catch(Exception e){
            LogHelper.error(log,"Exception In convertXeroReponseToJson - "+ e.getMessage());
            VerificationHelper.FailVerification("Exception In convertXeroReponseToJson");
            return "Exception in convertXeroReponseToString";
        }
    }*/

    public static String getAbsoluteFromRelativePath(String path)
    {
        return FileSystems.getDefault().getPath(path).normalize().toAbsolutePath().toString();
    }

    public static String getOSCompatibleAbsoluteFromRelativePath(String path)
    {
        String getOSCompetiblePath =FileSystems.getDefault().getPath(path).normalize().toAbsolutePath().toString();
        return getOSCompetiblePath(getOSCompetiblePath);
    }

    public static String getOSCompetiblePath(String path)
    {
        if (!System.getProperty("os.name").contains("Windows"))
        {
            path = path.replace("\\", "/");
        }
        return path;
    }

    public String getOutputFilePath(String fileName){
        LogHelper.info(log,"In getOutputFilePath");
        try{
            String filePath = getAbsoluteFromRelativePath("src\\test\\java\\testdata\\branch\\output\\"+fileName);
            LogHelper.info(log,"The Absolute path is '"+ filePath+"'");
            /*if (!System.getProperty("os.name").contains("Windows"))
            {
                filePath = filePath.replace("\\", "/");
            }*/
            LogHelper.info(log,"Returning the Absolute path is '"+ filePath+"'");
            //String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\testdata\\branch\\output\\"+fileName;
            return getOSCompatibleAbsoluteFromRelativePath(filePath);
        }catch(Exception e){
            LogHelper.error(log,"Exception In getOutputFilePath - "+ e.getMessage());
            VerificationHelper.FailVerification("Exception In getOutputFilePath ");
            return "Exception In getOutputFilePath ";
        }
    }


   /* public String getJsonNodeValue(String JSONString, String nodeName){
        Object object;
        try {
            JSONParser jsonParser = new JSONParser();
            //object = jsonParser.parse(new FileReader(JSONFile));
            object = jsonParser.parse(JSONString);
            JSONObject jsonObject = (JSONObject) object;
            //JSONObject temp;
            //JSONObject order;
            // order = (JSONObject) jsonObject.get("order");
            String replacedValue = null;

            String tempkey[] = nodeName.split("_");
            LogHelper.error(log, "TEMP KEY LENGTH    " + tempkey.length);
            if (tempkey.length <6) {
                switch (tempkey.length) {
                    case 1:
                        return jsonObject.get(tempkey[0]).toString();
                    case 2:
                        JSONObject temp20 = (JSONObject) jsonObject.get(tempkey[0]);
                        return temp20.get(tempkey[1]).toString();
                    case 3:
                        JSONObject temp31 = (JSONObject) jsonObject.get(tempkey[0]);
                        JSONObject temp32 = (JSONObject) temp31.get(tempkey[1]);
                        return temp32.get(tempkey[2]).toString();
                    case 4:
                        JSONObject temp41 = (JSONObject) jsonObject.get(tempkey[0]);
                        JSONObject temp42 = (JSONObject) temp41.get(tempkey[1]);
                        return temp42.get(tempkey[2]).toString();
                    case 5:
                        JSONObject temp51 = (JSONObject) jsonObject.get(tempkey[0]);
                        JSONObject temp52 = (JSONObject) temp51.get(tempkey[1]);
                        return temp52.get(tempkey[2]).toString();
                    default:
                        return jsonObject.get(tempkey[0]).toString();
                }
            }else{
                System.out.println("This Methos Supports only update 5 child object");
                return "ERROR- LIMIT IS 5 JSON CHILD OBJECTS";
            }
        } catch (Exception e) {
            LogHelper.error(log, "Exception in getJsonNodeValue ---- " + e.getMessage());
            return "Exception in getJsonNodeValue ---- " + e.getMessage();
        }
    }*/
   public int generateRandomNumber(int maxDigit) {
       Random num = new Random();
       int randomNumber=num.nextInt(maxDigit);
       LogHelper.info(log,"RANDOM NUMBER -- " + randomNumber);
       return randomNumber;
   }
    public int generateRandomNumber(int minDigit, int maxDigit) {
       try {
           Random num = new Random();
           int randomNumber = num.nextInt(Integer.valueOf(maxDigit) - Integer.valueOf(minDigit)+1) + Integer.valueOf(minDigit);
           LogHelper.info(log, "RANDOM NUMBER -- " + randomNumber);
           return randomNumber;
       } catch(Exception e){
           throw e;
       }
    }

    public Date getCurrentDateAndTime(String format) {
        String dateFormat;
        if(format.equals(null) || format.equals("")){
            dateFormat="yyyy-MM-dd 'at' HH:mm:ss z";
        }else{
            dateFormat=format;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        return date;
    }

}
