package com.sehati.model.request;

import javax.validation.constraints.NotNull;

public class MedicinePriceRequest {
    @NotNull(message = "{invalid.medicine_price.required}")
    private Integer medicinePrice;

    public Integer getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Integer medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
}
