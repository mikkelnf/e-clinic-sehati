package com.sehati.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "medicine_supplier_id")
    private MedicineSupplier medicineSupplier;

    private Integer qty;

    private String note;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public MedicineSupplier getMedicineSupplier() {
        return medicineSupplier;
    }

    public void setMedicineSupplier(MedicineSupplier medicineSupplier) {
        this.medicineSupplier = medicineSupplier;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
