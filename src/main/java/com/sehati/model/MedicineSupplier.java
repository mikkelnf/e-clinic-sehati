package com.sehati.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "medicine_supplier")
public class MedicineSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_supplier_id")
    private Integer medicineSupplierId;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "medicine_price")
    private Integer medicinePrice;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "medicineSupplier")
    private List<Transaction> transactionList;

    public Integer getMedicineSupplierId() {
        return medicineSupplierId;
    }

    public void setMedicineSupplierId(Integer medicineSupplierId) {
        this.medicineSupplierId = medicineSupplierId;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Integer medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
