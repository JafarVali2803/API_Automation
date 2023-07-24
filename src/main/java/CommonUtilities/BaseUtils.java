package CommonUtilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseUtils {

    private Properties properties;
    private String value="";
    public String getProperty(String path, String key) throws IOException {
        properties=new Properties();
        FileInputStream fis=new FileInputStream(path);
        properties.load(fis);
        String value= properties.getProperty(key);
        return value;
    }

    public static List<HashMap<Object,Object>> getJsonArrayToListOfHashMap(String path) throws IOException {
        //Read Json as String
        String jstring = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        // Convert String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<Object, Object>> data = mapper.readValue(jstring, new TypeReference<List<HashMap<Object,Object>>>() {});
        return data;
    }

    public static void reportLogger(RequestSpecification requestSpecification, Response response) throws Exception {
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
    }
    private static void printRequestLogInReport(RequestSpecification  requestSpecification) throws Exception {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request body is ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody().toString());
    }
    private static void printResponseLogInReport(Response response) throws Exception {
        ExtentReportManager.logInfoDetails("Response status is " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response body is ");
        ExtentReportManager.logJson(response.getBody().asString());
    }

    public String token() throws IOException {
        String token = getProperty("src/main/resources/Data.properties","token");
        return token;
    }
}
