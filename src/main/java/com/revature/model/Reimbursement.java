package com.revature.model;

import java.io.InputStream;
import java.util.Objects;

public class Reimbursement {

    private int id;
    private double amount;
    private String timeSubmitted;
    private String description;
    private InputStream receipt;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private int authorId;
    private int resolverId;
    private int typeId;
    private String type;
    private int statusId;
    private String status;

    private User employee;
    private User finance_manager;

    public Reimbursement() {
    }

    public Reimbursement(int id, double amount, String description, InputStream receipt, int authorId,
                         int resolverId, int typeId, int statusId, String firstName, String lastName,
                         User employee, User finance_manager) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.typeId = typeId;
        this.statusId = statusId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employee = employee;
        this.finance_manager = finance_manager;
    }

    public Reimbursement(int id, double amount, String timeSubmitted, String description,
                         InputStream receipt, String firstName, String lastName, String email, String username,
                         int authorId, int resolverId, int typeId, String type, int statusId, String status,
                         User employee, User finance_manager) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.description = description;
        this.receipt = receipt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.typeId = typeId;
        this.type = type;
        this.statusId = statusId;
        this.status = status;
        this.employee = employee;
        this.finance_manager = finance_manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(String timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getReceipt() {
        return receipt;
    }

    public void setReceipt(InputStream receipt) {
        this.receipt = receipt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getFinance_manager() {
        return finance_manager;
    }

    public void setFinance_manager(User finance_manager) {
        this.finance_manager = finance_manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && authorId == that.authorId && resolverId == that.resolverId && typeId == that.typeId && statusId == that.statusId && Objects.equals(timeSubmitted, that.timeSubmitted) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(type, that.type) && Objects.equals(status, that.status) && Objects.equals(employee, that.employee) && Objects.equals(finance_manager, that.finance_manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, timeSubmitted, description, receipt, firstName, lastName, email, username, authorId, resolverId, typeId, type, statusId, status, employee, finance_manager);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeSubmitted='" + timeSubmitted + '\'' +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", typeId=" + typeId +
                ", type='" + type + '\'' +
                ", statusId=" + statusId +
                ", status='" + status + '\'' +
                ", employee=" + employee +
                ", finance_manager=" + finance_manager +
                '}';
    }
}
