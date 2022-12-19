package com.cognizant.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.controller.VendorController;
import com.cognizant.model.VendorStock;
import com.cognizant.model.VendorStockPk;
import com.cognizant.service.VendorStockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
public class VendorStockControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private VendorStockService vendorStockService;

//	@Autowired
//	private VendorStockRepository vendorStockRepo;

	@Test
	void testGetAllVendorStock() throws Exception {
		mvc.perform(get("/vendor-stock/").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetVendorStockById() throws Exception {
		VendorStock vendorStock = new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03));
		doReturn(vendorStock).when(vendorStockService).findVendorStockById("001", "002");
		mvc.perform(get("/vendor-stock/{product_id}{vendor_id}", "001").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.vendor_id", is("001")));
	}

	@Test
	void testGetVendorByVendorStockIdNotFound() throws Exception {
		mvc.perform(get("/vendor-stock/001/002").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void testCreateVendorStock() throws Exception {
		mvc.perform(post("/vendor-stock").content(convertObjectToJsonString(new VendorStockPk("001", "002")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteVendorStockById() throws Exception {
		VendorStock vendorStock = new VendorStock(new VendorStockPk("001", "002"), 12, LocalDate.of(2022, 03, 03));
		doReturn(null).when(vendorStockService).deleteVendorStock("001", "002");
		mvc.perform(delete("/vendor/{vendor_id}, 001").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.vendor_id", is(nullValue())));
		assertEquals(vendorStock, null);
	}

	public static String convertObjectToJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
