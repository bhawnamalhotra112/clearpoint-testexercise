package helpers;

import utils.LogHelper;
import utils.VerificationHelper;
import gherkin.deps.com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            LogHelper.info(log,"Returning the Absolute path is '"+ filePath+"'");
            return getOSCompatibleAbsoluteFromRelativePath(filePath);
        }catch(Exception e){
            LogHelper.error(log,"Exception In getOutputFilePath - "+ e.getMessage());
            VerificationHelper.FailVerification("Exception In getOutputFilePath ");
            return "Exception In getOutputFilePath ";
        }
    }

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
