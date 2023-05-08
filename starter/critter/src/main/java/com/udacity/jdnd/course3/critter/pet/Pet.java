package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.user.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "pet")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private PetType type;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Customer owner;

    @Transient
    private long ownerId;

    private String notes;

    public Customer getOwner() {
        return this.owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;

        if (this.owner != null) {
            this.ownerId = owner.getId();
        }

    }

    public PetDTO convertToDto() {

        if (this.owner != null) {
            this.ownerId = this.owner.getId();
        }

        PetDTO petDTO = new PetDTO();

        BeanUtils.copyProperties(this, petDTO);

        return petDTO;

    }

}
