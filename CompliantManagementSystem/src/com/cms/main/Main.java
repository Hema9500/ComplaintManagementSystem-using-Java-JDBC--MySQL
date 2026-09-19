package com.cms.main;

import java.util.Scanner;

import com.cms.controller.ComplaintController;
import com.cms.model.User;
import com.cms.service.UserService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        UserService userService =
                new UserService();

        ComplaintController complaintController =
                new ComplaintController();

        // =================================================
        // CREATE DEFAULT ADMIN
        // =================================================

        userService.createDefaultAdmin();

        // =================================================
        // MAIN MENU
        // =================================================

        while (true) {

            System.out.println(
                    "\n========================================");

            System.out.println(
                    "      COMPLAINT MANAGEMENT SYSTEM");

            System.out.println(
                    "========================================");

            System.out.println(
                    "1. Register");

            System.out.println(
                    "2. Login");

            System.out.println(
                    "3. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice =
                    scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                // =========================================
                // REGISTER
                // =========================================

                case 1:

                    register(
                            scanner,
                            userService);

                    break;

                // =========================================
                // LOGIN
                // =========================================

                case 2:

                    login(
                            scanner,
                            userService,
                            complaintController);

                    break;

                // =========================================
                // EXIT
                // =========================================

                case 3:

                    System.out.println(
                            "\nThank you for using the system.");

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "\nInvalid choice.");
            }
        }
    }

    // =====================================================
    // REGISTER
    // =====================================================

    private static void register(
            Scanner scanner,
            UserService userService) {

        System.out.println(
                "\n========== USER REGISTRATION ==========");

        System.out.print(
                "Enter name: ");

        String name =
                scanner.nextLine();

        System.out.print(
                "Enter email: ");

        String email =
                scanner.nextLine();

        System.out.print(
                "Enter password: ");

        String password =
                scanner.nextLine();

        User user =
                new User(
                        name,
                        email,
                        password,
                        "USER"
                );

        boolean result =
                userService.registerUser(user);

        if (result) {

            System.out.println(
                    "\nRegistration successful!");

            System.out.println(
                    "Your account has been created as USER.");

        } else {

            System.out.println(
                    "\nRegistration failed.");
        }
    }

    // =====================================================
    // LOGIN
    // =====================================================

    private static void login(
            Scanner scanner,
            UserService userService,
            ComplaintController complaintController) {

        System.out.println(
                "\n========== LOGIN ==========");

        System.out.print(
                "Enter email: ");

        String email =
                scanner.nextLine();

        System.out.print(
                "Enter password: ");

        String password =
                scanner.nextLine();

        User user =
                userService.login(
                        email,
                        password);

        if (user == null) {

            System.out.println(
                    "\nInvalid email or password.");

            return;
        }

        System.out.println(
                "\nLogin successful!");

        System.out.println(
                "Welcome, "
                        + user.getName());

        System.out.println(
                "Role: "
                        + user.getRole());

        // =================================================
        // USER
        // =================================================

        if ("USER".equalsIgnoreCase(
                user.getRole())) {

            userMenu(
                    scanner,
                    complaintController,
                    user);
        }

        // =================================================
        // ADMIN
        // =================================================

        else if ("ADMIN".equalsIgnoreCase(
                user.getRole())) {

            adminMenu(
                    scanner,
                    complaintController);
        }
    }

    // =====================================================
    // USER MENU
    // =====================================================

    private static void userMenu(
            Scanner scanner,
            ComplaintController complaintController,
            User user) {

        while (true) {

            System.out.println(
                    "\n========== USER MENU ==========");

            System.out.println(
                    "1. Submit Complaint");

            System.out.println(
                    "2. Track My Complaint");

            System.out.println(
                    "3. Delete My Complaint");

            System.out.println(
                    "4. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice =
                    scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                // =========================================
                // SUBMIT
                // =========================================

                case 1:

                    complaintController.submitComplaint(
                            user.getUserId());

                    break;

                // =========================================
                // TRACK
                // =========================================

                case 2:

                    complaintController.trackMyComplaints(
                            user.getUserId());

                    break;

                // =========================================
                // DELETE OWN COMPLAINT
                // =========================================

                case 3:

                    complaintController.deleteMyComplaint(
                            user.getUserId());

                    break;

                // =========================================
                // LOGOUT
                // =========================================

                case 4:

                    System.out.println(
                            "\nUser logged out successfully.");

                    return;

                default:

                    System.out.println(
                            "\nInvalid choice.");
            }
        }
    }

    // =====================================================
    // ADMIN MENU
    // =====================================================

    private static void adminMenu(
            Scanner scanner,
            ComplaintController complaintController) {

        while (true) {

            System.out.println(
                    "\n========== ADMIN MENU ==========");

            System.out.println(
                    "1. View Complaint");

            System.out.println(
                    "2. View All Complaints");

            System.out.println(
                    "3. Update Complaint Status");

            System.out.println(
                    "4. Delete Complaint");

            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice =
                    scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                // =========================================
                // VIEW ONE
                // =========================================

                case 1:

                    complaintController.viewComplaint();

                    break;

                // =========================================
                // VIEW ALL
                // =========================================

                case 2:

                    complaintController.viewAllComplaints();

                    break;

                // =========================================
                // UPDATE
                // =========================================

                case 3:

                    complaintController
                            .updateComplaintStatus();

                    break;

                // =========================================
                // DELETE
                // =========================================

                case 4:

                    complaintController.deleteComplaint();

                    break;

                // =========================================
                // LOGOUT
                // =========================================

                case 5:

                    System.out.println(
                            "\nAdmin logged out successfully.");

                    return;

                default:

                    System.out.println(
                            "\nInvalid choice.");
            }
        }
    }
}