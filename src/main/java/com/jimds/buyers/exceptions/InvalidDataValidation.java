package com.jimds.buyers.exceptions;

public class InvalidDataValidation {


    public static void generateException(String ...problems) throws Exception{
        if(problems.length == 1){
            throw new Exception( "The "+problems[problems.length - 1]+ " is invalid");
        }
        String resultToMount = "";
        for(String problem : problems){

            resultToMount = problem + ", ";
        }
        resultToMount+=resultToMount + " are in valid";
        throw new Exception( resultToMount);
    }
}
