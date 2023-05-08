package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.Data;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "schedules")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private List<Long> customerIds;

    @ManyToMany
    @JoinTable(name = "schedule_employee", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @Transient
    private List<Long> employeeIds;

    @ManyToMany
    @JoinTable(name = "schedule_pet", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets;

    @Transient
    private List<Long> petIds;

    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "schedule_activities")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public ScheduleDTO convertToDto() {

        ScheduleDTO s = new ScheduleDTO();

        BeanUtils.copyProperties(this, s);

        s.setEmployeeIds(CollectionUtils.isNotEmpty(this.employees)
                ? this.employees.stream().map(Employee::getId).collect(Collectors.toList())
                : new ArrayList<>());

        s.setPetIds(CollectionUtils.isNotEmpty(this.pets)
                ? this.pets.stream().map(Pet::getId).collect(Collectors.toList())
                : new ArrayList<>());

        return s;

    }

}
