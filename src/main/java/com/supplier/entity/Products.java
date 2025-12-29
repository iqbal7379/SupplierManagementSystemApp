package com.supplier.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private double productPrice;
	private int productStackQty;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Orders> orders;
	
	@JoinColumn(name="supplierId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Supplier supplier;
	
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders; 
	}
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductStackQty() {
		return productStackQty;
	}
	public void setProductStackQty(int productStackQty) {
		this.productStackQty = productStackQty;
	}
	
	

}
