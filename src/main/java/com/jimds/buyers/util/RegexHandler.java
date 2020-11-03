package com.jimds.buyers.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexHandler {
    private String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public boolean isEmailValid (String email){
        return expressionResult(email,emailRegex);
    }


    public boolean isPasswordValid(String password) {
        return expressionResult(password,passwordRegex);
    }

    private boolean expressionResult(String data, String expression){
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
