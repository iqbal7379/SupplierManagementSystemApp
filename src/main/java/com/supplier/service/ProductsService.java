package com.supplier.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplier.dao.ProductsDao;
import com.supplier.dao.SupplierDao;
import com.supplier.dto.ResponseStructure;
import com.supplier.entity.Products;
import com.supplier.entity.Supplier;
import com.supplier.exception.IdNotFoundException;
import com.supplier.exception.NotRecordFoundExecption;

@Service
public class ProductsService {
	
	@Autowired
	ProductsDao productsDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	//Add Products
	public ResponseEntity<ResponseStructure<Products>> addProducts(int supplierId,Products products) {
		Optional<Supplier> optional=supplierDao.getSupplierById(supplierId);
		ResponseStructure<Products> response=new ResponseStructure<Products>();
		
		if (optional.isPresent()) {
			Supplier supplier=optional.get();
			products.setSupplier(supplier);
			
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Product add successfull");
			response.setData(productsDao.addProducts(products));
			return new ResponseEntity<ResponseStructure<Products>>(response,HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException("Id not found");
		}
	}
	
	
	   //fetch By Id
		public ResponseEntity<ResponseStructure<Products>> getProductsByid(int id){
			Optional<Products> optional=productsDao.getProductsById(id);
			ResponseStructure<Products> response=new ResponseStructure<Products>();
			if (optional.isPresent()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Products fetch by Id ");
				response.setData(optional.get());
				return new ResponseEntity<ResponseStructure<Products>>(response,HttpStatus.OK);
			}
			else {
				throw new IdNotFoundException("unable to fetch Product since id is invalid");
				}
			}
		
		
		
		//fetch all Products
		public ResponseEntity<ResponseStructure<List<Products>>> getAllProducts(){
			List<Products> products= productsDao.getAllProducts();
			ResponseStructure<List<Products>> response=new ResponseStructure<List<Products>>();
			if (products.size()>0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch all Products successfully");
			response.setData(products);
			return new ResponseEntity<ResponseStructure<List<Products>>>(response,HttpStatus.OK);
			}
			else{
				throw new NotRecordFoundExecption("Not able to fetch the recored");
				}
			}
		
		
		// Update product
		public ResponseEntity<ResponseStructure<Products>> updateProducts(Products products){
			Optional<Products> optional=productsDao.getProductsById(products.getProductId());
			ResponseStructure<Products> response=new ResponseStructure<Products>();
			if (optional.isPresent()) {
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setMessage("Product add updated");
				response.setData(productsDao.updateProducts(products));
				return new ResponseEntity<ResponseStructure<Products>>(response,HttpStatus.OK);
			}
			else {
				throw new IdNotFoundException("Id not found");
			}
			
		}
		
		
		 //delete product
				public ResponseEntity<ResponseStructure<Products>> deleteProducts(int id){
					Optional<Products> option=productsDao.getProductsById(id);
					ResponseStructure<Products> response=new ResponseStructure<Products>();
					if (option.isPresent()) {
					productsDao.deleteProducts(option.get());
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Delete Products");
					
					return new ResponseEntity<ResponseStructure<Products>>(response,HttpStatus.OK);
					}
					else{
						throw new IdNotFoundException("Not able to delete  recored since id is invalid");
						}
					}
				
				
				
				//get product by supplier
				public ResponseEntity<ResponseStructure<List<Products>>> getProductsBySupplier(Supplier supplierId){
					Optional<Supplier> optional=supplierDao.getSupplierById(supplierId.getSupplierId());
					ResponseStructure<List<Products>> response=new ResponseStructure<List<Products>>();
					if (optional.isPresent()) {
						response.setStatusCode(HttpStatus.OK.value());
						response.setMessage("product fetch by supplier ");
						response.setData(productsDao.getProductsBySupplier(supplierId));
						return new ResponseEntity<ResponseStructure<List<Products>>>(response,HttpStatus.OK);
					}
					else {
						throw new NotRecordFoundExecption("unable to fetch  product since phone number is invalid");
						}
					}
				
				
				
				//get product by Stack Qty
				public ResponseEntity<ResponseStructure<List<Products>>> getProductsByStackQty(int qty){
					List<Products> products=productsDao.getProductsByStackQty(qty);
					ResponseStructure<List<Products>> response=new ResponseStructure<List<Products>>();
					if (products.size()>0) {
						response.setStatusCode(HttpStatus.OK.value());
						response.setMessage("products fetch by quantity ");
						response.setData(products);
						return new ResponseEntity<ResponseStructure<List<Products>>>(response,HttpStatus.OK);
					}
					else{
						throw new NotRecordFoundExecption("unable to fetch since phone is invalid");
					}
				}
				
				

			   	//get Products by pagination and sorting
					public ResponseEntity<ResponseStructure<Page<Products>>> getProductsByPaginationAndSorting(int pageNumber,int pageSize,String fields){
						Page<Products> pages=productsDao.getProductsByPaginationAndSorting(pageNumber, pageSize, fields);
						ResponseStructure<Page<Products>> response=new ResponseStructure<Page<Products>>();
						if (!pages.isEmpty()) {
							response.setStatusCode(HttpStatus.OK.value());
							response.setMessage("Products fetched successfully by page and sorting");
							response.setData(pages);
							return new ResponseEntity<ResponseStructure<Page<Products>>>(response,HttpStatus.OK);
						}
						else {
							throw new NotRecordFoundExecption("not record found");
							}
						}
					}
	
		
		
	
	
	
	
	