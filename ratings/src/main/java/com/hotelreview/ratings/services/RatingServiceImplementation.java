package com.hotelreview.ratings.services;

import com.hotelreview.ratings.dto.RatingDto;
import com.hotelreview.ratings.entities.Rating;
import com.hotelreview.ratings.exception.ResourceNotFoundException;
import com.hotelreview.ratings.repositories.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImplementation implements RatingService{
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public RatingDto getRatingById(String ratingId) {
        Rating rating = ratingRepository
                .findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating could not be found for: ", ratingId));
        return modelMapper.map(rating, RatingDto.class);
    }

    @Override
    public RatingDto createRating(RatingDto ratingDto) {
        String ratingId = UUID.randomUUID().toString();
        ratingDto.setId(ratingId);
        Rating rating = ratingRepository.save(modelMapper.map(ratingDto, Rating.class));
        return modelMapper.map(rating, RatingDto.class);
    }

    @Override
    public RatingDto updateRating(RatingDto ratingDto, String ratingId) {
        Rating rating = modelMapper.map(getRatingById(ratingId), Rating.class);
        rating.setRating(ratingDto.getRating());
        rating.setUserId(ratingDto.getUserId());
        rating.setFeedback(ratingDto.getFeedback());
        rating.setHotelId(ratingDto.getHotelId());
        return modelMapper.map(ratingRepository.save(rating), RatingDto.class);
    }

    @Override
    public List<RatingDto> getRatings() {
        List<Rating> ratingList = ratingRepository.findAll();
        return ratingList.stream()
                .map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingsByUserId(String userId) {
        List<Rating> ratingList = ratingRepository.getRatingByUserId(userId);
        return ratingList.stream()
                .map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingsByHotelId(String hotelId) {
        List<Rating> ratingList = ratingRepository.getRatingByHotelId(hotelId);
        return ratingList.stream()
                .map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteRatingById(String ratingId) {
        Rating rating = modelMapper.map(getRatingById(ratingId), Rating.class);
        ratingRepository.delete(rating);
    }
}
