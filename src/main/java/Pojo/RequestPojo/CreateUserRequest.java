package Pojo.RequestPojo;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String gender;
    private String name;
    private String email;
    private String status;

    public CreateUserRequest(Builder builder) {
        this.gender = builder.gender;
        this.name = builder.name;
        this.email = builder.email;
        this.status = builder.status;
    }
    public static class Builder {
        public String email;
        public String name;
        public String status;
        private String gender;

        public Builder() {
            //These are to assign default values to the request body
            this.email = "dummy" + String.valueOf(Math.random()).substring(3, 7) + "@yop.com";
            this.name = "DummyUser"+String.valueOf(Math.random()).substring(3, 7);
            this.status = "active";
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public CreateUserRequest build() {
            CreateUserRequest createUserRequest = new CreateUserRequest(this);
            return createUserRequest;
        }
    }
}
