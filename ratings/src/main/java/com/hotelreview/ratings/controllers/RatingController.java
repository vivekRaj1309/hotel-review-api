package com.hotelreview.ratings.controllers;

import com.hotelreview.ratings.dto.RatingDto;
import com.hotelreview.ratings.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RatingDto> createRating(@Valid @RequestBody RatingDto ratingDto){
        return new ResponseEntity<>(ratingService.createRating(ratingDto), HttpStatus.CREATED);
    }
    @PutMapping("/{ratingId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RatingDto> updateRating(@Valid @RequestBody RatingDto ratingDto, @PathVariable String ratingId){
        return new ResponseEntity<>(ratingService.updateRating(ratingDto, ratingId), HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    public ResponseEntity<List<RatingDto>> getAllRatings(){
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }
    @GetMapping("/{ratingId}")
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable String ratingId){
        return new ResponseEntity<>(ratingService.getRatingById(ratingId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingsByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId), HttpStatus.OK);
    }
}
