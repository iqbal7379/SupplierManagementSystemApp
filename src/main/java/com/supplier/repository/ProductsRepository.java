package com.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplier.entity.Products;
import com.supplier.entity.Supplier;

public interface ProductsRepository extends JpaRepository<Products, Integer>{

	
     
	List<Products> findBySupplier(Supplier supplierId);


	List<Products> findByProductStackQty(int qty);

}
