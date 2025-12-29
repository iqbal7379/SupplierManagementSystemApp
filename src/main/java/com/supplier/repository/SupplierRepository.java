package com.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplier.entity.Products;
import com.supplier.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	
	
	@Query("select p.supplier from Products p where p.productId=?1")
	List<Supplier> findSupplierByProducts(int  productsId);
	

}
