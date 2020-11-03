package com.jimds.buyers.dto;

import com.jimds.buyers.model.AplicationUser;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationDTO {
    private String password;
    private String email;


    public AplicationUser toUser(){
        return new AplicationUser(email,password);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
