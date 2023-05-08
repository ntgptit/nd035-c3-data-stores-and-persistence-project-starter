package com.udacity.jdnd.course3.critter.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.exceptions.CritterException;
import com.udacity.jdnd.course3.critter.exceptions.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.UserService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

/**
 * @author GiapNT
 *
 */
@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer saveCustomer(Customer customer) {

        Customer customerResult = this.customerRepository.save(customer);

        List<Long> petIds = customer.getPetIds();
        if (!CollectionUtils.isEmpty(petIds)) {

            for (Long petId : petIds) {

                Pet pet = this.petRepository.findById(petId).orElse(null);

                if (pet == null) {
                    throw new ResourceNotFoundException("Pet with id " + petId + " does not exist");
                }

                Customer ownerOfPet = pet.getOwner();

                if (ownerOfPet != null && !Objects.equals(ownerOfPet.getId(), customerResult.getId())) {
                    throw new CritterException("Pet with id " + petId + " already belongs to another owner");
                }

                pet.setOwner(customerResult);
                this.petRepository.save(pet);

            }

        }

        return customerResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerResults = this.customerRepository.findAll();

        for (Customer customer : customerResults) {
            customer.setPets(this.petRepository.findByOwner(customer));
        }

        return customerResults;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getOwnerByPet(long petId) {

        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet with id " + petId + " not found"));

        Long ownerId = pet.getOwner().getId();

        Customer customer = this.customerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner with id " + ownerId + " not found"));

        customer.setPets(Arrays.asList(pet));

        return customer;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployee(long employeeId) {
        return this.employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findEmployeesForService(EmployeeRequestDTO employee) {

        Set<EmployeeSkill> skills = employee.getSkills();
        Set<DayOfWeek> daysAvailable = Sets.newHashSet(LocalDate.parse(employee.getDate().toString()).getDayOfWeek());

        return this.employeeRepository.findEmployeesBySkillAndDay(skills, daysAvailable, skills.size());
    }

}
