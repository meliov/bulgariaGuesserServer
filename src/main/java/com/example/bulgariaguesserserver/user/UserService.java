package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.city.City;
import com.example.bulgariaguesserserver.user.dto.CreateUserDto;
import com.example.bulgariaguesserserver.user.dto.LoggedInUser;
import com.example.bulgariaguesserserver.user.dto.UpdateUserDto;
import com.example.bulgariaguesserserver.user.dto.UserDto;
import com.example.bulgariaguesserserver.util.BaseEntity;
import com.example.bulgariaguesserserver.util.exception.UserIsPresentException;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Integer MAX_STORED_CITIES = 10;
    private static final LoggedInUser loggedInUser = new LoggedInUser();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(CreateUserDto createUserDto) throws UserIsPresentException {
        System.out.println("In create new user");
        if (userRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(createUserDto.getEmail()) || e.getEmail().equals(createUserDto.getUsername()))) {
            throw new UserIsPresentException("User already exists");
        }
        var user = modelMapper.map(createUserDto, User.class);
        var salt = BCrypt.gensalt();
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        user.setPoints(0.0);
        user.setLevel(1);
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto loginUser(String username, String enteredPass) {
        System.out.println("In login user");
        User user = null;
        try {
            user = userRepository.findUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user with username: " + username);
        }

        if (Objects.isNull(user)) {
            throw new RuntimeException("User with username = " + username + "does not exist.");
        } else {
            if (!BCrypt.checkpw(enteredPass, user.getPassword())) {
                throw new RuntimeException("Incorrect password!");
            }

        }
        initialiseLoggedInUser(user);;
        return modelMapper.map(user, UserDto.class);
    }

    public void logout() {
        System.out.println("In logout user");
        loggedInUser.setCurrentUser(null);
        System.out.println("User logged out successfully!");
    }

    public UserDto updateCurrentUser(UpdateUserDto userDto) {
        User userToUpdate;
        try {
            var optionalUser = userRepository.findById(userDto.getId());
            if (optionalUser.isPresent()) {
                userToUpdate = optionalUser.get();
            } else {
                throw new Exception("");
            }
            userToUpdate.updateUserFromDto(userDto);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user with id: " + userDto.getId());
        }
        initialiseLoggedInUser(userToUpdate);
        return modelMapper.map(userRepository.save(userToUpdate), UserDto.class);
    }

    public void addCityForLoggedInUser(City city) {
        if (loggedInUser.getCurrentUser().getAlreadyPickedCities() == null
                || loggedInUser.getCurrentUser().getAlreadyPickedCities().size() >= MAX_STORED_CITIES) {
            loggedInUser.getCurrentUser().setAlreadyPickedCities(new ArrayList<>());
        }

        loggedInUser.getCurrentUser().getAlreadyPickedCities().add(city);
        userRepository.save(loggedInUser.getCurrentUser());
    }

    public List<Long> getLoggedInUserCitiesIds() {
        return loggedInUser
                .getCurrentUser()
                .getAlreadyPickedCities()
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public List<UserDto> getAllUsers(){
       return userRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(User::getLevel)
                        .thenComparing(User::getPoints)
                        .reversed())
               .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    private void initialiseLoggedInUser(User user){
        if(loggedInUser.getCurrentUser() == null){
            loggedInUser.setCurrentUser(new User());
        }
        loggedInUser.setCurrentUser(user);
    }
}
