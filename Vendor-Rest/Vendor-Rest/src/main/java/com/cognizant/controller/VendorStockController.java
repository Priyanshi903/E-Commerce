package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.model.VendorStock;
import com.cognizant.service.VendorStockService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vendor-stock")
public class VendorStockController {

	@Autowired
	private VendorStockService vendorStockService;

	@GetMapping
	@Operation(summary = "get the list of vendor stock")
	public List<VendorStock> findAllVendorStock() {
		log.info("finding all vendor-stock");
		// find all vendor stock
		return vendorStockService.findAllVendorStock();
	}

	@GetMapping("/{product_id}/{vendor_id}")
	@Operation(summary = "find vendor-stock by id")
	public VendorStock findVendorStockById(@PathVariable String product_id, @PathVariable String vendor_id)
			throws VendorNotFoundException {
		log.info("finding Vendor_Stock By Id");
		return vendorStockService.findVendorStockById(product_id, vendor_id);
	}

	@PostMapping
	@Operation(summary = "Creates new vendor-stock")
	public String createVendor(@RequestBody VendorStock vendorStock) {
		vendorStockService.createVendorStock(vendorStock);
		log.info("vendor created");
		return "Vendor Created";
	}

	@DeleteMapping("/{product_id}/{vendor_id}")
	@Operation(summary = "Deletes vendor-stock by Id")
	public String deleteVendorStockById(@PathVariable String product_id, @PathVariable String vendor_id) {
		// deletes vendorStock by Id
		vendorStockService.deleteVendorStock(product_id, vendor_id);
		log.info("vendor deleted");
		return "Vendor-Stock with product_id:" + product_id + "and vendor_id:" + vendor_id + " Deleted";
	}
}
