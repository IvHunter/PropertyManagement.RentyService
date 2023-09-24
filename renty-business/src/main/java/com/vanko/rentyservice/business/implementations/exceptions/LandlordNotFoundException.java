package com.vanko.rentyservice.business.implementations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Landlord not found")
public class LandlordNotFoundException extends RuntimeException {
    public LandlordNotFoundException(String message) {
        super(message);
    }
}
