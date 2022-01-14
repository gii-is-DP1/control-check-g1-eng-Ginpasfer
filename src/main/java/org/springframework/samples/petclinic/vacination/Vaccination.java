package org.springframework.samples.petclinic.vacination;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vaccination")
public class Vaccination extends BaseEntity {
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name="vaccinated_pet_id")
    Pet vaccinatedPet;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    Vaccine vaccine; 
}
