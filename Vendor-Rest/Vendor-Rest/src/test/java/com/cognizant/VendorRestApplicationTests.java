package com.cognizant;

import org.junit.jupiter.api.Test;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.controller.test.VendorControllerTest;
import com.cognizant.controller.test.VendorStockControllerTest;
import com.cognizant.service.test.VendorServiceTest;
import com.cognizant.service.test.VendorStockServiceTest;

@SpringBootTest
@ComponentScan("com.*")
@SuiteClasses({ VendorControllerTest.class, VendorStockControllerTest.class, VendorServiceTest.class,
		VendorStockServiceTest.class })
class VendorRestApplicationTests {

	@Test
	void contextLoads() {
	}

}
