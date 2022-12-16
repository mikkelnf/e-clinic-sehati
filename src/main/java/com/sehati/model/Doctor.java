package com.sehati.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name= "m_doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @NotBlank(message = "{invalid.doctor_name.required}")
    @NotNull
    @Column(name = "doctor_name", unique = true)
    private String doctorName;

    @NotBlank(message = "{invalid.doctor_phone.required}")
    @NotNull
    @Column(name = "doctor_phone")
    private String doctorPhone;

    @Column(name = "is_active")
    private Boolean isActive = true;

//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "doctor")
    private List<Visit> visitList;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
