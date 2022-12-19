package com.cognizant.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.model.Vendor;
import com.cognizant.repository.VendorRepository;
import com.cognizant.service.VendorService;

@RunWith(SpringRunner.class)
@WebMvcTest({ VendorService.class })
public class VendorServiceTest {

	@MockBean
	private VendorRepository vendorRepo;

	@Autowired
	private VendorService vendorService;

	@Test
	void contextLoads() {

	}

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	void findNoVendorIfVendorRepoIsEmpty() {
		Iterable<Vendor> vendors = vendorRepo.findAll();
		assertThat(vendors).isEmpty();
	}

	@Test
	void testGetVendorByVendorId() throws VendorNotFoundException {
		VendorService vendorService = new VendorService();
		Vendor vendor = new Vendor("001", "siva", 50.0, 4);
		when(vendorRepo.findById("001")).thenReturn(Optional.of(vendor));
		assertEquals(vendorService.getVendorById("001").getVendor_name(), vendor.getVendor_name());
	}

	@Test
	void testGetVendorByIdException() {
		when(vendorRepo.findById("001")).thenReturn(Optional.empty());
		Exception exception = assertThrows(VendorNotFoundException.class, () -> vendorService.getVendorById("001"));
		assertEquals("Vendor Not Found", exception.getMessage());
	}

	@Test
	void deleteAllVendors() {
		Vendor vendor = new Vendor("001", "siva", 40.0, 4);
		Vendor vendor2 = new Vendor("002", "siva2", 35.0, 5);
		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(vendor);
		vendorList.add(vendor2);
		vendorRepo.deleteAll();
		Iterable<Vendor> vendors = vendorRepo.findAll();
		assertThat(vendors).hasSize(0).isEmpty();
	}

	@Test
	void getAllVendors() {
		Vendor vendor = new Vendor("001", "siva", 40.0, 4);
		Vendor vendor2 = new Vendor("002", "siva2", 35.0, 5);
		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(vendor);
		vendorList.add(vendor2);
		when(vendorRepo.findAll()).thenReturn(vendorList);
		assertEquals(vendorService.findAllVendors(), vendorList);
	}

	@Test
	void testcreateVendorIfNotExist() {
		Vendor vendor = vendorRepo.save(new Vendor("001", "siva", 40.0, 4));
		assertThat(vendor).hasFieldOrPropertyWithValue("vendor_id", "001");
		assertThat(vendor).hasFieldOrPropertyWithValue("vendor_name", "siva");
		assertThat(vendor).hasFieldOrPropertyWithValue("delivery_charge", 40.0);
		assertThat(vendor).hasFieldOrPropertyWithValue("rating", 4);
	}

	@Test
	void testDeleteVendorByVendorId() {
		Vendor vendor = new Vendor("001", "siva", 40.0, 4);
		Vendor vendor2 = new Vendor("002", "siva2", 35.0, 5);
		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(vendor);
		vendorList.add(vendor2);
		vendorRepo.deleteById("002");
		Iterable<Vendor> vendors = vendorRepo.findAll();
		assertThat(vendors).hasSize(1).contains(vendor);
	}

	@Test
	void testUpdateVendorByVendorId() {
		Vendor vendor = new Vendor("001", "siva", 40.0, 4);
		Vendor vendor2 = new Vendor("002", "siva2", 35.0, 5);
		List<Vendor> vendorList = new ArrayList<>();
		vendorList.add(vendor);
		vendorList.add(vendor2);
		Vendor updatedVendor = new Vendor("003", "siva3", 35.0, 3);
		Optional<Vendor> ven = vendorRepo.findById(vendor2.getVendor_id());
		assertThat(vendor.getVendor_id().equals(updatedVendor.getVendor_id()));
		assertThat(ven.get().equals(updatedVendor));
	}
}
