package com.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supplier.dto.OrderStatus;
import com.supplier.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Optional<Orders> findByTrackingNumber(String trackingNumber);

	
	List<Orders> findByTotalAmount(double totalAmount);


	List<Orders> findByOrderStatus(OrderStatus status);

    @Query("select c.orders from Customer c where c.customerId=?1")
	List<Orders> findOrderByCustomer(int customerId);

}
