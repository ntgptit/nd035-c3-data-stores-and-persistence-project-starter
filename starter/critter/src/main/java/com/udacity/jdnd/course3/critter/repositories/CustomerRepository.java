package com.udacity.jdnd.course3.critter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.user.Customer;

/**
 * @author GiapNT
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c JOIN c.pets p WHERE p.id = :petId")
    Customer getOwnerByPetId(Long petId);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.pets")
    List<Customer> findAllWithPets();

}
