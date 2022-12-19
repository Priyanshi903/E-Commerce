package com.cognizant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.model.Vendor;
import com.cognizant.service.VendorService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vendor")
@Slf4j
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@GetMapping
	@Operation(summary = "get the list of all vendors")
	public List<Vendor> findAllVendors() {
		return vendorService.findAllVendors();
	}

	@GetMapping("/{vendor_id}")
	@Operation(summary = "get the list of vendors by vendor_id")
	public Vendor getVendorById(@PathVariable String vendor_id) throws VendorNotFoundException {
		log.info("list of vendors by id");
		// get list of vendors by id
		return vendorService.getVendorById(vendor_id);
	}

	@PutMapping
	@Operation(summary = "used to modify the vendor")
	public List<Vendor> modifyVendor(@RequestBody @Valid Vendor vendor) {
		// used to modify vendor
		vendorService.modifyVendor(vendor.getVendor_id(), vendor);
		log.info("vendor modified");
		return vendorService.findAllVendors();
	}

	@PostMapping
	@Operation(summary = "Creates a new vendor")
	public String createVendor(@RequestBody Vendor vendor) {
		// used to create new vendor
		vendorService.createVendor(vendor);
		log.info("vendor created");
		return "Vendor Created";
	}

	@DeleteMapping("/{vendor_id}")
	@Operation(summary = "Deletes vendor by vendor_id")
	public String deleteVendorById(@PathVariable String vendor_id) throws VendorNotFoundException {
		// deletes vendor by vendor_id
		vendorService.deleteVendor(vendor_id);
		log.info("vendor deleted");
		return "Vendor with vendor_id:" + vendor_id + " Deleted";
	}
	
	@GetMapping("/all-vendors/{product_id}")
	@Operation(summary = "Get All Vendor details")
	public List<Vendor> getAllVendorDetails(@PathVariable String product_id) {
		log.info("get all vendor details");
		return vendorService.getAllVendorDetails(product_id);
	}
	
	@GetMapping("/one-vendor/{product_id}")
	@Operation(summary = "Get one Vendor details")
	public List<Vendor> getOneVendorDetails(@PathVariable String product_id) {
		log.info("get One vendor details");
		return vendorService.getOneVendorDetails(product_id);
	}
	
	@GetMapping("/{product_id}/{quantity}")
	@Operation(summary = "get vendor details by product_id and quantity")
	public Vendor getVendorDetails(@PathVariable String product_id,@PathVariable int quantity) {
	log.info("vendor details using id");
	vendorService.getOneVendorDetails(product_id);
	return vendorService.getVendorDetails(product_id, quantity);
	}
}
