package com.test.task01LoginAppBack.sec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token ;
    private String userName ;
    private String roles ;
    private String status ;

    public JwtResponse() {
    }

    public JwtResponse(String token, String userName, String roles, String status) {
        this.token = token;
        this.userName = userName;
        this.roles = roles;
        this.status = status;
    }
}
