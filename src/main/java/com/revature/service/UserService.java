package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.model.User;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public UserService(UserDAO mockDao) {
        this.userDao = mockDao;
    }

    public User login(LoginDTO dto) throws SQLException, FailedLoginException {
        User user = this.userDao.getUserByUsernameAndPassword(dto);

        if (user == null) {
            throw new FailedLoginException("Invalid username and/or password was provided");
        }
        return user;
    }

    public User signUp(User user) throws SQLException {

        User userExists = this.userDao.getUserByUsername(user.getUsername());

        if(userExists!=null){
            throw new IllegalArgumentException("The username already exists");
        }
        user.setUser_role(user.getUser_role());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        /*
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
         */

        return this.userDao.signUp(user);
    }
}