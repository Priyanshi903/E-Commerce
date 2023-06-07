package com.cognizant.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url="http://localhost:8087")
public interface AuthProxy {
	
	@GetMapping("/auth/health-check")
	public ResponseEntity<String> healthCheck();
	
	@GetMapping("/auth/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);
}
