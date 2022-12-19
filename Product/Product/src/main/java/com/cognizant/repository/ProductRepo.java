package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
	@Query(value="Select p from Product p where p.product_name=?1")
	public List<Product> findProductByProductName(String productName);
}
