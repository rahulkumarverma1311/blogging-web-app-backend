package com.deeRav.service;

import com.deeRav.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto , Integer userId);
    UserDto getUserBYId(Integer userId);
    List<UserDto>getAllUser();
    void deleteUser(Integer userId);
}
