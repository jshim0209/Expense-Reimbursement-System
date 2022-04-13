package com.revature.dto;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Objects;

public class AddReimbursementDTO {

    private double amount;
    private String description;
    private InputStream receipt;
    private int typeId;

    public AddReimbursementDTO() {
    }

    public AddReimbursementDTO(double amount, String description, InputStream receipt, int typeId) {
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.typeId = typeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursementDTO that = (AddReimbursementDTO) o;
        return Double.compare(that.amount, amount) == 0 && typeId == that.typeId && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, receipt, typeId);
    }

    @Override
    public String toString() {
        return "AddReimbursementDTO{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", typeId=" + typeId +
                '}';
    }
}