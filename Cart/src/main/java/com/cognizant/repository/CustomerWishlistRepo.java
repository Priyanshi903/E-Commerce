package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.CustomerWishlist;

@Repository
public interface CustomerWishlistRepo extends JpaRepository<CustomerWishlist, Long> {
	
	@Query(value = "Select c from CustomerWishlist c where c.email=?1 and c.product_id=?2")
	public CustomerWishlist findByEmailAndProduct(String email,String productId);
	
	@Query(value = "Select c from CustomerWishlist c where c.email=?1")
	public List<CustomerWishlist> findByEmail(String email);

}
