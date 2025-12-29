package com.supplier.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplier.entity.Products;
import com.supplier.entity.Supplier;
import com.supplier.repository.SupplierRepository;

@Repository
public class SupplierDao {
    @Autowired
	SupplierRepository supplierRepository;
    
    
    //save
    public Supplier saveSupplier(Supplier supplier) {
    	return supplierRepository.save(supplier);
    }
    
    //get supplier
    public Optional<Supplier> getSupplierById(int id) {
    	return supplierRepository.findById(id);
    }
    
    
    //get all supplier
    public List<Supplier> getAllSupplier() {
    	return supplierRepository.findAll();
    }
    
    
    //update supplier
    public Supplier  updateSupplier(Supplier supplier) {
    	return supplierRepository.save(supplier);
    }
    
    //delete
    public void deleteSupplier(Supplier supplier) {
    	 supplierRepository.delete(supplier);
    }
    
    //get supplier By product
    public List<Supplier> getSupplierByProduct(int productId){
    	return supplierRepository.findSupplierByProducts(productId);
    	
    }
    
    //get supplier By pagination and sorting
    public Page<Supplier> getSupplierByPaginationAndSorting(int pageNumber,int pageSize,String fields){
    	return supplierRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(fields).ascending()
    			));
    }
}
