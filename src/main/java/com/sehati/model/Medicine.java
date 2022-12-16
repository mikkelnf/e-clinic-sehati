package com.sehati.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "m_medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Integer medicineId;

    @Column(name = "medicine_name", unique = true)
    private String medicineName;
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "medicine")
    private List<MedicineSupplier> medicineSupplierList;
    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
