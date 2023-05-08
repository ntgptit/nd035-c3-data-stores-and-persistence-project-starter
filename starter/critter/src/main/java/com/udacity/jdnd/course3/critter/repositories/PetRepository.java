package com.udacity.jdnd.course3.critter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;

/**
 * @author GiapNT
 *
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	List<Pet> findByOwner(Customer owner);

	List<Pet> findAllByIdIn(List<Long> ids);

}
