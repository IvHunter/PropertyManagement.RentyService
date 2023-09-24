package com.vanko.rentyservice.business.implementations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid apartment")
public class InvalidApartmentException extends RuntimeException {
    public InvalidApartmentException(String message) {
        super(message);
    }
}

