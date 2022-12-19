package com.cognizant.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.exception.VendorNotFoundException;
import com.cognizant.exception.VendorStockNotFoundException;
import com.cognizant.model.VendorStock;
import com.cognizant.model.VendorStockPk;
import com.cognizant.repository.VendorStockRepository;
import com.cognizant.service.VendorStockService;

public class VendorStockServiceTest {

	@MockBean
	private VendorStockRepository vendorStockRepo;

	@Autowired
	private VendorStockService vendorStockService;

	@Test
	void contextLoads() {
	}

	@Test
	void findNoVendorStockIfVendorStockRepoIsEmpty() {
		Iterable<VendorStock> vendorStock = vendorStockRepo.findAll();
		assertThat(vendorStock).isEmpty();
	}

	@Test
	void testGetVendorStockByVendorId() throws VendorStockNotFoundException, VendorNotFoundException {
		VendorStock vendorStock = new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03));
		when(vendorStockRepo.findById(new VendorStockPk("001", "002"))).thenReturn(Optional.of(vendorStock));
		assertEquals(vendorStockService.findVendorStockById("001", "002").getStock_in_hand(),
				vendorStock.getStock_in_hand());
	}

	@Test
	void getAllVendorStock() {
		VendorStock vendorStock = new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03));
		VendorStock vendorStock2 = new VendorStock(new VendorStockPk("003", "004"), 7, LocalDate.of(2022, 02, 28));
		List<VendorStock> vs = new ArrayList<>();
		vs.add(vendorStock2);
		vs.add(vendorStock);
		when(vendorStockRepo.findAll()).thenReturn(vs);
		assertEquals(vendorStockService.findAllVendorStock(), vs);
	}

	@Test
	void testcreateVendorIfNotExist() {
		VendorStock vendorStock = vendorStockRepo
				.save(new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03)));
		assertThat(vendorStock).hasFieldOrPropertyWithValue("vendor_id", "001");
		assertThat(vendorStock).hasFieldOrPropertyWithValue("product_id", "002");
		assertThat(vendorStock).hasFieldOrPropertyWithValue("sotck_in_hand", 12);
		assertThat(vendorStock).hasFieldOrPropertyWithValue("replinshment_date", "(2022, 03, 03)");
	}

	@Test
	void testDeleteVendorStockByVendorId() {
		VendorStock vendorStock = new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03));
		VendorStock vendorStock2 = new VendorStock(new VendorStockPk("003", "004"), 7, LocalDate.of(2022, 02, 28));
		List<VendorStock> vs = new ArrayList<>();
		vs.add(vendorStock2);
		vs.add(vendorStock);
		vendorStockRepo.deleteById(new VendorStockPk("001", "002"));
		Iterable<VendorStock> vendorSt = vendorStockRepo.findAll();
		assertThat(vendorSt).hasSize(1).contains(vendorStock2);
	}

}
