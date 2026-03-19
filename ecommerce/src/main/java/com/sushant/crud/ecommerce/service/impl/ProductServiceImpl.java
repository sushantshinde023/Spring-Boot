package com.sushant.crud.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sushant.crud.ecommerce.dto.ProductRequest;
import com.sushant.crud.ecommerce.dto.ProductResponse;
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
	public ProductResponse createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Product product=new Product();
		product.setName(productRequest.name());
		product.setDescription(productRequest.description());
		product.setPrice(productRequest.price());
		product.setQuantity(productRequest.quantity());
		
		Product saved = productRepository.save(product);

	    return mapToResponse(saved);
	}

	private ProductResponse mapToResponse(Product product) {
		ProductResponse response = new ProductResponse( product.getId(),product.getName(),product.getDescription(),product.getPrice());
		return response;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		 List<Product> allProducts=productRepository.findAll();
		 
		 return allProducts.stream().map(product->mapToResponse(product)).toList();
	}

	@Override
	public ProductResponse getProductById(Long id) {
		// TODO Auto-generated method stub
		Product producet=productRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Product not found with id " + id));
		return  mapToResponse(producet);
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductRequest productToBeUpdated) {
		// TODO Auto-generated method stub
		
		Product dbProduct=productRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Product not found with id " + id));
		dbProduct.setName(productToBeUpdated.name());
		dbProduct.setDescription(productToBeUpdated.description());
		dbProduct.setPrice(productToBeUpdated.price());
		dbProduct.setQuantity(productToBeUpdated.quantity());
		
		Product updated=productRepository.save(dbProduct);
		return mapToResponse(updated);
		
		
	
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

}
