package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {

	@ElementCollection(targetClass = EmployeeSkill.class)
	@Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

	@ElementCollection(targetClass = DayOfWeek.class)
	@Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

	public EmployeeDTO convertToDto() {

		EmployeeDTO s = new EmployeeDTO();

		BeanUtils.copyProperties(this, s);

		return s;

	}

}
