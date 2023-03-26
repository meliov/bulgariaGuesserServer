package com.example.bulgariaguesserserver.user;

import com.example.bulgariaguesserserver.city.City;
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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Double points;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<City> alreadyPickedCities;

    @Lob
    @Column(nullable = false)
    private byte[] image;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(name = "user_friends", joinColumns = {
//            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable =   false)}, inverseJoinColumns = {
//            @JoinColumn(name = "friends_id", referencedColumnName = "id", nullable = false)}, uniqueConstraints=
//    @UniqueConstraint(columnNames={"user_id", "friends_id"})
//    )
//
//    private List<User> friends;


    public UserStatus getStatus() {
        return status;
    }


    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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

//    public List<User> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<User> friends) {
//        this.friends = friends;
//    }

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
}
