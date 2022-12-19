package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.model.VendorStock;
import com.cognizant.model.VendorStockPk;
import com.cognizant.repository.VendorStockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorStockService {

	@Autowired
	private VendorStockRepository vendorStockRepo;

	public List<VendorStock> findAllVendorStock() {
		log.info("get all vendor stock");
		return vendorStockRepo.findAll();
	}

	public VendorStock findVendorStockById(String product_id, String vendor_id) throws VendorNotFoundException {
		VendorStock vendorStock = vendorStockRepo.findById(new VendorStockPk(product_id, vendor_id)).orElse(null);
		if (vendorStock != null) {
			log.info("vendor Stock Exists");
			return vendorStock;
		} else {
			log.error("Vendor-Stock not found");
			throw new VendorNotFoundException(
					"vendor Stock doesnot exist for product_id:" + product_id + " with vendor:" + vendor_id);
		}
	}

	// creates new vendor-stock
	public VendorStock createVendorStock(VendorStock vendorStock) {
		vendorStockRepo.saveAndFlush(vendorStock);
		log.info("vendor-stock created");
		return vendorStock;
	}

	// deletes vendor-stock if exists
	public void deleteVendorStock(String product_id, String vendor_id) {
		if (product_id != null && vendor_id != null) {
			vendorStockRepo.deleteById(new VendorStockPk(product_id, vendor_id));
			log.info("vendor-stock deleted");
		}
	}
	
}
