package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cms.model.User;
import com.cms.util.DBConnection;

public class UserDAO {

    // =====================================================
    // REGISTER USER
    // =====================================================

    public boolean registerUser(User user) {

        String sql =
                "INSERT INTO users " +
                "(name, email, password, role) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    user.getName());

            ps.setString(
                    2,
                    user.getEmail());

            ps.setString(
                    3,
                    user.getPassword());

            ps.setString(
                    4,
                    user.getRole());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                System.out.println(
                        "Email already exists.");

            } else {

                e.printStackTrace();
            }

            return false;
        }
    }

    // =====================================================
    // LOGIN
    // =====================================================

    public User login(
            String email,
            String password) {

        String sql =
                "SELECT * FROM users " +
                "WHERE email = ? AND password = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    email);

            ps.setString(
                    2,
                    password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                User user =
                        new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setName(
                        rs.getString("name"));

                user.setEmail(
                        rs.getString("email"));

                user.setPassword(
                        rs.getString("password"));

                user.setRole(
                        rs.getString("role"));

                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // CHECK IF ADMIN EXISTS
    // =====================================================

    public boolean adminExists() {

        String sql =
                "SELECT COUNT(*) FROM users " +
                "WHERE role = 'ADMIN'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}