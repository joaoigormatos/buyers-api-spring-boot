package com.jimds.buyers.exceptions;

import javax.naming.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    public InvalidJwtAuthenticationException(String message){
        super(message);
    }
    public InvalidJwtAuthenticationException(){
        super();
    }
}
