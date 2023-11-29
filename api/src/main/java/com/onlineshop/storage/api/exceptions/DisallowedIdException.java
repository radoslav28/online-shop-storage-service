package com.onlineshop.storage.api.exceptions;

public class DisallowedIdException extends RuntimeException{
    public DisallowedIdException(String id) {
        super(String.format("Entity with id '%s' doesn't exist.", id));
    }
}
