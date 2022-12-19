package com.cognizant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.*")
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}

}
