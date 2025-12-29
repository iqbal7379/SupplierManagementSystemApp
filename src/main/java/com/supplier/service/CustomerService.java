package com.supplier.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.supplier.dao.CustomerDao;
import com.supplier.dto.ResponseStructure;
import com.supplier.entity.*;
import com.supplier.exception.*;




@Service
public class CustomerService {
	
     @Autowired
     CustomerDao customerDao;
	
	
	//save
		public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer Customer){
			ResponseStructure<Customer> response=new ResponseStructure<Customer>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Record saved");
			response.setData(customerDao.saveCustomer(Customer));
			return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.CREATED);
		}
		
		//getById
		public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id){
		Optional<Customer> optional =customerDao.getCustomerById(id);
		ResponseStructure<Customer> response=new ResponseStructure<Customer>();
		if (optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Customer fetch by Id ");
			response.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("unable to fetch since id is invalid");
			}
		}
		
		//getAllCustomer
		public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
			List<Customer> customer= customerDao.getAllCustomer();
			ResponseStructure<List<Customer>> response=new ResponseStructure<List<Customer>>();
			if (customer.size()>0) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch all Customer");
			response.setData(customer);
			return new ResponseEntity<ResponseStructure<List<Customer>>>(response,HttpStatus.OK);
			}
			else{
				throw new NotRecordFoundExecption("Not able to fetch the recored");
				}
			}
		
		//update Customer
		public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer){
			Optional<Customer> optional=customerDao.getCustomerById(customer.getCustomerId());
			ResponseStructure<Customer> response=new ResponseStructure<Customer>();
			if (optional.isPresent()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Customer updated");
				response.setData(customerDao.updateCustomer(customer));
				return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.OK);
			}
			else {
				throw new IdNotFoundException("unable to update since id is invalid");
				}
			}
		
		
		
		//delete Customer
		public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id){
			Optional<Customer> option=customerDao.getCustomerById(id);
			ResponseStructure<Customer> response=new ResponseStructure<Customer>();
			if (option.isPresent()) {
			customerDao.deleteCustomer(option.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Delete Customer");
			
			return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.OK);
			}
			else{
				throw new IdNotFoundException("Not able to delete  recored since id is invalid");
				}
			}
		
		
		
		
		//get customer by phone
		public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(long phone){
			Optional<Customer> optional =customerDao.getCustomerByPhone(phone);
			ResponseStructure<Customer> response=new ResponseStructure<Customer>();
			if (optional.isPresent()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Customer fetch by phone ");
				response.setData(optional.get());
				return new ResponseEntity<ResponseStructure<Customer>>(response,HttpStatus.OK);
			}
			else {
				throw new NotRecordFoundExecption("unable to fetch since phone is invalid");
				}
			}
		
		//get Customer by order
			public ResponseEntity<ResponseStructure<List<Customer>>>getCustomerByOrders(int orderId){
				List<Customer> customer=customerDao.getCustomerByOrders(orderId);
				ResponseStructure<List<Customer>> response=new ResponseStructure<List<Customer>>();
				if (!customer.isEmpty()) {
					response.setStatusCode(HttpStatus.OK.value());
				    response.setMessage("fetch Customer by product");
					response.setData(customer);
					return new ResponseEntity<ResponseStructure<List<Customer>>>(response,HttpStatus.OK);
				}
				else{
					throw new NotRecordFoundExecption("Not able to fetch recored");
					}
				}
			
			
			//get Customer by pagination and sorting
			public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(int pageNumber,int pageSize,String fields){
				Page<Customer> pages=customerDao.getCustomerByPaginationAndSorting(pageNumber, pageSize, fields);
				ResponseStructure<Page<Customer>> response=new ResponseStructure<Page<Customer>>();
				if (!pages.isEmpty()) {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Customer fetched successfully by page and sorting");
					response.setData(pages);
					return new ResponseEntity<ResponseStructure<Page<Customer>>>(response,HttpStatus.OK);
				}
				else {
					throw new NotRecordFoundExecption("not record found");
				}
			}
			
			
			
			
			
		
}
