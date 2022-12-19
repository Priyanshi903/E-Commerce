package com.cognizant.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class is for vendor entity coming from vendor microservice
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
	
	private String vendor_id;
	
	private String vendor_name;
	
	private double delivery_charge;
	
	private int rating;

}
