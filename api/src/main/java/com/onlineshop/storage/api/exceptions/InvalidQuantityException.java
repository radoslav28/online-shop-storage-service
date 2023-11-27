package com.onlineshop.storage.api.exceptions;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException() {
        super("Invalid quantity");
    }
}
