package com.onlineshop.storage.api.exceptions;

public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException() {
        super("Not enough quantity in storage");
    }
}
