package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

}
