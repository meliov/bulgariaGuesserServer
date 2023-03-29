package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.user.dto.CreateUserDto;
import com.example.bulgariaguesserserver.user.dto.UserDto;
import com.example.bulgariaguesserserver.util.exception.UserIsPresentException;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(CreateUserDto createUserDto) throws UserIsPresentException {
        if (userRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(createUserDto.getEmail()))) {
            throw new UserIsPresentException("User already exists");
        }
        var user = modelMapper.map(createUserDto, User.class);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setPoints(0.0);
        user.setLevel(0);
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findUserObjectById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User with id = " + id + "does not exist.");
        }
        return modelMapper.map(user.get(), UserDto.class);
    }
}
