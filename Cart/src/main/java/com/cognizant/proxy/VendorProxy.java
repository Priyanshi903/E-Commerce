package com.cognizant.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.model.Vendor;

@FeignClient(name = "vendor-rest-api", url = "http://localhost:8086")
public interface VendorProxy {

	@GetMapping("vendor/{vendor_id}")
	public Vendor getVendorById(@PathVariable String vendor_id);

	@GetMapping("vendor/{product_id}/{quantity}")
	public Vendor getVendorDetails(@PathVariable String product_id, @PathVariable int quantity);
}
