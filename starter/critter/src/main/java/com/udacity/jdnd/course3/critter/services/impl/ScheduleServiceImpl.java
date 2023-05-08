package com.udacity.jdnd.course3.critter.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Employee;

/**
 * @author GiapNT
 *
 */
@Service("ScheduleServiceImpl")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Schedule createSchedule(Schedule schedule) {

        List<Employee> employees = this.employeeRepository.findAllById(schedule.getEmployeeIds());
        List<Pet> pets = this.petRepository.findAllById(schedule.getPetIds());

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return this.scheduleRepository.save(schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schedule> getAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schedule> getScheduleForPet(Long petId) {
        return this.scheduleRepository.getScheduleForPet(petId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return this.scheduleRepository.getScheduleForEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schedule> getScheduleForCustomer(Long customerId) {

        return this.scheduleRepository.getScheduleForCustomer(customerId);
    }

}
