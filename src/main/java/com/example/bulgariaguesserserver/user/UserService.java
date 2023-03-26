package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.user.dto.CreateUserDto;
import com.example.bulgariaguesserserver.user.dto.UserDto;
import com.example.bulgariaguesserserver.util.exception.UserIsPresentException;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserDto user) throws UserIsPresentException;


    UserDto findUserObjectById(Long id);
}
