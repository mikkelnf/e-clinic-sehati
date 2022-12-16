package com.sehati.model.request;

import javax.validation.constraints.NotNull;

public class TransactionRequest {
    @NotNull(message = "{invalid.visit_id.required}")
    private Integer visitId;

    @NotNull(message = "{invalid.medicine_supplier_id.required}")
    private Integer medicineSupplierId;

    @NotNull(message = "{invalid.transaction_qty.required}")
    private Integer qty;

    private String note;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Integer getMedicineSupplierId() {
        return medicineSupplierId;
    }

    public void setMedicineSupplierId(Integer medicineSupplierId) {
        this.medicineSupplierId = medicineSupplierId;
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
