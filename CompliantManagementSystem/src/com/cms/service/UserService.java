package com.cms.service;

import com.cms.dao.UserDAO;
import com.cms.model.User;
import com.cms.model.UserRole;

public class UserService {

    private UserDAO userDAO;

    public UserService() {

        userDAO = new UserDAO();
    }

    // =====================================================
    // REGISTER NORMAL USER
    // =====================================================

    public boolean registerUser(User user) {

        if (user == null) {

            System.out.println(
                    "User cannot be null.");

            return false;
        }

        if (user.getName() == null ||
                user.getName().trim().isEmpty()) {

            System.out.println(
                    "Name cannot be empty.");

            return false;
        }

        if (user.getEmail() == null ||
                user.getEmail().trim().isEmpty()) {

            System.out.println(
                    "Email cannot be empty.");

            return false;
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            System.out.println(
                    "Password cannot be empty.");

            return false;
        }

        // Normal registration is always USER

        user.setRole(
                UserRole.USER.name());

        return userDAO.registerUser(user);
    }

    // =====================================================
    // LOGIN
    // =====================================================

    public User login(
            String email,
            String password) {

        if (email == null ||
                email.trim().isEmpty()) {

            System.out.println(
                    "Email cannot be empty.");

            return null;
        }

        if (password == null ||
                password.trim().isEmpty()) {

            System.out.println(
                    "Password cannot be empty.");

            return null;
        }

        return userDAO.login(
                email,
                password);
    }

    // =====================================================
    // CREATE DEFAULT ADMIN
    // =====================================================

    public void createDefaultAdmin() {

        if (userDAO.adminExists()) {

            return;
        }

        User admin =
                new User(
                        "System Admin",
                        "admin@gmail.com",
                        "admin123",
                        UserRole.ADMIN.name()
                );

        boolean result =
                userDAO.registerUser(admin);

        if (result) {

            System.out.println(
                    "Default admin account created.");

            System.out.println(
                    "Email: admin@gmail.com");

            System.out.println(
                    "Password: admin123");
        }
    }
}