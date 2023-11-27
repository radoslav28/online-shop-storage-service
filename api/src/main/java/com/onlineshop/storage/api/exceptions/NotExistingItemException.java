package com.onlineshop.storage.api.exceptions;

public class NotExistingItemException extends RuntimeException{
    public NotExistingItemException(String id) {
        super(String.format("Item with '%s' id doesn't exist.", id));
    }
}
