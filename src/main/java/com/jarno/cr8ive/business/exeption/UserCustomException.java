package com.jarno.cr8ive.business.exeption;

public class UserCustomException extends Exception{

    public UserCustomException(){

    }
    public UserCustomException(String message) { super(message);}

    public UserCustomException(String message, Throwable cause) {super (message, cause);}
}
