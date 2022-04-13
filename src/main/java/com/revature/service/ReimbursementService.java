package com.revature.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import com.revature.exception.ImageNotFoundException;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.dao.ReimbursementDAO;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.dto.UpdateReimbursementStatusDTO;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;

public class ReimbursementService {
    Logger logger = LoggerFactory.getLogger(ReimbursementService.class);

    private ReimbursementDAO reimbDao;

    public ReimbursementService() {
        this.reimbDao = new ReimbursementDAO();
    }

    public ReimbursementService(ReimbursementDAO mockDao) {
        this.reimbDao = mockDao;
    }


    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        return reimbDao.getAllReimbursements();
    }

    public List<ResponseReimbursementDTO> getAllReimbursementsbyUserId(String userId) throws SQLException {
        try {
            int uId = Integer.parseInt(userId);
            return reimbDao.getAllReimbursementsByUserId(uId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid integer given for request.");
        }
    }

    public List<ResponseReimbursementDTO> getPendingReimbursements() throws SQLException {
        return reimbDao.getPendingReimbursements();
    }

    public List<ResponseReimbursementDTO> getApprovedReimbursements() throws SQLException {
        return reimbDao.getApprovedReimbursements();
    }

    public List<ResponseReimbursementDTO> getDeniedReimbursements() throws SQLException {
        return reimbDao.getDeniedReimbursements();
    }

    public ResponseReimbursementDTO addReimbursement(int id, AddReimbursementDTO dto) throws SQLException, InvalidImageException, IOException {

        Tika tika = new Tika();
        String mimeType = tika.detect(dto.getReceipt());

        if(!mimeType.equals("image/jpeg")  && !mimeType.equals("image/gif") && !mimeType.equals("image/png")){
            throw new InvalidImageException("Image must be a .jpg, .gif, or a .png");
        }

        Reimbursement reimbursementAdded = this.reimbDao.addReimbursement(id, dto);
        logger.info("User added new reimbursement");
        System.out.println(id);


        return new ResponseReimbursementDTO(reimbursementAdded.getId(), reimbursementAdded.getAmount(),
                reimbursementAdded.getTimeSubmitted(), reimbursementAdded.getDescription(),
                reimbursementAdded.getFirstName(), reimbursementAdded.getLastName(), reimbursementAdded.getStatusId(),
                reimbursementAdded.getTypeId());
    }

    public boolean updateReimbursementStatus(UpdateReimbursementStatusDTO dto, String reimbId) throws
            SQLException {

        int rId = Integer.parseInt(reimbId);
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        Timestamp submitted = Timestamp.valueOf(LocalDateTime.now());
        dto.setTimeResolved(submitted);
        logger.info("User updated the status of reimbursement with an id of " + reimbId + " to " + dto.getStatusId() + ".");


        return reimbDao.updateReimbursementStatus(dto, rId);
    }

    public InputStream getReceiptFromReimbursementById(String reimbId) throws SQLException, ImageNotFoundException{
        try {
            int rId = Integer.parseInt(reimbId);

            System.out.println(reimbId);

            InputStream is = this.reimbDao.getReceiptFromReimbursementById(rId);

            if (is == null) {
                throw new ImageNotFoundException("Reimbursement id " + reimbId + " does not have an image");
            }

            return is;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Reimbursement and/or user id must be an int value");
        }
    }
}