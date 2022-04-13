package com.revature.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class UpdateReimbursementStatusDTO {

    private int resolverId;
    private int statusId;
    private Timestamp timeResolved;

    public UpdateReimbursementStatusDTO() {
    }

    public UpdateReimbursementStatusDTO(int resolverId, int statusId, Timestamp timeResolved) {
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.timeResolved = timeResolved;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTimeResolved() {
        return timeResolved;
    }

    public void setTimeResolved(Timestamp timeResolved) {
        this.timeResolved = timeResolved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateReimbursementStatusDTO that = (UpdateReimbursementStatusDTO) o;
        return resolverId == that.resolverId && statusId == that.statusId && Objects.equals(timeResolved, that.timeResolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resolverId, statusId, timeResolved);
    }

    @Override
    public String toString() {
        return "UpdateReimbursementStatusDTO{" +
                "resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", timeResolved=" + timeResolved +
                '}';
    }
}





