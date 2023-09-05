package com.hotelreview.ratings.services;

import com.hotelreview.ratings.dto.RatingDto;

import java.util.List;

public interface RatingService {
    RatingDto getRatingById(String ratingId);
    RatingDto createRating(RatingDto ratingDto);
    RatingDto updateRating(RatingDto ratingDto, String ratingId);
    List<RatingDto> getRatings();
    List<RatingDto> getRatingsByUserId(String userId);
    List<RatingDto> getRatingsByHotelId(String hotelId);
    void deleteRatingById(String ratingId);
}
