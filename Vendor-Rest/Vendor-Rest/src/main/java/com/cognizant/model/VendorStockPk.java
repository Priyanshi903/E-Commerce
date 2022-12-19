package com.cognizant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VendorStockPk implements Serializable {

	private static final long serialVersionUID = 1L;
	// Composite key -> product_id, vendor_id
	@Column
	private String product_id;
	@Column
	private String vendor_id;
}
