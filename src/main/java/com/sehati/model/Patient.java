package com.sehati.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "m_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @NotBlank(message = "{invalid.patient_name.required}")
    @NotNull
    @Column(name = "patient_name", unique = true)
    private String patientName;

    @NotBlank(message = "{invalid.patient_address.required}")
    @NotNull
    @Column(name = "patient_address")
    private String patientAddress;

    @NotBlank(message = "{invalid.patient_phone.required}")
    @NotNull
    @Column(name = "patient_phone")
    private String patientPhone;

    @NotNull(message = "{invalid.patient_birthdate.required}")
    @Temporal(TemporalType.DATE)
    @Column(name = "patient_birthdate")
    private Date patientBirthdate;

//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    private List<Visit> visitList;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public Date getPatientBirthdate() {
        return patientBirthdate;
    }

    public void setPatientBirthdate(Date patientBirthdate) {
        this.patientBirthdate = patientBirthdate;
    }
}
