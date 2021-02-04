package com.mm.jalc.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotFoundPhonesInOrderException extends RuntimeException {

    public NotFoundPhonesInOrderException() {
        super("Not found phones in catalog.");
    }
}
