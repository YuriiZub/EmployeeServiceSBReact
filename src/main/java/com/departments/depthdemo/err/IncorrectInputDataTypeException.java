package com.departments.depthdemo.err;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectInputDataTypeException extends Exception {

    public IncorrectInputDataTypeException(String message) {
        super("Invalid value of " + message);
    }
}
