package com.cognizant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.ProductAlreadyExistsException;
import com.cognizant.exception.ProductNotFoundException;
import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public Product getProductById(String productId) throws ProductNotFoundException {
		log.info("Searching product with id.......");
		Product product = productRepo.findById(productId).orElse(null);
		if (product != null) {
			log.info("Returning product .....");
			log.debug("Product is:{}", product);
			return product;
		}

		else {
			log.info("Product not found.....");
			throw new ProductNotFoundException("Product Not Found");
		}

	}

	public boolean deleteProduct(String productId) throws ProductNotFoundException {

		Product product = productRepo.findById(productId).orElse(null);
		if (product != null) {
			productRepo.deleteById(productId);
			log.info("Product deleted");
			return true;
		}

		else {
			log.info("Product not found.....");
			throw new ProductNotFoundException("Product Not Found");
		}

	}

	public boolean addProduct(Product product) throws ProductAlreadyExistsException {
		log.info("Adding product to the database....");
		Product checkProduct = productRepo.findById(product.getProduct_id()).orElse(null);
		if (checkProduct == null) {
			productRepo.saveAndFlush(product);
			log.info("Added product to the database....");
			return true;
		} else {
			log.info("Product Exists already..............");
			throw new ProductAlreadyExistsException("Product with " + product.getProduct_id() + " already Present");
		}

	}

	public List<Product> searchProductByname(String productName) throws ProductNotFoundException {
		log.info("Searching Products by name......");
		List<Product> productList = productRepo.findProductByProductName(productName);
		if (productList.size() == 0) {
			log.info("Product with " + productName + " not present");
			throw new ProductNotFoundException("Product not found");
		} else {
			log.info("Product List according to name:{}", productList);
			return productList;
		}
	}

	public List<Product> getAllProducts() {
		log.info("Returning All Products");
		List<Product> productList = productRepo.findAll();
		log.info("All list of products:{}", productList);
		return productList;
	}

	public boolean updateProduct(Product product) throws ProductNotFoundException {
		log.info("Updating Product.....");
		Product productExists = productRepo.findById(product.getProduct_id()).orElse(null);
		if (productExists != null) {
			log.info("Product Updated :{}", product);
			productRepo.saveAndFlush(product);
			return true;
		} else {
			log.info("Product not found.....");
			throw new ProductNotFoundException("Product Not Found");
		}

	}

}
