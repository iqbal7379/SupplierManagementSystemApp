package com.supplier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplier.dao.CustomerDao;
import com.supplier.dao.OrdersDao;
import com.supplier.dao.ProductsDao;
import com.supplier.dto.OrderStatus;
import com.supplier.dto.ResponseStructure;
import com.supplier.entity.Customer;
import com.supplier.entity.Orders;
import com.supplier.entity.Products;
import com.supplier.exception.IdNotFoundException;
import com.supplier.exception.NotRecordFoundExecption;

@Service
public class OrdersService {
	
	@Autowired
	OrdersDao ordersDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
    ProductsDao productDao;
	
	
	//add orders
	public ResponseEntity<ResponseStructure<Orders>> addOrders(Orders orders){
		int customerId=orders.getCustomer().getCustomerId();
		Optional<Customer> optional=customerDao.getCustomerById(customerId);
		ResponseStructure<Orders> response=new ResponseStructure<Orders>();
		if (optional.isPresent()) {
			orders.setCustomer(optional.get());
			List<Products> products=new ArrayList<Products>();
			if(orders.getProducts()!=null) {
			for(Products product:orders.getProducts()) {
				Optional<Products> productOpt=productDao.getProductsById(product.getProductId());
				if (productOpt.isPresent()) {
					products.add(productOpt.get());
				}
				else {
					 throw new IdNotFoundException("Product not found with id: " + product.getProductId());
					 }
				}
			}
			orders.setProducts(products);
			Orders savedOrder=ordersDao.addOrders(orders);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Order added with products");
			response.setData(savedOrder);
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException("Id not found");
		}
	}
	
	// fetch order by id
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(int orderId){
		Optional<Orders> optional=ordersDao.getOrderById(orderId);
		ResponseStructure<Orders> response=new ResponseStructure<Orders>();
		if (optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("orderd fetch by id successfully");
			response.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("unable to fetch order since id is invalid");
		}
	}
	
	
	//fetch all orders
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders(){
		List<Orders> orders=ordersDao.getAllOrders();
		ResponseStructure<List<Orders>> response=new ResponseStructure<List<Orders>>();
		if (!orders.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch all order successfully");
			response.setData(orders);
			
			return new ResponseEntity<ResponseStructure<List<Orders>>>(response,HttpStatus.OK);
		}
		else {
			throw new NotRecordFoundExecption("unable to fetch");
		}
	}
	
	
	//update orders
	public ResponseEntity<ResponseStructure<Orders>> updateOrders(Orders orders){
		Optional<Orders> optional=ordersDao.getOrderById(orders.getOderId());
		ResponseStructure<Orders> response=new ResponseStructure<Orders>();
		if (optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("order update successfully");
			response.setData(ordersDao.updateOrders(orders));
			
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
	}
		throw new IdNotFoundException("unable to update order since id is valid");
	}
	
	
	//delete orders
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(int orders){
		Optional<Orders> optional=ordersDao.getOrderById(orders);
		ResponseStructure<Orders> response=new ResponseStructure<Orders>();
		
		if (optional.isPresent()) {
			ordersDao.deleteOrders(optional.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("order delete successfully");
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("unable to delete since id is not found");
		}
	}
	
	
	// fetch order by tracking number
	public ResponseEntity<ResponseStructure<Orders>>getOrderByTrackingNumber(String trackingNumber){
		Optional<Orders> optional=ordersDao.getOrderByTrackingNumber(trackingNumber);
		
		ResponseStructure<Orders> response=new ResponseStructure<Orders>();
		if (optional.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch order by Tracking Number successfully");
			response.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Orders>>(response,HttpStatus.OK);
		}
		else {
			throw new NotRecordFoundExecption("unable to fetch order ");
		}
	}
	
	//fetch orders by greater than total amount
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersGreaterThanAmount(double totalAmount){
		List<Orders> orders=ordersDao.getOrdersGreaterThanAmount(totalAmount);
		ResponseStructure<List<Orders>> response=new ResponseStructure<List<Orders>>();
		
		if (!orders.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch order successfully by greater amount");
			response.setData(orders);
			 return new ResponseEntity<ResponseStructure<List<Orders>>>(response,HttpStatus.OK);
		}
		else {
			throw new NotRecordFoundExecption("unable to fetch order ");
		}
		
		
	}
	
	
	//fetch order by orderStatus
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByOrderStatus(OrderStatus status){
		List<Orders> orders=ordersDao.getOrderByStatus(status);
        ResponseStructure<List<Orders>> response=new ResponseStructure<List<Orders>>();
		
		if (!orders.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("fetch order successfully by orderStatus");
			response.setData(orders);
			 return new ResponseEntity<ResponseStructure<List<Orders>>>(response,HttpStatus.OK);
		}
		else {
			throw new NotRecordFoundExecption("unable to fetch order ");
		}
	}
	
	
	//fetch order by customer
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(int customerId){
		List<Orders> orders=ordersDao.getOrderByCustumer(customerId);
		 ResponseStructure<List<Orders>> response=new ResponseStructure<List<Orders>>();
			
			if (!orders.isEmpty()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("fetch order successfully by customer");
				response.setData(orders);
				 return new ResponseEntity<ResponseStructure<List<Orders>>>(response,HttpStatus.OK);
			}
			else {
				throw new NotRecordFoundExecption("unable to fetch order ");
			}
	}
	
	
	
	//fetch order by pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Orders>>>
	getOrderByPaginationAndSorting(int pageNumber,int pageSize,String fields){
		Page<Orders> pages=ordersDao.getOrderByPaginationAndSorting(pageNumber, pageSize, fields);
		
		 ResponseStructure<Page<Orders>> response=new ResponseStructure<Page<Orders>>();
			
			if (!pages.isEmpty()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("fetch order successfully by customer");
				response.setData(pages);
				 return new ResponseEntity<ResponseStructure<Page<Orders>>>(response,HttpStatus.OK);
			}
			else {
				throw new NotRecordFoundExecption("unable to fetch order ");
			}
	
	}

}
