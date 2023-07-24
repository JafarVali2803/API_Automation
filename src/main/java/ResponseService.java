import Pojo.RequestPojo.CreateUserRequest;
import Pojo.ResponsePojo.CreateUserResponse;
import Pojo.ResponsePojo.GetAllUsersResponse;
import io.restassured.response.Response;

public class ResponseService {

    public GetAllUsersResponse[] getAllUsersRequest(String token) throws Exception {
        Response response = new RequestClient().getAllUsersRequest(token);
        GetAllUsersResponse[] getAllUsersResponse = response.as(GetAllUsersResponse[].class);
        getAllUsersResponse[0].setStatusCode(response.statusCode());
        return getAllUsersResponse;
    }

    public CreateUserResponse createUserRequest(CreateUserRequest createUserRequest, String token) throws Exception {
        Response response = new RequestClient().createUserRequest(createUserRequest,token);
        //System.out.println("jafar"+response.getTime());
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
        createUserResponse.setStatusCode(response.statusCode());
        return createUserResponse;
    }
}
