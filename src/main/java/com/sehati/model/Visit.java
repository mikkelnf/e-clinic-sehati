package com.sehati.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visitId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Temporal(TemporalType.DATE)
    @Column(name = "visit_date", nullable = false)
    private Date visitDate;

//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "visit")
    private List<Transaction> transactionList;

    private String complaints;

    private String note;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
