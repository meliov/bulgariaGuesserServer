package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.city.CityDto;
import com.example.bulgariaguesserserver.city.CityService;
import com.example.bulgariaguesserserver.user.dto.CreateUserDto;
import com.example.bulgariaguesserserver.util.exception.UserIsPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDto user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (UserIsPresentException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User Already Exists ..");
        }
    }

}