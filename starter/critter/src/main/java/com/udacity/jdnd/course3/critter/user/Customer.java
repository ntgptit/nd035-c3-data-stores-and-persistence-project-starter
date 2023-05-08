package com.udacity.jdnd.course3.critter.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.pet.Pet;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends User {

    private String phoneNumber;

    private String notes;

    @Transient
    private List<Long> petIds = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    public CustomerDTO convertToDto() {

        CustomerDTO s = new CustomerDTO();

        BeanUtils.copyProperties(this, s);

        if (CollectionUtils.isNotEmpty(this.pets)) {
            CollectionUtils.addAll(s.getPetIds(), this.pets.stream().map(Pet::getId).collect(Collectors.toList()));
        }

        return s;

    }

}
