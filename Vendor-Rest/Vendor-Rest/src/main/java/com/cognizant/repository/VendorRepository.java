package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Vendor;

@Repository 
public interface VendorRepository extends JpaRepository<Vendor, String> {

	@Query(value = "SELECT v.* FROM vendor v INNER JOIN vendor_stock vs ON v.vendor_id = vs.vendor_id "
			+ "WHERE vs.product_id = ?1 ORDER BY v.rating desc", nativeQuery = true)
	public List<Vendor> getAllVendorDetails(String product_id);

	@Query(value = "SELECT v.* FROM vendor v INNER JOIN vendor_stock vs ON v.vendor_id = vs.vendor_id "
			+ "WHERE vs.product_id = ?1 ORDER BY v.rating desc LIMIT 1", nativeQuery = true)
	public List<Vendor> getOneVendorDetails(String product_id);
}
