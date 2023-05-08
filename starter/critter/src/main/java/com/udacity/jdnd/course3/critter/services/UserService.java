package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;

/**
 * @author GiapNT
 *
 */
@Service
public interface UserService {

	Customer saveCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getOwnerByPet(long petId);

	Employee saveEmployee(Employee employee);

	Employee getEmployee(long employeeId);

	List<Employee> findEmployeesForService(EmployeeRequestDTO employee);

}
