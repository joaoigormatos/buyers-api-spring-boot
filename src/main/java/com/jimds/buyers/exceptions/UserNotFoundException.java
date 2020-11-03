package com.jimds.buyers.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException(String email){
        super("User with email "+email+" has been not found!");
    }
    public UserNotFoundException(int id){
        super("User with id "+id+" has been not found!");
    }


}
