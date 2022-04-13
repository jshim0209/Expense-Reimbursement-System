package com.revature.controller;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.dto.UpdateReimbursementStatusDTO;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
        this.jwtService = JWTService.getInstance();
    }
    // Finance Manager Only
    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("finance_manager")) {
            throw new UnauthorizedResponse("You must be a finance manager to access this endpoint");
        }
        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();

        ctx.status(200);
        ctx.json(reimbursements);
    };

    // Employee Only
    private Handler getAllReimbursementsByUserId = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String userId = ctx.pathParam("user_id");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot obtain reimbursements that don't belong to yourself");
        }

        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursementsbyUserId(userId);
        ctx.json(reimbursements);
    };

    private Handler addReimbursement = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("employee")) {
            throw new UnauthorizedResponse("You must be a employee to access this endpoint");
        }
        String userId = ctx.pathParam("user_id");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot add a reimbursement request for an employee other than yourself");
        }

        System.out.println(id);

        String description = ctx.formParam("description");
        String reimbAmount = ctx.formParam("amount");
        double amount = Double.parseDouble(reimbAmount);
        String type_id = ctx.formParam("typeId");
        int typeId = Integer.parseInt(type_id);
        UploadedFile file = ctx.uploadedFile("receipt");
        InputStream is = file.getContent();

        System.out.println(amount);
        System.out.println(reimbAmount);

        AddReimbursementDTO dto = new AddReimbursementDTO();

        dto.setDescription(description);
        dto.setAmount(amount);
        dto.setTypeId(typeId);
        dto.setReceipt(is);

        ResponseReimbursementDTO addedReimbursement = this.reimbursementService.addReimbursement(id, dto);

        System.out.println(id);

        ctx.status(201);
        ctx.json(addedReimbursement);
    };

    private Handler getPendingReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("finance_manager")) {
            throw new UnauthorizedResponse("You must be a finance manager to access this endpoint");
        }

        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getPendingReimbursements();
        ctx.json(reimbursements);
    };

    private Handler getApprovedReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("finance_manager")) {
            throw new UnauthorizedResponse("You must be a finance manager to access this endpoint");
        }

        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getApprovedReimbursements();
        ctx.json(reimbursements);
    };

    private Handler getDeniedReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("finance_manager")) {
            throw new UnauthorizedResponse("You must be a finance manager to access this endpoint");
        }

        List<ResponseReimbursementDTO> reimbursements = this.reimbursementService.getDeniedReimbursements();
        ctx.json(reimbursements);
    };

    // Finance Manager Only
    private Handler updateReimbursementStatus =(ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("finance_manager")) {
            throw new UnauthorizedResponse("You must be a finance manager to access this endpoint");
        }

        String reimbId = ctx.pathParam("reimb_id");
        UpdateReimbursementStatusDTO dto = ctx.bodyAsClass(UpdateReimbursementStatusDTO.class);
        dto.setResolverId((Integer) token.getBody().get("user_id"));
        boolean success = reimbursementService.updateReimbursementStatus(dto, reimbId);

        ctx.status(201);
        ctx.json(success);
    };

    private Handler getReceiptFromReimbursementById = ctx -> {

        String reimbId = ctx.pathParam("reimb_id");
        InputStream receipt = this.reimbursementService.getReceiptFromReimbursementById(reimbId);

        System.out.println(reimbId);

        Tika tika = new Tika();
        String mimeType = tika.detect(receipt);

        ctx.header("Content-Type", mimeType); // tell the client what type of image is being sent in the response
        ctx.result(receipt);
    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements); // For finance manager
        app.get("/users/{user_id}/reimbursements", getAllReimbursementsByUserId); // For specific employee
        app.post("/users/{user_id}/reimbursements", addReimbursement); // Add a new reimbursement
        app.get("/reimbursements/pending", getPendingReimbursements); // For finance manager sort by status
        app.get("/reimbursements/approved", getApprovedReimbursements); // For finance manager sort by status
        app.get("/reimbursements/denied", getDeniedReimbursements); // For finance manager sort by status
        app.patch("/reimbursements/{reimb_id}", updateReimbursementStatus); // Update the Reimbursement Status
        app.get("/reimbursements/{reimb_id}/receipt", getReceiptFromReimbursementById); // Obtain receipt for reimbursement

    }
}