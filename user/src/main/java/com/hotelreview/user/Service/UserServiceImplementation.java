package com.hotelreview.user.Service;

import com.hotelreview.user.Dto.UserDto;
import com.hotelreview.user.Exceptions.ResourceNotFoundException;
import com.hotelreview.user.Repositories.UserRepository;
import com.hotelreview.user.entities.Hotel;
import com.hotelreview.user.entities.Rating;
import com.hotelreview.user.entities.User;
import com.hotelreview.user.external.services.HotelService;
import com.hotelreview.user.external.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
    private final RatingService ratingService;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository,
                                     ModelMapper modelMapper,
                                     RestTemplate restTemplate,
                                     HotelService hotelService,
                                     RatingService ratingService){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
        this.ratingService = ratingService;
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setId(userId);
        List<Rating> ratingList = userDto.getRatingList();
        ratingList.forEach(rating -> {
            rating.setUserId(userId);
            ratingService.createRating(rating);
            String hotelId = rating.getHotelId();
            rating.setHotel(hotelService.getHotel(hotelId));
        });
        userRepository.save(modelMapper.map(userDto, User.class));
        userDto.setRatingList(ratingList);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = modelMapper.map(getUser(userId), User.class);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
//            Calling rating service using rest template
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getId(), Rating[].class);
        assert ratings != null;
        List<Rating> ratingList = Arrays.stream(ratings).peek(rating -> {
/*          Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Calling hotel service with help of OpenFeignClient */

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }).toList();
        user.setRatingList(ratingList);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> {
            List<Rating> ratingList = ratingService.getRatingByUserId(user.getId());
            ratingList.forEach(rating -> rating.setHotel(hotelService.getHotel(rating.getHotelId())));
            user.setRatingList(ratingList);
        });
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
    @Override
    public void deleteUser(String userId) {
        User user = modelMapper.map(getUser(userId), User.class);
        userRepository.delete(user);
    }
}
