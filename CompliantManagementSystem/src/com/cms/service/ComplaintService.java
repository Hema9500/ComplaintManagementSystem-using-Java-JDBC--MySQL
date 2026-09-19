package com.cms.service;

import java.util.List;

import com.cms.dao.ComplaintDAO;
import com.cms.model.Complaint;
import com.cms.model.ComplaintStatus;

public class ComplaintService {

    private ComplaintDAO complaintDAO;

    public ComplaintService() {

        complaintDAO =
                new ComplaintDAO();
    }

    // =====================================================
    // SUBMIT COMPLAINT
    // =====================================================

    public boolean submitComplaint(
            Complaint complaint) {

        if (complaint == null) {

            System.out.println(
                    "Complaint cannot be null.");

            return false;
        }

        if (complaint.getUserId() <= 0) {

            System.out.println(
                    "Invalid user.");

            return false;
        }

        if (complaint.getTitle() == null ||
                complaint.getTitle().trim().isEmpty()) {

            System.out.println(
                    "Title cannot be empty.");

            return false;
        }

        if (complaint.getDescription() == null ||
                complaint.getDescription()
                        .trim()
                        .isEmpty()) {

            System.out.println(
                    "Description cannot be empty.");

            return false;
        }

        // Every new complaint starts as PENDING

        complaint.setStatus(
                ComplaintStatus.PENDING.name());

        return complaintDAO.addComplaint(
                complaint);
    }

    // =====================================================
    // TRACK USER COMPLAINTS
    // =====================================================

    public List<Complaint> trackMyComplaints(
            int userId) {

        if (userId <= 0) {

            return null;
        }

        return complaintDAO
                .getComplaintsByUserId(userId);
    }

    // =====================================================
    // DELETE USER'S OWN COMPLAINT
    // =====================================================

    public boolean deleteMyComplaint(
            int complaintId,
            int userId) {

        if (complaintId <= 0 ||
                userId <= 0) {

            return false;
        }

        return complaintDAO.deleteUserComplaint(
                complaintId,
                userId);
    }

    // =====================================================
    // ADMIN: VIEW ONE
    // =====================================================

    public Complaint getComplaint(
            int complaintId) {

        if (complaintId <= 0) {

            return null;
        }

        return complaintDAO.getComplaintById(
                complaintId);
    }

    // =====================================================
    // ADMIN: VIEW ALL
    // =====================================================

    public List<Complaint> getAllComplaints() {

        return complaintDAO.getAllComplaints();
    }

    // =====================================================
    // ADMIN: UPDATE STATUS
    // =====================================================

    public boolean updateStatus(
            int complaintId,
            ComplaintStatus status) {

        if (complaintId <= 0 ||
                status == null) {

            return false;
        }

        return complaintDAO.updateStatus(
                complaintId,
                status.name());
    }

    // =====================================================
    // ADMIN: DELETE
    // =====================================================

    public boolean deleteComplaint(
            int complaintId) {

        if (complaintId <= 0) {

            return false;
        }

        return complaintDAO.deleteComplaint(
                complaintId);
    }
}