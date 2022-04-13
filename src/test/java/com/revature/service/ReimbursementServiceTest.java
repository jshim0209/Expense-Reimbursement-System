package com.revature.service;


import com.revature.dao.ReimbursementDAO;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {

    @Mock
    private ReimbursementDAO reimbDao;

    @InjectMocks
    private ReimbursementService reimbService;

    @Test
    public void testGetAllReimbursements() throws SQLException {

        // Arrange
        List<ResponseReimbursementDTO> fakeReimbursements = new ArrayList<>();
        User author = new User(1, "testAuthor", "password", "employee");
        User resolver = new User(2, "testResolver", "password", "finance_manager");

        fakeReimbursements.add(new ResponseReimbursementDTO(1, 300, "currentTime", "description", "firstName", "lastName", 1, 1));
        fakeReimbursements.add(new ResponseReimbursementDTO(2, 400, "currentTime", "description", "firstName", "lastName", 2, 2));
        fakeReimbursements.add(new ResponseReimbursementDTO(3, 500, "currentTime", "description", "firstName", "lastName", 3, 3));

        // Act
        when(reimbDao.getAllReimbursements()).thenReturn(fakeReimbursements);

        List<ResponseReimbursementDTO> actual = reimbService.getAllReimbursements();
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursements);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testGetPendingReimbursements() throws SQLException {

        // Arrange
        List<ResponseReimbursementDTO> fakeReimbursements = new ArrayList<>();
        User author = new User(1, "testAuthor", "password", "employee");
        User resolver = new User(2, "testResolver", "password", "finance_manager");

        fakeReimbursements.add(new ResponseReimbursementDTO(1, 300, "currentTime", "description", "firstName", "lastName", 1, 1));
        fakeReimbursements.add(new ResponseReimbursementDTO(2, 400, "currentTime", "description", "firstName", "lastName", 2, 2));
        fakeReimbursements.add(new ResponseReimbursementDTO(3, 500, "currentTime", "description", "firstName", "lastName", 3, 3));

        // Act
        when(reimbDao.getPendingReimbursements()).thenReturn(fakeReimbursements);

        List<ResponseReimbursementDTO> actual = reimbService.getPendingReimbursements();
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursements);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testGetApprovedReimbursements() throws SQLException {

        // Arrange
        List<ResponseReimbursementDTO> fakeReimbursements = new ArrayList<>();
        User author = new User(1, "testAuthor", "password", "employee");
        User resolver = new User(2, "testResolver", "password", "finance_manager");

        fakeReimbursements.add(new ResponseReimbursementDTO(1, 300, "currentTime", "description", "firstName", "lastName", 1, 1));
        fakeReimbursements.add(new ResponseReimbursementDTO(2, 400, "currentTime", "description", "firstName", "lastName", 2, 2));
        fakeReimbursements.add(new ResponseReimbursementDTO(3, 500, "currentTime", "description", "firstName", "lastName", 3, 3));

        // Act
        when(reimbDao.getApprovedReimbursements()).thenReturn(fakeReimbursements);

        List<ResponseReimbursementDTO> actual = reimbService.getApprovedReimbursements();
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursements);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testGetDeniedReimbursements() throws SQLException {

        // Arrange
        List<ResponseReimbursementDTO> fakeReimbursements = new ArrayList<>();
        User author = new User(1, "testAuthor", "password", "employee");
        User resolver = new User(2, "testResolver", "password", "finance_manager");

        fakeReimbursements.add(new ResponseReimbursementDTO(1, 300, "currentTime", "description", "firstName", "lastName", 1, 1));
        fakeReimbursements.add(new ResponseReimbursementDTO(2, 400, "currentTime", "description", "firstName", "lastName", 2, 2));
        fakeReimbursements.add(new ResponseReimbursementDTO(3, 500, "currentTime", "description", "firstName", "lastName", 3, 3));

        // Act
        when(reimbDao.getDeniedReimbursements()).thenReturn(fakeReimbursements);

        List<ResponseReimbursementDTO> actual = reimbService.getDeniedReimbursements();
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursements);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testAddReimbursement() throws SQLException, IOException, InvalidImageException {
        // Arrange
        User author = new User(1, "testAuthor", "password", "employee");

        InputStream receipt = new FileInputStream("C://Java/receiptImage/receipt-1.jpg");

        when(reimbDao.addReimbursement(1, new AddReimbursementDTO(500, "description",
                receipt, 1))).thenReturn(new Reimbursement(1, 500, "description",
                receipt, 1, 0, 1, 1, "firstName", "lastName",
                author, null));

        ResponseReimbursementDTO actual = reimbService.addReimbursement(1, new AddReimbursementDTO(500, "description",
                receipt, 1));

        ResponseReimbursementDTO expected = new ResponseReimbursementDTO(1, 500, null, "description",
                "firstName", "lastName", 1, 1);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testUpdateReimbursement() throws SQLException {

    }
}
