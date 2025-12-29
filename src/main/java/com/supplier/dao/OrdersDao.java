package com.supplier.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplier.dto.OrderStatus;
import com.supplier.entity.Orders;
import com.supplier.repository.OrdersRepository;

@Repository
public class OrdersDao {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	//add order
	public  Orders addOrders(Orders orders) {
		return ordersRepository.save(orders);
		
	}
	
	//fetch order by id
	public Optional<Orders> getOrderById(int orderId){
		return ordersRepository.findById(orderId);
	}
	
	//fetch all orders
	public List<Orders> getAllOrders(){
		return ordersRepository.findAll();
	}
	
	//update orders
	public Orders updateOrders(Orders orders) {
		return ordersRepository.save(orders);
	}
	
	//delete orders
	public void deleteOrders(Orders orderId) {
		ordersRepository.delete(orderId);
	}
	
	// fetch order by tracking number
	public Optional<Orders> getOrderByTrackingNumber(String trackingNumber){
		return ordersRepository.findByTrackingNumber(trackingNumber);
	}
	
	
	//fetch order Greater than amount
	public List<Orders> getOrdersGreaterThanAmount(double totalAmount){
		return ordersRepository.findByTotalAmount(totalAmount);
	}
	
	// fetch order by status
	public List<Orders> getOrderByStatus(OrderStatus status){
		return ordersRepository.findByOrderStatus(status);
	}
	
	//fetch order by customer
	public List<Orders> getOrderByCustumer(int customerId){
		return ordersRepository.findOrderByCustomer(customerId);
		
	}
	
	//fetch order by pagination and sorting
	public Page<Orders> getOrderByPaginationAndSorting(int pageNumber,int pageSize,String fields){
		return ordersRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(fields).ascending()));
	}
}
