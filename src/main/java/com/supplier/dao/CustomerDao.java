package com.supplier.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.supplier.entity.Customer;
import com.supplier.entity.Orders;
import com.supplier.repository.CustomerRepository;

@Repository
public class CustomerDao {
	

	@Autowired
	CustomerRepository customerRepository;
	
	 //save customer
    public  Customer saveCustomer(Customer customer) {
    	return customerRepository.save(customer);
    }
    
    //get Customer
    public Optional<Customer> getCustomerById(int id) {
    	return customerRepository.findById(id);
    }
    
    
    //get all Customer
    public List<Customer> getAllCustomer() {
    	return customerRepository.findAll();
    }
    
    
    //update Customer
    public Customer  updateCustomer(Customer customer) {
    	return customerRepository.save(customer);
    }
    
    //delete
    public void deleteCustomer(Customer customer) {
    	 customerRepository.delete(customer);
    }
    
    //get customer by phone number
    public Optional<Customer> getCustomerByPhone(long phone){
    	return customerRepository.findCustomerByPhone(phone);
    }
    
    
    //get Customer By order
    public List<Customer> getCustomerByOrders(int orderId){
    	return customerRepository.findCustomerByOrders(orderId);
    	
    }
    
    //get supplier By pagination and sorting
    public Page<Customer> getCustomerByPaginationAndSorting(int pageNumber,int pageSize,String fields){
    	return customerRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(fields).ascending()
    			));
    }
	
}
