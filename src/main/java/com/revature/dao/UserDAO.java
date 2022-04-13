package com.revature.dao;

import java.sql.*;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

public class UserDAO {

    public User getUserByUsernameAndPassword(LoginDTO dto) throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {

            String sql = "SELECT u.user_id, u.username, u.password, ur.role, u.first_name, u.last_name, u.email " +
                    "FROM users u " +
                    "INNER JOIN user_roles ur " +
                    "ON u.user_role_id = ur.id " +
                    "WHERE u.username = ? AND u.password = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, dto.getUsername());
            pstmt.setString(2, dto.getPassword());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String user_role = rs.getString("role");

                User retrievedUser = new User();
                retrievedUser.setUser_id(user_id);
                retrievedUser.setUsername(username);
                retrievedUser.setPassword(password);
                retrievedUser.setFirstName(firstName);
                retrievedUser.setLastName(lastName);
                retrievedUser.setEmail(email);
                retrievedUser.setUser_role(user_role);

                return retrievedUser;
            } else {
                return null;
            }
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT * from users2 " +
                    "WHERE users2.username = ? ";

            try( PreparedStatement pstmt = con.prepareStatement(sql)){
                pstmt.setString(1, username);;

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String user_name = rs.getString("username");
                    String password = rs.getString("password");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String role = rs.getString("role");

                    return new User(id, user_name, password, firstName, lastName, email, role);
                }
                return null;
            }
        }
    }



    public User signUp(User user) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            String sql =  "INSERT into users (username, password, first_name, last_name, email, user_role_id) "+
                    "values (?, ?, ?, ?, ?, (SELECT ur.id from user_roles ur where ur.role = ?))";
            try(PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                pstmt.setString(1,user.getUsername());
                pstmt.setString(2,user.getPassword());
                pstmt.setString(3,user.getFirstName());
                pstmt.setString(4,user.getLastName());
                pstmt.setString(5,user.getEmail());
                pstmt.setString(6,user.getUser_role());

                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int userId = rs.getInt(1);

                return new User(userId,user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUser_role());
            }
        }
    }

}