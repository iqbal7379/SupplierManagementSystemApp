package com.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.dto.ResponseStructure;
import com.supplier.entity.Products;
import com.supplier.entity.Supplier;
import com.supplier.service.ProductsService;


@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	ProductsService productsService;
	
	//save
	@PostMapping("/add/{supplierId}")
	public ResponseEntity<ResponseStructure<Products>> addProducts(@PathVariable int supplierId,@RequestBody Products products){
		return productsService.addProducts(supplierId, products);
	}
	
	//fetch products by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Products>> getProductsByid(@PathVariable int id){
		return productsService.getProductsByid(id);
	}
	
	
	//fetch All Product
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Products>>> getAllProducts(){
		return productsService.getAllProducts();
	}
	
	//update Products
	@PutMapping
	public ResponseEntity<ResponseStructure<Products>> updateProducts(@RequestBody Products products){
		return productsService.updateProducts(products);
	}
	
	
	//delete product
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Products>> deleteProducts(@PathVariable int id){
		return productsService.deleteProducts(id);
	}
	
	
	//get product by supplier
	@GetMapping("/supplier/{supplierId}")
	public ResponseEntity<ResponseStructure<List<Products>>> getProductsBySupplier(@PathVariable Supplier supplierId){
		return productsService.getProductsBySupplier(supplierId);
	}
	
	
	
	//get product by Stack Qty
	@GetMapping("/qty/{qty}")
	public ResponseEntity<ResponseStructure<List<Products>>> getProductsByStackQty(@PathVariable int qty){
		return productsService.getProductsByStackQty(qty);
	}
	
	
	//get Products by pagination and sorting
	@GetMapping("/{pageNumber}/{pageSize}/{fields}")
	public ResponseEntity<ResponseStructure<Page<Products>>> getCustomerByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String fields){
		return productsService.getProductsByPaginationAndSorting(pageNumber, pageSize, fields);
	}
}
 