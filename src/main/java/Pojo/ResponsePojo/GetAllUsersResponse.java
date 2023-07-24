package Pojo.ResponsePojo;

import lombok.Getter;
import lombok.Setter;

@Getter
public class GetAllUsersResponse {

    @Setter
    private int statusCode;

    private String gender;
    private String name;
    private String id;
    private String email;
    private String status;
}
