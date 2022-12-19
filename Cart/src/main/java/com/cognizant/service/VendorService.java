package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.Vendor;
import com.cognizant.proxy.VendorProxy;

@Service
public class VendorService {
	
	@Autowired
	private VendorProxy vendorProxy;
	
	public Vendor getVendorForProduct(String productId,int quantity) {
		return vendorProxy.getVendorDetails(productId, quantity);
	}
	
	public Vendor getVendor(String vendorId) {

		return vendorProxy.getVendorById(vendorId);
	}


}
