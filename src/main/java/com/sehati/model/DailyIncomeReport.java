package com.sehati.model;

import java.util.Date;

public class DailyIncomeReport {
    private Date visitDate;

    private Integer qtySoldMedicine;

    private Integer totalSoldMedicine;

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getQtySoldMedicine() {
        return qtySoldMedicine;
    }

    public void setQtySoldMedicine(Integer qtySoldMedicine) {
        this.qtySoldMedicine = qtySoldMedicine;
    }

    public Integer getTotalSoldMedicine() {
        return totalSoldMedicine;
    }

    public void setTotalSoldMedicine(Integer totalSoldMedicine) {
        this.totalSoldMedicine = totalSoldMedicine;
    }
}
