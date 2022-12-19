package com.cognizant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating")
@IdClass(RatingId.class)
public class Rating implements Serializable {
	
	@Id
	private String customer_email;
	
	@Id
	private String product_id;
	
	//range(1 to 5)
	private int product_rating;
	
	

}
