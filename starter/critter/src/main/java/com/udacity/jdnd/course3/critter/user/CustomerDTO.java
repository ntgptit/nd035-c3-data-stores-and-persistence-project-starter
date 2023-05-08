package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

	public Customer convertToEntity() {

		Customer s = new Customer();

		BeanUtils.copyProperties(this, s);

		return s;

	}

}
