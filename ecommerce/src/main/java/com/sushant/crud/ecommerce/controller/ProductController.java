package com.sushant.crud.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushant.crud.ecommerce.entity.Product;
import com.sushant.crud.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}
	@PutMapping("/{productId}")
	public Product getProductById(@PathVariable Long productId,@RequestBody Product product) {
		return productService.updateProduct(productId,product);
	}
	
	
	@DeleteMapping("/{productId}")
	public void deleteProductById(@PathVariable Long productId) {
		 productService.deleteProduct(productId);
	}
	

}
