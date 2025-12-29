package com.supplier.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.supplier.dao.SupplierDao;
import com.supplier.dto.ResponseStructure;
import com.supplier.entity.*;
import com.supplier.exception.*;


@Service
public class SupplierService {

	@Autowired
	SupplierDao supplierDao;
	//save
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier){
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Record saved");
		response.setData(supplierDao.saveSupplier(supplier));
		return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.CREATED);
	}
	
	//getById
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(int id){
	Optional<Supplier> optional =supplierDao.getSupplierById(id);
	ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
	if (optional.isPresent()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Supplier fetch by Id ");
		response.setData(optional.get());
		return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
	}
	else {
		throw new IdNotFoundException("unable to fetch since id is invalid");
		}
	}
	
	//getAllSupplier
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
		List<Supplier> supplier= supplierDao.getAllSupplier();
		ResponseStructure<List<Supplier>> response=new ResponseStructure<List<Supplier>>();
		if (supplier.size()>0) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("fetch all supplier");
		response.setData(supplier);
		return new ResponseEntity<ResponseStructure<List<Supplier>>>(response,HttpStatus.OK);
		}
		else{
			throw new NotRecordFoundExecption("Not able to fetch recored");
			}
		}
	
	//update supplier
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier){
		Optional<Supplier> optional=supplierDao.getSupplierById(supplier.getSupplierId());
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		if (optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Supplier updated");
			response.setData(supplierDao.updateSupplier(supplier));
			return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("unable to update since id is invalid");
			}
		}
	
	
	
	//delete supplier
	public ResponseEntity<ResponseStructure<Supplier>> deleteSuppliey(int id){
		Optional<Supplier> option=supplierDao.getSupplierById(id);
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		if (option.isPresent()) {
		supplierDao.deleteSupplier(option.get());
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Delete supplier");
		
		return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
		}
		else{
			throw new IdNotFoundException("Not able to delete  recored since id is invalid");
			}
		}
	
	
	//get supplier by product
		public ResponseEntity<ResponseStructure<List<Supplier>>>getSupplierByProduct(int productId){
			List<Supplier> supplier=supplierDao.getSupplierByProduct(productId);
			ResponseStructure<List<Supplier>> response=new ResponseStructure<List<Supplier>>();
			if (supplier.size()>0) {
				response.setStatusCode(HttpStatus.OK.value());
			    response.setMessage("fetch supplier by product");
				response.setData(supplier);
				return new ResponseEntity<ResponseStructure<List<Supplier>>>(response,HttpStatus.OK);
			}
			else{
				throw new NotRecordFoundExecption("Not able to fetch recored");
				}
			}
		
		
		//get supplier by pagination and sorting
		public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(int pageNumber,int pageSize,String fields){
			Page<Supplier> pages=supplierDao.getSupplierByPaginationAndSorting(pageNumber, pageSize, fields);
			ResponseStructure<Page<Supplier>> response=new ResponseStructure<Page<Supplier>>();
			if (!pages.isEmpty()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("supplier fetched successfully by page and sorting");
				response.setData(pages);
				return new ResponseEntity<ResponseStructure<Page<Supplier>>>(response,HttpStatus.OK);
			}
			else {
				throw new NotRecordFoundExecption("not record found");
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

