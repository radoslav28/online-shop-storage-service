package com.onlineshop.storage.api.exceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException() {
        super("Invalid price");
    }
}
