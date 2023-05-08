package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

/**
 * @author GiapNT
 *
 */
@Service
public interface ScheduleService {

	Schedule createSchedule(Schedule schedule);

	List<Schedule> getAllSchedules();

	List<Schedule> getScheduleForPet(Long petId);

	List<Schedule> getScheduleForEmployee(Long employeeId);

	List<Schedule> getScheduleForCustomer(Long customerId);

}
