package com.example.bulgariaguesserserver.util.exception;

import java.io.Serializable;

public class UserIsPresentException extends Exception implements Serializable {
    public UserIsPresentException(String message) {
        super(message);
    }
}
