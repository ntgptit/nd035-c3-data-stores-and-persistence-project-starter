package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import lombok.Data;

/**
 * Represents the form that pet request and response data takes. Does not map to
 * the database directly.
 */
@Data
public class PetDTO {
	private long id;
	private PetType type;
	private String name;
	private long ownerId;
	private LocalDate birthDate;
	private String notes;

	public Pet convertToEntity() {

		Pet pet = new Pet();

		BeanUtils.copyProperties(this, pet);

		return pet;

	}

}
