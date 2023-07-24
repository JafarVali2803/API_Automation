import CommonUtilities.ExtentReportManager;
import CommonUtilities.BaseUtils;
import Pojo.RequestPojo.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.io.IOException;

import static CommonUtilities.BaseUtils.reportLogger;

public class RequestClient {

    BaseUtils data = new BaseUtils();
    private RequestSpecification getRequestSpecification(Object body) throws IOException {
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpecification=RestAssured.given().log().body()
                .baseUri(data.getProperty("src/main/resources/Data.properties","base-uri"))
                .contentType(ContentType.JSON)
                .body(body);
        return requestSpecification;
    }

    public Response getAllUsersRequest(String token) throws Exception {
        RequestSpecification requestSpecification = getRequestSpecification("");
        Response response = requestSpecification.header("Authorization", "Bearer " + token)
                .when()
                  .get(data.getProperty("src/main/resources/Data.properties","getAllUsers"));
        response.then()
                  .log().all();
        reportLogger(requestSpecification, response);
        return response;
    }

    public Response createUserRequest(CreateUserRequest body, String token) throws Exception {
        RequestSpecification requestSpecification = getRequestSpecification(body);
        Response response = requestSpecification.header("Authorization", "Bearer " + token)
                .when()
                  .post(data.getProperty("src/main/resources/Data.properties","createUser"));
        response.then()
                  .log().all();
        reportLogger(requestSpecification, response);
        return response;
    }
}
