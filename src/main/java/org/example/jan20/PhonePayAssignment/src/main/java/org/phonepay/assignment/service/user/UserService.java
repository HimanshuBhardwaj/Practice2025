package org.phonepay.assignment.service.user;

import org.phonepay.assignment.exception.UserLoadException;
import org.phonepay.assignment.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/
public class UserService implements UserServiceI {
    TreeSet<User> users;

    public UserService() {
        users.addAll(loadUsersFromFile("UserData"));
    }

    @Override
    public boolean isValidUser(UUID userID) {
        User dummyUser = getDummyUser(userID);
        return users.contains(dummyUser);
    }

    private User getDummyUser(UUID userID) {
        User user = new User();
        user.setId(userID);
        return user;
    }

    // Method to load users from a CSV file
    private TreeSet<User> loadUsersFromFile(String filePath) throws UserLoadException {
        TreeSet<User> users = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                UUID id = UUID.fromString(fields[0]);
                String name = fields[1];
                String phoneNumber = fields[2];
                String emailId = fields[3];
                users.add(new User(id, name, phoneNumber, emailId));
            }
        } catch (Exception e) {
            throw new UserLoadException(e.getMessage());
        }
        return users;
    }
}
