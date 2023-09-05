package com.hotelreview.user.Controller;

import com.hotelreview.user.Dto.ApiResponse;
import com.hotelreview.user.Dto.UserDto;
import com.hotelreview.user.Service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody @PathVariable UserDto userDto, String userId){
        return new ResponseEntity<>(userService.updateUser(userDto, userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
//    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }
    // Creating fall-back method for circuit breaker
    public ResponseEntity<UserDto> ratingHotelFallBack(String userId, Exception ex){
        UserDto userDto = UserDto.builder().userName("Dummy").email("Dummy@abc.com").contact("Dummy").build();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse("User deleted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
