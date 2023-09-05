package com.hotelreview.hotels.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String fieldName, String fieldColumn, String id) {
        super(String.format("%s not found with %s: %s", fieldName, fieldColumn, id));
    }
}
