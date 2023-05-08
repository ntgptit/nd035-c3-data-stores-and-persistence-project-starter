package com.udacity.jdnd.course3.critter.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.exceptions.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;

/**
 * @author GiapNT
 *
 */
@Service("PetServiceImpl")
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet savePet(Pet pet) {

        long ownerId = pet.getOwnerId();
        Customer owner = this.customerRepository
                .findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner with id " + ownerId + " not found."));

        pet.setOwner(owner);

        return this.petRepository.save(pet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getPet(Long petId) {
        return this.petRepository.findById(petId).orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pet> getPets() {
        return this.petRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return this.petRepository.findByOwner(this.customerRepository.findById(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Owner with id " + ownerId + " not found")));
    }

}
