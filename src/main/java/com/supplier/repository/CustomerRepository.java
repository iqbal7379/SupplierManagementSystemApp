package com.supplier.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplier.entity.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select o.customer from Orders o where o.oderId=?1")
	List<Customer> findCustomerByOrders(int orderId);
	
	@Query("select c from Customer c where c.customerPhone=?1")
	Optional<Customer> findCustomerByPhone(long phone);
    

}
