package com.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.supplier.dto.ResponseStructure;
import com.supplier.entity.*;
import com.supplier.service.CustomerService;
 

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	
	@Autowired
    CustomerService customerService;
	
	//save
		@PostMapping
		public ResponseEntity<ResponseStructure<Customer>>saveSupplier(@ RequestBody Customer customer){
			return customerService.saveCustomer(customer);
		}
		
		//get by id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Customer>> getCustomerByid(@PathVariable int id){
			return customerService.getCustomerById(id);
		}
		
		
		//get all supplier
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
			return customerService.getAllCustomer();
		}
		
		
		//update
		@PutMapping
		public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer){
			return customerService.updateCustomer(customer);
		}
		
		
		//get delete
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable int id){
			return customerService.deleteCustomer(id);
		}
		
		
		// get by phone
		@GetMapping("/phone/{phone}")
		public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@PathVariable long phone){
			return customerService.getCustomerByPhone(phone);
		}
		
		
		
		//get Customer by order
		@GetMapping("/orders/{orderId}")
		public ResponseEntity<ResponseStructure<List<Customer>>>getCustomerByOrders(@PathVariable int orderId){
			return customerService.getCustomerByOrders(orderId);
		}
		
		
		//get Customer by pagination and sorting
		@GetMapping("/{pageNumber}/{pageSize}/{fields}")
		public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String fields){
			return customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize, fields);
		}
}
