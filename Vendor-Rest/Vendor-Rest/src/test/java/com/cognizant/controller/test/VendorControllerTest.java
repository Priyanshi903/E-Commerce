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

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.controller.VendorController;
import com.cognizant.model.Vendor;
import com.cognizant.service.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
@AutoConfigureMockMvc
public class VendorControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private VendorService vendorService;

	@Test
	void testGetAllVendors() throws Exception {
		mvc.perform(get("/product/").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetVendorByVendorId() throws Exception {
		Vendor vendor = new Vendor("001", "Siva", 50, 5);
		doReturn(vendor).when(vendorService).getVendorById("001");
		mvc.perform(get("/vendor/{vendor_id}", "001").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.vendor_name", is("Siva")));
	}

	@Test
	void testGetVendorByVendorIdNotFound() throws Exception {
		mvc.perform(get("/vendor/001").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void testCreateVendor() throws Exception {
		mvc.perform(post("/vendor").content(convertObjectToJsonString(new Vendor("001", "siva", 50, 5)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteVendorById() throws Exception {
		Vendor vendor = new Vendor("001", "Siva", 50, 5);
		doReturn(null).when(vendorService).deleteVendor("001");
		mvc.perform(delete("/vendor/{vendor_id}, 001").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.vendor_id", is(nullValue())));
		assertEquals(vendor, null);
	}

	public static String convertObjectToJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
