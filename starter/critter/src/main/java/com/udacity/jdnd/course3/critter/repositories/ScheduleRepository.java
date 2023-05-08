package com.udacity.jdnd.course3.critter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

/**
 * @author GiapNT
 *
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> getScheduleForPet(Long petId);

    @Query("SELECT s FROM Schedule s JOIN s.employees p WHERE p.id = :employeeId")
    List<Schedule> getScheduleForEmployee(Long employeeId);

    @Query("SELECT s FROM Schedule s JOIN s.pets p JOIN p.owner o WHERE o.id = :ownerId")
    List<Schedule> getScheduleForCustomer(Long ownerId);

}
