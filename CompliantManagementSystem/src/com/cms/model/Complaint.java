package com.cms.model;

import java.sql.Timestamp;

public class Complaint {

    private int complaintId;
    private int userId;
    private String title;
    private String description;
    private String status;
    private Timestamp createdDate;

    public Complaint() {
    }

    public Complaint(
            int userId,
            String title,
            String description) {

        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = ComplaintStatus.PENDING.name();
    }

    // Complaint ID

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    // User ID

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Title

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Description

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Created date

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(
            Timestamp createdDate) {

        this.createdDate = createdDate;
    }
}