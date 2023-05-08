package com.udacity.jdnd.course3.critter.repositories;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

/**
 * @author GiapNT
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN e.skills es JOIN e.daysAvailable da WHERE es IN (:skills) AND da IN (:daysAvailable) GROUP BY e.id HAVING COUNT(es) = :skillsCount")
    List<Employee> findEmployeesBySkillAndDay(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable,
            long skillsCount);

}
