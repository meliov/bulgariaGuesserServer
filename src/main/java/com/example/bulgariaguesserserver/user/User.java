package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.city.City;
import com.example.bulgariaguesserserver.user.dto.UpdateUserDto;
import com.example.bulgariaguesserserver.user.dto.UserDto;
import com.example.bulgariaguesserserver.util.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer level;
    @Column(nullable = false)
    private Double points;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<City> alreadyPickedCities;

    @Lob
    private byte[] image;


    public User() {
    }

    public User(String firstName, String lastName, String username, String password, LocalDateTime registrationDate, String email, Integer level, Double points, List<City> alreadyPickedCities, byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.email = email;
        this.level = level;
        this.points = points;
        this.alreadyPickedCities = alreadyPickedCities;
        this.image = image;
    }

    public void updateUserFromDto(UpdateUserDto userDto){
        this.setImage(userDto.getImage());
        this.setLevel(userDto.getLevel());
        this.setPoints(userDto.getPoints());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<City> getAlreadyPickedCities() {
        return alreadyPickedCities;
    }

    public User setAlreadyPickedCities(List<City> alreadyPickedCities) {
        this.alreadyPickedCities = alreadyPickedCities;
        return this;
    }


    public Integer getLevel() {
        return level;
    }

    public User setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Double getPoints() {
        return points;
    }

    public User setPoints(Double points) {
        this.points = points;
        return this;
    }


    public byte[] getImage() {
        return image;
    }

    public User setImage(byte[] image) {
        this.image = image;
        return this;
    }
}
