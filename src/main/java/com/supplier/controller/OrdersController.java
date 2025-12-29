package com.supplier.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supplier.dto.*;
import com.supplier.entity.Orders;
import com.supplier.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	@Autowired
	OrdersService orderService;
	
	//add order
	@PostMapping
	public ResponseEntity<ResponseStructure<Orders>> addOrders(@RequestBody Orders orders){
		return orderService.addOrders(orders);
	}
	
	
	//get order by id
	@GetMapping("/{orderId}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable int orderId){
		return orderService.getOrderById(orderId);
	}
	
    //get all order
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders(){
		return orderService.getAllOrders();
	}
	
    //update order
	@PutMapping
	public ResponseEntity<ResponseStructure<Orders>> updateOrders(@RequestBody Orders orders){
		return orderService.updateOrders(orders);
	}
	
	//delete order
	@DeleteMapping("/{orderId}")
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(@PathVariable int orderId){
		return orderService.deleteOrder(orderId);
	}
	
	
	//get  order by tracking number
	@GetMapping("/tracking/{trackingNumber}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(@PathVariable String trackingNumber){
		return orderService.getOrderByTrackingNumber(trackingNumber);
	}
	
	//get orderwithAmountGreaterThanParticular value
	@GetMapping("/totalAmount/{totalAmount}")
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByGreaterThanTotalAmount(@PathVariable double totalAmount){
		return orderService.getOrdersGreaterThanAmount(totalAmount);
	}
	
	//get order by status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByOrderStatus(@PathVariable OrderStatus status){
		return orderService.getOrdersByOrderStatus(status);
	}
	
	//get order by customer
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(@PathVariable int customerId){
		return orderService.getOrdersByCustomer(customerId);
	}
	
	//get order by paginationAndsorting
	@GetMapping("/{pageNumber}/{pageSize}/{fields}")
	public ResponseEntity<ResponseStructure<Page<Orders>>>
	getOrderByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String fields){
		return orderService.getOrderByPaginationAndSorting(pageNumber, pageSize, fields);
	}

}
