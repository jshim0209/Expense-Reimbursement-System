package com.revature.dto;


import java.util.Objects;

public class ResponseReimbursementDTO {

    private int id;
    private double amount;
    private String timeSubmitted;
    private String description;
    private String firstName;
    private String lastName;
    private int statusId;
    private String status;
    private int typeId;
    private String type;

    public void ResponseRimbursementDTO() {
    }

    public ResponseReimbursementDTO(int id, double amount, String timeSubmitted, String description,
                                    String firstName, String lastName, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public ResponseReimbursementDTO(int id, double amount, String timeSubmitted, String description,
                                    String firstName, String lastName, String status, String type) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.type = type;
    }

    public ResponseReimbursementDTO(int id, double amount, String timeSubmitted, String description, String firstName,
                                    String lastName, int statusId, String status, int typeId, String type) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.statusId = statusId;
        this.status = status;
        this.typeId = typeId;
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseReimbursementDTO that = (ResponseReimbursementDTO) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && statusId == that.statusId && typeId == that.typeId && Objects.equals(timeSubmitted, that.timeSubmitted) && Objects.equals(description, that.description) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(status, that.status) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, timeSubmitted, description, firstName, lastName, statusId, status, typeId, type);
    }

    @Override
    public String toString() {
        return "ResponseReimbursementDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeSubmitted='" + timeSubmitted + '\'' +
                ", description='" + description + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", statusId=" + statusId +
                ", status='" + status + '\'' +
                ", typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }
}