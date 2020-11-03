package com.jimds.buyers.exceptions;

public class InvalidEmailException extends Exception {

    public InvalidEmailException(){
        super("Email is invalid");
    }

}
