package com.hotelreview.user.external.services;

import com.hotelreview.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    void createRating(Rating rating);
    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatingByUserId(@PathVariable String userId);
}
