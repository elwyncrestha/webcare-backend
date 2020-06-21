package com.pemits.webcare.core.exception;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Invalid Token");
    }
}
