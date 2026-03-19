package com.sushant.crud.ecommerce.service;

import java.util.List;

import com.sushant.crud.ecommerce.dto.ProductRequest;
import com.sushant.crud.ecommerce.dto.ProductResponse;
import com.sushant.crud.ecommerce.entity.Product;

public interface ProductService {
	
	ProductResponse createProduct(ProductRequest product);
	List<ProductResponse> getAllProducts();

	ProductResponse getProductById(Long id);

	ProductResponse updateProduct(Long id, ProductRequest product);

	void deleteProduct(Long id);

}
