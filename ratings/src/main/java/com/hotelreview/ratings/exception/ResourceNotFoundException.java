package com.hotelreview.ratings.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message, String ratingId) {
        super(String.format("%s" + ratingId, message, ratingId));
    }
}
