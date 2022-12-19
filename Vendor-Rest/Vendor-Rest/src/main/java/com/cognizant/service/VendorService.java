package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.model.Vendor;
import com.cognizant.model.VendorStock;
import com.cognizant.model.VendorStockPk;
import com.cognizant.repository.VendorRepository;
import com.cognizant.repository.VendorStockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepo;

	@Autowired
	private VendorStockRepository vendorStockRepo;

	// get list of all vendors
	public List<Vendor> findAllVendors() {
		log.info("get All Vendors");
		return vendorRepo.findAll();
	}

	// get vendors by id
	public Vendor getVendorById(String vendor_id) throws VendorNotFoundException {
		Vendor vendor = vendorRepo.findById(vendor_id).orElse(null);
		if (vendor != null) {
			log.info("vendor found");
			return vendor;
		} else {
			log.error("vendor not found");
			throw new VendorNotFoundException("vendor with vendor_id:" + vendor_id + " not found");
		}
	}

	// create new vendor
	public Vendor createVendor(Vendor vendor) {
		vendorRepo.saveAndFlush(vendor);
		log.info("vendor created");
		return vendor;
	}

	// deletes vendor if existed
	public void deleteVendor(String vendor_id) throws VendorNotFoundException {
		Vendor vendor = vendorRepo.findById(vendor_id).orElse(null);
		if (vendor != null) {
			vendorRepo.deleteById(vendor_id);
			log.info("Vendor Deleted");
		} else {
			log.info("Vendor Not Found with id:" + vendor_id);
			throw new VendorNotFoundException("Vendor Not Found with Id:" + vendor_id);
		}
	}

	// modifies vendor
	public void modifyVendor(String vendor_id, Vendor vendor) {
		log.info("start");
		for (Vendor v : findAllVendors()) {
			if (v.getVendor_id().equalsIgnoreCase(vendor_id)) {
				v.setVendor_name(vendor.getVendor_name());
				v.setDelivery_charge(vendor.getDelivery_charge());
				v.setRating(vendor.getRating());
			}
			vendorRepo.save(vendor);
			log.info("vendor modified successfully");
		}
		log.info("end");
	}

	// get all vendor details
	public List<Vendor> getAllVendorDetails(String product_id) {
		return vendorRepo.getAllVendorDetails(product_id);
	}

	// get one vendor details
	public List<Vendor> getOneVendorDetails(String product_id) {
		return vendorRepo.getOneVendorDetails(product_id);
	}

	// get vendor details by product_id and quantity
	public Vendor getVendorDetails(String product_id, int quantity) {
		log.info("getting vendors with product_id and quantity");
		List<String> vendorlistIds = vendorStockRepo.getVendorDetails(product_id, quantity);
		if (vendorlistIds.isEmpty()) {
			return new Vendor();
		}
		int max = 0;
		Vendor vendorToBeReturned = new Vendor();
		String finalVendorId = "";
		for (String vendorid : vendorlistIds) {
			Vendor vendor = vendorRepo.findById(vendorid).orElse(null);
			if (vendor.getRating() > max) {
				finalVendorId = vendorid;
				vendorToBeReturned = vendor;
				max = vendor.getRating();
				if (vendor.getRating() == 5) {
					VendorStockPk vendorStockPk = new VendorStockPk(product_id, vendorid);
					VendorStock vendorStock = vendorStockRepo.findById(vendorStockPk).get();
					vendorStock.setStock_in_hand(vendorStock.getStock_in_hand() - quantity);
					vendorStockRepo.saveAndFlush(vendorStock);
					return vendorToBeReturned;
				}
			}
		}
		VendorStockPk vendorStockPk = new VendorStockPk(product_id, finalVendorId);
		VendorStock vendorStock = vendorStockRepo.findById(vendorStockPk).get();
		vendorStock.setStock_in_hand(vendorStock.getStock_in_hand() - quantity);
		vendorStockRepo.saveAndFlush(vendorStock);
		log.info("vendor:::::::::::::::::::::::::::{}", vendorToBeReturned);
		return vendorToBeReturned;
	}

}
