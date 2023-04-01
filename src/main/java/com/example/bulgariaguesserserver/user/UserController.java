package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.user.dto.CreateUserDto;
import com.example.bulgariaguesserserver.user.dto.UpdateUserDto;
import com.example.bulgariaguesserserver.user.dto.UserDto;
import com.example.bulgariaguesserserver.util.exception.UserIsPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     *
     * @param usernameAndPass all post/put requests are being sent with only requestBody,
     *                        here request params are not awaited because of easier handling in frontend(android)
     *                        from there all params must be sent with ### as divider
     *
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody String usernameAndPass) {
        String username;
        String pass;
        try{
            String[] paramsArray = usernameAndPass.split("###");
            username = paramsArray[0];
            pass = paramsArray[1];
        }catch (Exception e){
            throw new RuntimeException("Bad arguments:  " + e.getMessage());
        }
        return ResponseEntity.ok(userService.loginUser(username, pass));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto userDto) {
        return ResponseEntity.ok(userService.updateCurrentUser(userDto));
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logoutUser() {
        userService.logout();
        return ResponseEntity.ok("Logged out successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}