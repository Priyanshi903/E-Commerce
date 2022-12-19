package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class is for vendor entity coming from vendor microservice
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendor")
public class Vendor {
	
	@Id
	private String vendor_id;
	
	private String vendor_name;
	
	private double delivery_charge;
	
	private int rating;

}
