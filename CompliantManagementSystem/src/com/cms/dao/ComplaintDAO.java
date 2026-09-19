package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.model.Complaint;
import com.cms.util.DBConnection;

public class ComplaintDAO {

    // =====================================================
    // ADD COMPLAINT
    // =====================================================

    public boolean addComplaint(
            Complaint complaint) {

        String sql =
                "INSERT INTO complaints " +
                "(user_id, title, description, status) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    complaint.getUserId());

            ps.setString(
                    2,
                    complaint.getTitle());

            ps.setString(
                    3,
                    complaint.getDescription());

            ps.setString(
                    4,
                    complaint.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET ONE COMPLAINT
    // ADMIN
    // =====================================================

    public Complaint getComplaintById(
            int complaintId) {

        String sql =
                "SELECT * FROM complaints " +
                "WHERE complaint_id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    complaintId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return extractComplaint(rs);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // GET ALL COMPLAINTS
    // ADMIN
    // =====================================================

    public List<Complaint> getAllComplaints() {

        List<Complaint> complaints =
                new ArrayList<>();

        String sql =
                "SELECT * FROM complaints " +
                "ORDER BY created_date DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                complaints.add(
                        extractComplaint(rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return complaints;
    }

    // =====================================================
    // GET USER'S COMPLAINTS
    // USER
    // =====================================================

    public List<Complaint> getComplaintsByUserId(
            int userId) {

        List<Complaint> complaints =
                new ArrayList<>();

        String sql =
                "SELECT * FROM complaints " +
                "WHERE user_id = ? " +
                "ORDER BY created_date DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    userId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                complaints.add(
                        extractComplaint(rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return complaints;
    }

    // =====================================================
    // DELETE USER'S OWN COMPLAINT
    // =====================================================

    public boolean deleteUserComplaint(
            int complaintId,
            int userId) {

        String sql =
                "DELETE FROM complaints " +
                "WHERE complaint_id = ? " +
                "AND user_id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    complaintId);

            ps.setInt(
                    2,
                    userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // ADMIN DELETE COMPLAINT
    // =====================================================

    public boolean deleteComplaint(
            int complaintId) {

        String sql =
                "DELETE FROM complaints " +
                "WHERE complaint_id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    complaintId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE STATUS
    // ADMIN
    // =====================================================

    public boolean updateStatus(
            int complaintId,
            String status) {

        String sql =
                "UPDATE complaints " +
                "SET status = ? " +
                "WHERE complaint_id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    status);

            ps.setInt(
                    2,
                    complaintId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // RESULTSET → COMPLAINT
    // =====================================================

    private Complaint extractComplaint(
            ResultSet rs)
            throws SQLException {

        Complaint complaint =
                new Complaint();

        complaint.setComplaintId(
                rs.getInt("complaint_id"));

        complaint.setUserId(
                rs.getInt("user_id"));

        complaint.setTitle(
                rs.getString("title"));

        complaint.setDescription(
                rs.getString("description"));

        complaint.setStatus(
                rs.getString("status"));

        complaint.setCreatedDate(
                rs.getTimestamp("created_date"));

        return complaint;
    }
}