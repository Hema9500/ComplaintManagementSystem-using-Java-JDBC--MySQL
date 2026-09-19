package com.cms.controller;

import java.util.List;
import java.util.Scanner;

import com.cms.model.Complaint;
import com.cms.model.ComplaintStatus;
import com.cms.service.ComplaintService;

public class ComplaintController {

    private ComplaintService complaintService;
    private Scanner scanner;

    public ComplaintController() {

        complaintService =
                new ComplaintService();

        scanner =
                new Scanner(System.in);
    }

    // =====================================================
    // USER: SUBMIT
    // =====================================================

    public void submitComplaint(
            int userId) {

        System.out.println(
                "\n========== SUBMIT COMPLAINT ==========");

        System.out.print(
                "Enter complaint title: ");

        String title =
                scanner.nextLine();

        System.out.print(
                "Enter complaint description: ");

        String description =
                scanner.nextLine();

        Complaint complaint =
                new Complaint(
                        userId,
                        title,
                        description);

        boolean result =
                complaintService.submitComplaint(
                        complaint);

        if (result) {

            System.out.println(
                    "\nComplaint submitted successfully!");

            System.out.println(
                    "Your complaint status is: PENDING");

        } else {

            System.out.println(
                    "\nFailed to submit complaint.");
        }
    }

    // =====================================================
    // USER: TRACK
    // =====================================================

    public void trackMyComplaints(
            int userId) {

        System.out.println(
                "\n========== MY COMPLAINTS ==========");

        List<Complaint> complaints =
                complaintService.trackMyComplaints(
                        userId);

        if (complaints == null ||
                complaints.isEmpty()) {

            System.out.println(
                    "You have no complaints.");

            return;
        }

        for (Complaint complaint : complaints) {

            printComplaint(complaint);
        }
    }

    // =====================================================
    // USER: DELETE OWN COMPLAINT
    // =====================================================

    public void deleteMyComplaint(
            int userId) {

        System.out.println(
                "\n========== DELETE MY COMPLAINT ==========");

        System.out.print(
                "Enter complaint ID: ");

        int complaintId =
                scanner.nextInt();

        scanner.nextLine();

        boolean result =
                complaintService.deleteMyComplaint(
                        complaintId,
                        userId);

        if (result) {

            System.out.println(
                    "Your complaint has been deleted.");

        } else {

            System.out.println(
                    "Complaint not found or it does not belong to you.");
        }
    }

    // =====================================================
    // ADMIN: VIEW ONE
    // =====================================================

    public void viewComplaint() {

        System.out.println(
                "\n========== VIEW COMPLAINT ==========");

        System.out.print(
                "Enter complaint ID: ");

        int complaintId =
                scanner.nextInt();

        scanner.nextLine();

        Complaint complaint =
                complaintService.getComplaint(
                        complaintId);

        if (complaint == null) {

            System.out.println(
                    "Complaint not found.");

            return;
        }

        printComplaint(complaint);
    }

    // =====================================================
    // ADMIN: VIEW ALL
    // =====================================================

    public void viewAllComplaints() {

        System.out.println(
                "\n========== ALL COMPLAINTS ==========");

        List<Complaint> complaints =
                complaintService.getAllComplaints();

        if (complaints == null ||
                complaints.isEmpty()) {

            System.out.println(
                    "No complaints found.");

            return;
        }

        for (Complaint complaint : complaints) {

            printComplaint(complaint);
        }
    }

    // =====================================================
    // ADMIN: UPDATE STATUS
    // =====================================================

    public void updateComplaintStatus() {

        System.out.println(
                "\n========== UPDATE STATUS ==========");

        System.out.print(
                "Enter complaint ID: ");

        int complaintId =
                scanner.nextInt();

        scanner.nextLine();

        Complaint complaint =
                complaintService.getComplaint(
                        complaintId);

        if (complaint == null) {

            System.out.println(
                    "Complaint not found.");

            return;
        }

        System.out.println(
                "\nCurrent status: "
                        + formatStatus(
                                complaint.getStatus()));

        System.out.println(
                "\n1. PENDING");

        System.out.println(
                "2. IN_PROGRESS");

        System.out.println(
                "3. RESOLVED");

        System.out.print(
                "Enter new status: ");

        int choice =
                scanner.nextInt();

        scanner.nextLine();

        ComplaintStatus newStatus;

        switch (choice) {

            case 1:

                newStatus =
                        ComplaintStatus.PENDING;

                break;

            case 2:

                newStatus =
                        ComplaintStatus.IN_PROGRESS;

                break;

            case 3:

                newStatus =
                        ComplaintStatus.RESOLVED;

                break;

            default:

                System.out.println(
                        "Invalid status.");

                return;
        }

        boolean result =
                complaintService.updateStatus(
                        complaintId,
                        newStatus);

        if (result) {

            System.out.println(
                    "Complaint status updated successfully.");

        } else {

            System.out.println(
                    "Failed to update status.");
        }
    }

    // =====================================================
    // ADMIN: DELETE
    // =====================================================

    public void deleteComplaint() {

        System.out.println(
                "\n========== DELETE COMPLAINT ==========");

        System.out.print(
                "Enter complaint ID: ");

        int complaintId =
                scanner.nextInt();

        scanner.nextLine();

        boolean result =
                complaintService.deleteComplaint(
                        complaintId);

        if (result) {

            System.out.println(
                    "Complaint deleted successfully.");

        } else {

            System.out.println(
                    "Complaint not found.");
        }
    }

    // =====================================================
    // DISPLAY COMPLAINT
    // =====================================================

    private void printComplaint(
            Complaint complaint) {

        System.out.println(
                "\n----------------------------------------");

        System.out.println(
                "Complaint ID : "
                        + complaint.getComplaintId());

        System.out.println(
                "User ID      : "
                        + complaint.getUserId());

        System.out.println(
                "Title        : "
                        + complaint.getTitle());

        System.out.println(
                "Description  : "
                        + complaint.getDescription());

        System.out.println(
                "Status       : "
                        + formatStatus(
                                complaint.getStatus()));

        System.out.println(
                "Created Date : "
                        + complaint.getCreatedDate());

        System.out.println(
                "----------------------------------------");
    }

    // =====================================================
    // FORMAT STATUS
    // =====================================================

    private String formatStatus(
            String status) {

        if (status == null) {

            return "UNKNOWN";
        }

        switch (status) {

            case "PENDING":
                return "Pending";

            case "IN_PROGRESS":
                return "In Progress";

            case "RESOLVED":
                return "Resolved";

            default:
                return status;
        }
    }
}