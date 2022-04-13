package com.revature.dao;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.dto.UpdateReimbursementStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {


    public Reimbursement addReimbursement(int authorId, AddReimbursementDTO dto) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false); // We could set autocommit to false, and at the end, commit the changes

            String sql = "INSERT INTO reimbursements " +
                    "(amount, description, receipt, author_id, type_id) " +
                    "VALUES (?, ?, ?, ?, ?) ";

            try(PreparedStatement pstmt1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt1.setDouble(1, dto.getAmount());
                pstmt1.setString(2, dto.getDescription());
                pstmt1.setBinaryStream(3, dto.getReceipt());
                pstmt1.setInt(4, authorId);
                pstmt1.setInt(5, dto.getTypeId());

                pstmt1.executeUpdate();

                ResultSet rs = pstmt1.getGeneratedKeys();
                rs.next();

                System.out.println(dto.getReceipt());

                int rId = rs.getInt(1); // Our automatically generated id

                String sql2 = "SELECT * " +
                        "FROM users " +
                        "WHERE user_id = ? ";

                try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
                    pstmt2.setInt(1, authorId);

                    ResultSet rs2 = pstmt2.executeQuery();
                    rs2.next();

                    int employeeId = rs2.getInt("user_id");
                    String firstName = rs2.getString("first_name");
                    String lastName = rs2.getString("last_name");
                    String employeeUsername = rs2.getString("username");
                    String employeePassword = rs2.getString("password");
                    String userRole = "employee";

                    User employee = new User(employeeId, employeeUsername, employeePassword, userRole);


                    Reimbursement reimbursement = new Reimbursement(rId, dto.getAmount(),
                            dto.getDescription(), dto.getReceipt(), authorId, 0,
                            dto.getTypeId(), 1, firstName, lastName, employee, null);

                    con.commit();
                    return reimbursement;
                }
            }
        }
    }


    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT * " +
                    "FROM reimbursements2 " +
                    "ORDER BY reimb_id ";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

            while (rs.next()) {
                // Reimbursements
                int id = rs.getInt("reimb_id");
                double amount = rs.getDouble("amount");
                String timeSubmitted = rs.getString("reimb_submitted");
                String description = rs.getString("description");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String status = rs.getString("status");
                String type = rs.getString("type");

                ResponseReimbursementDTO reimbursement = new ResponseReimbursementDTO(id, amount, timeSubmitted,
                        description, firstName, lastName, status, type);
                reimbursements.add(reimbursement);
            }

            return reimbursements;

        }
    }

    public List<ResponseReimbursementDTO> getAllReimbursementsByUserId(int user_id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT * " +
                    "FROM users " +
                    "WHERE user_id = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user_id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            String username = rs.getString("email");

            String sql2 = "SELECT * " +
                    "FROM reimbursements2 rt " +
                    "WHERE rt.email = ? ";

            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString(1, username);
            ResultSet rs2 = pstmt2.executeQuery();

            List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

            while (rs2.next()) {
                // Reimbursements
                int id = rs2.getInt("reimb_id");
                double amount = rs2.getDouble("amount");
                String timeSubmitted = rs2.getString("reimb_submitted");
                String description = rs2.getString("description");
                String firstName = rs2.getString("first_name");
                String lastName = rs2.getString("last_name");
                String status = rs2.getString("status");
                String type = rs2.getString("type");


                ResponseReimbursementDTO reimbursement = new ResponseReimbursementDTO(id, amount, timeSubmitted,
                        description, firstName, lastName, status, type);
                reimbursements.add(reimbursement);
            }

            return reimbursements;
        }
    }

    public List<ResponseReimbursementDTO> getPendingReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT * " +
                    "FROM reimbursements2 " +
                    "WHERE status = 'pending' " +
                    "ORDER BY reimb_id ";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

            while (rs.next()) {
                // Reimbursements
                int id = rs.getInt("reimb_id");
                double amount = rs.getDouble("amount");
                String timeSubmitted = rs.getString("reimb_submitted");
                String description = rs.getString("description");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String rStatus = rs.getString("status");
                String type = rs.getString("type");


                ResponseReimbursementDTO reimbursement = new ResponseReimbursementDTO(id, amount, timeSubmitted,
                        description, firstName, lastName, rStatus, type);
                reimbursements.add(reimbursement);
            }

            return reimbursements;

        }
    }

    public List<ResponseReimbursementDTO> getApprovedReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT * " +
                    "FROM reimbursements2 " +
                    "WHERE status = 'approved' " +
                    "ORDER BY reimb_id ";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

            while (rs.next()) {
                // Reimbursements
                int id = rs.getInt("reimb_id");
                double amount = rs.getDouble("amount");
                String timeSubmitted = rs.getString("reimb_submitted");
                String description = rs.getString("description");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String rStatus = rs.getString("status");
                String type = rs.getString("type");


                ResponseReimbursementDTO reimbursement = new ResponseReimbursementDTO(id, amount, timeSubmitted,
                        description, firstName, lastName, rStatus, type);
                reimbursements.add(reimbursement);
            }

            return reimbursements;

        }
    }

    public List<ResponseReimbursementDTO> getDeniedReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT * " +
                    "FROM reimbursements2 " +
                    "WHERE status = 'denied' " +
                    "ORDER BY reimb_id ";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            List<ResponseReimbursementDTO> reimbursements = new ArrayList<>();

            while (rs.next()) {
                // Reimbursements
                int id = rs.getInt("reimb_id");
                double amount = rs.getDouble("amount");
                String timeSubmitted = rs.getString("reimb_submitted");
                String description = rs.getString("description");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String rStatus = rs.getString("status");
                String type = rs.getString("type");


                ResponseReimbursementDTO reimbursement = new ResponseReimbursementDTO(id, amount, timeSubmitted,
                        description, firstName, lastName, rStatus, type);
                reimbursements.add(reimbursement);
            }

            return reimbursements;

        }
    }

    public boolean updateReimbursementStatus(UpdateReimbursementStatusDTO dto, int rId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false);
            String sql = "UPDATE reimbursements  " +
                    "SET status_id = ?, reimb_resolved = ?, resolver_id = ?  " +
                    "WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, dto.getStatusId());
            pstmt.setTimestamp(2, dto.getTimeResolved());
            pstmt.setInt(3, dto.getResolverId());
            pstmt.setInt(4, rId);

            if (pstmt.executeUpdate() == 1) {
                con.commit();
                return true;
            } else {
                return false;
            }

        }
    }

    public InputStream getReceiptFromReimbursementById(int reimbId) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT receipt " +
                    "FROM reimbursements " +
                    "WHERE id = ? ";

            try(PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, reimbId);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    InputStream is = rs.getBinaryStream("receipt");

                    return is;
                } else {
                    return null;
                }
            }

        }
    }
}
