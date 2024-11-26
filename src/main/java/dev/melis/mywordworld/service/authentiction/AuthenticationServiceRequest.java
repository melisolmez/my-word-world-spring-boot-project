package dev.melis.mywordworld.service.authentiction;

import lombok.Getter;

@Getter
public class AuthenticationServiceRequest {

    private String email;
    private String password;

    public AuthenticationServiceRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuthenticationServiceRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
