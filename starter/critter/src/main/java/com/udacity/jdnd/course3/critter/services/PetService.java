package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.Pet;

/**
 * @author GiapNT
 *
 */
@Service
public interface PetService {

	Pet savePet(Pet pet);

	Pet getPet(Long petId);

	List<Pet> getPets();

	List<Pet> getPetsByOwner(Long ownerId);
}
