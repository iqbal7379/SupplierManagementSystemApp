package com.supplier.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int supplierId;
	private String supplierName;
	private long supplierPhone;
	private String supplierEmail;
	private String companyName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Products> products;
	
	
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public long getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(long supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

}
