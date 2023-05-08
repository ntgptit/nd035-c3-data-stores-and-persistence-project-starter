package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import lombok.Data;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class EmployeeDTO {
	private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

	public Employee convertToEntity() {

		Employee s = new Employee();

		BeanUtils.copyProperties(this, s);

		return s;

	}

}
