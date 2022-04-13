package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDAO mockDao;

    @InjectMocks
    private UserService userService;

    private LoginDTO dto = new LoginDTO();

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testLogin_positive() throws FailedLoginException, SQLException {
        dto.setUsername("username");
        dto.setPassword("password");

        when(mockDao.getUserByUsernameAndPassword(dto)).thenReturn(
                new User(1, "username", "password", "finance_manager")
        );

        User expected = new User(1, "username", "password", "finance_manager");

        User actual = userService.login(dto);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLogin_negative() {
        Assertions.assertThrows(FailedLoginException.class, () -> {
            this.userService.login(dto);
        });
    }

    @Test
    public void testSignUpPositive() throws SQLException {
        User fakeUser = new User (99,"userName","password","fisrtName",
                "lastName", "email@revature.com","employee");
        when(mockDao.getUserByUsername(eq("userName"))).thenReturn(null);
        when(mockDao.signUp(fakeUser)).thenReturn(fakeUser);
        User actual =  userService.signUp(fakeUser);
        User expected = fakeUser;
        Assertions.assertEquals(expected, actual);
    }
}
