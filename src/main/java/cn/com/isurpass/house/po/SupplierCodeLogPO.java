package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suppliercodelog")
public class SupplierCodeLogPO {
    @Id
    private String suppliercode;
    private String usercode;

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public String toString() {
        return "SupplierCodeLogPO{" +
                "suppliercode='" + suppliercode + '\'' +
                ", usercode='" + usercode + '\'' +
                '}';
    }
}
