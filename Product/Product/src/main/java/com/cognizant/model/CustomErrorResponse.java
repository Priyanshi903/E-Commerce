package com.cognizant.model;

import java.util.Date;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is for customizing the exception handler
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {
	private HttpStatus status;
	private Date timestamp;
	private String message;
}
