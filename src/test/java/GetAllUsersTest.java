import CommonUtilities.BaseUtils;
import Pojo.ResponsePojo.GetAllUsersResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetAllUsersTest {


    private ResponseService responseService;
    private BaseUtils baseUtils;

    @BeforeClass
    public void beforeClass(){
        responseService = new ResponseService();
        baseUtils = new BaseUtils();
    }
    @Test
    public void getAllusersTC_01() throws Exception {

        GetAllUsersResponse[] getAllUsersResponse = responseService
                .getAllUsersRequest(baseUtils.token());
        assertEquals(getAllUsersResponse[0].getStatusCode(),200);
    }
}
