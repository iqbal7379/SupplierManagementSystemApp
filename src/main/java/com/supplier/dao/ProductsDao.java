package com.supplier.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplier.entity.Products;
import com.supplier.entity.Supplier;
import com.supplier.repository.ProductsRepository;

@Repository
public class ProductsDao {
	
    @Autowired
    private ProductsRepository productRepository;
	
    // Add Product
    public Products addProducts(Products products) {
        return productRepository.save(products);
    }
	
    // Get product by id
    public Optional<Products> getProductsById(int id){
        return productRepository.findById(id);
    }

    // Get all products
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    // Update product
    public Products updateProducts(Products products) {
        return productRepository.save(products);
    }
	
    // Delete product
    public void deleteProducts(Products products) {
        productRepository.delete(products);
    }
	
    // Get products by supplierId
    public List<Products> getProductsBySupplier(Supplier supplierId){
        return productRepository.findBySupplier(supplierId);
    }
	
    // Get products by stack qty
    public List<Products> getProductsByStackQty(int qty){
        return productRepository.findByProductStackQty(qty);
    }
	
    // Pagination + sorting
    public Page<Products> getProductsByPaginationAndSorting(int pageNumber, int pageSize, String field){
        return productRepository.findAll(
            PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending())
        );
    }
}