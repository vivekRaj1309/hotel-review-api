package com.hotelreview.user.Service;

import com.hotelreview.user.Dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, String userId);
    UserDto getUser(String userId);
    List<UserDto> getAllUser();
    void deleteUser(String userId);
}
