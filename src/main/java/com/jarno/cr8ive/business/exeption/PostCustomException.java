package com.jarno.cr8ive.business.exeption;




public class PostCustomException extends Exception{

    public PostCustomException(){

    }
    public PostCustomException(String message) { super(message);}

    public PostCustomException(String message, Throwable cause) {super (message, cause);}
}
