package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.VendorStock;
import com.cognizant.model.VendorStockPk;

@Repository
public interface VendorStockRepository extends JpaRepository<VendorStock, VendorStockPk> {
	@Query(value="SELECT vendor_id FROM vendor_stock WHERE stock_in_hand>=?2 and product_id=?1", nativeQuery = true)
	public List<String> getVendorDetails(String product_id,int quantity);
}
