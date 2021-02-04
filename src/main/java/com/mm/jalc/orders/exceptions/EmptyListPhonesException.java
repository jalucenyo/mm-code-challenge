package com.mm.jalc.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyListPhonesException extends RuntimeException {

    public EmptyListPhonesException() {
        super("List phones empty, in order");
    }
}
