package com.hotelreview.user.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String id) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, id));
    }
}
