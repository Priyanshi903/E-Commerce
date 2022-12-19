package com.cognizant.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_stock")
public class VendorStock implements Serializable {

	@EmbeddedId
	VendorStockPk vendorStockPk;
	@Column
	private long stock_in_hand;
	@Column
	private LocalDate replinshment_date;

}
