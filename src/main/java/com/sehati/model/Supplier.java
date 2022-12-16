package com.sehati.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "m_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;

    @NotBlank(message = "{invalid.supplier_name.required}")
    @NotNull
    @Column(name = "supplier_name", unique = true)
    private String supplierName;

//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "supplier")
    private List<MedicineSupplier> medicineSupplierList;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
