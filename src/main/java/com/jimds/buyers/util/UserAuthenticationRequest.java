package com.jimds.buyers.util;


import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationRequest {
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

    public UserAuthenticationRequest() {

    }

    public UserAuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
