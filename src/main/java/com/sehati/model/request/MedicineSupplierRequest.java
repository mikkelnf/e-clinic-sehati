package com.sehati.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MedicineSupplierRequest {
    @NotBlank(message = "{invalid.medicine_name.required}")
    @NotNull
    private String medicineName;

    @NotNull(message = "{invalid.supplier_id.required}")
    private Integer supplierId;

    @NotNull(message = "{invalid.medicine_price.required}")
    private Integer medicinePrice;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Integer medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
}
