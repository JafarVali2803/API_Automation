package Pojo.ResponsePojo;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUserResponse {

    @Setter
    private int statusCode;
    private String gender;

    private String name;

    private String id;

    private String email;

    private String status;
    private String field;

    private String message;

}
