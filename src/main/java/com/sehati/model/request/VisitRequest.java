package com.sehati.model.request;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class VisitRequest {
    @NotNull(message = "{invalid.doctor_id.required}")
    private Integer doctorId;

    @NotNull(message = "{invalid.patient_id.required}")
    private Integer patientId;

    @NotNull(message = "{invalid.visit_date.required}")
    private Date visitDate;

    private String complaints;

    private String note;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
