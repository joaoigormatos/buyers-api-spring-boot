package com.jimds.buyers.exceptions;

public class ProductNotFound extends RuntimeException  {

    public ProductNotFound(String mensage){
        super(mensage);
    }
    public ProductNotFound(int id){
        super("User with id "+id+" has been not found!");
    }


}
