package com.example.bulgariaguesserserver.user.dto;

import com.example.bulgariaguesserserver.user.User;

/**
 * Stores the current logged in user, because so far authentication and authorization is out of the box
 */
public class LoggedInUser {
    private static User currentUser = new User();

    public User getCurrentUser() {
        return currentUser;
    }

    public LoggedInUser setCurrentUser(User currentUser) {
        LoggedInUser.currentUser = currentUser;
        return this;
    }
}
