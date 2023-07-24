import CommonUtilities.BaseUtils;
import Pojo.RequestPojo.CreateUserRequest;
import Pojo.ResponsePojo.CreateUserResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static CommonUtilities.BaseUtils.getJsonArrayToListOfHashMap;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateUserTest {
    private ResponseService responseService;
    private BaseUtils baseUtils;

    @BeforeClass
    public void beforeClass(){
        responseService = new ResponseService();
        baseUtils = new BaseUtils();
    }

    @Test(dataProvider = "getData")
    public void createUserTC_01(HashMap<Object, Object> input) throws Exception {

        CreateUserRequest createUserRequest = new CreateUserRequest.Builder()
                .gender((String) input.get("gender")).build();
        CreateUserResponse createUserResponse = responseService
                .createUserRequest(createUserRequest,baseUtils.token());
        assertEquals(createUserResponse.getStatusCode(),201);
        assertNotNull(createUserResponse.getId());
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<Object, Object>> data = getJsonArrayToListOfHashMap
                (System.getProperty("user.dir")+ baseUtils.getProperty("src/main/resources/Data.properties","Data"));
        Object[][] obj= new Object[data.size()][1];
        for(int i=0;i<data.size();i++){
            obj[i][0]=data.get(i);
        }
        return obj;
    }
}
