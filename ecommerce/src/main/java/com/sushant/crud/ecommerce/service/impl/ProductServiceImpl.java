package com.sushant.crud.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sushant.crud.ecommerce.entity.Product;
import com.sushant.crud.ecommerce.exception.ResourceNotFoundException;
import com.sushant.crud.ecommerce.repository.ProductRepository;
import com.sushant.crud.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository; 
	

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Product not found with id " + id));
	}

	@Override
	public Product updateProduct(Long id, Product productToBeUpdated) {
		// TODO Auto-generated method stub
		
		Product dbProduct=productRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Product not found with id " + id));
		dbProduct.setName(productToBeUpdated.getName());
		dbProduct.setDescription(productToBeUpdated.getDescription());
		dbProduct.setPrice(productToBeUpdated.getPrice());
		dbProduct.setQuantity(productToBeUpdated.getQuantity());
		
		return productRepository.save(dbProduct);
	
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

}
