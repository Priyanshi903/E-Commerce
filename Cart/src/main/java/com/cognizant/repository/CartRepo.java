package com.cognizant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
	
	@Query(value = "Select c from Cart c where c.email=?1")
	public List<Cart> findByCustomerEmail(String CustomerId);
	
	@Query("Select c from Cart c where c.email=?1 and c.product_id=?2")
	public Cart exists(String email, String product_id);
}
