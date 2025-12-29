package com.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.dto.ResponseStructure;
import com.supplier.entity.Supplier;
import com.supplier.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	//save
	@PostMapping
	public ResponseEntity<ResponseStructure<Supplier>>saveSupplier(@ RequestBody Supplier supplier){
		return supplierService.saveSupplier(supplier);
	}
	
	//get by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierByid(@PathVariable int id){
		return supplierService.getSupplierById(id);
	}
	
	
	//get all supplier
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		return supplierService.getAllSupplier();
	}
	
	
	//update
	@PutMapping
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier){
		return supplierService.updateSupplier(supplier);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(@PathVariable int id){
		return supplierService.deleteSuppliey(id);
	}
	
	
	//get supplier by products
	@GetMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<List<Supplier>>> getSupplierByProducts(@PathVariable int productId ){
		return supplierService.getSupplierByProduct(productId);
		}
	
	//pagination and sorting
	@GetMapping("/{pageNumber}/{pageSize}/{fields}")
	public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting
	(@PathVariable int pageNumber, @PathVariable int pageSize,@PathVariable String fields){
		return supplierService.getSupplierByPaginationAndSorting(pageNumber, pageSize, fields);
	}

}
