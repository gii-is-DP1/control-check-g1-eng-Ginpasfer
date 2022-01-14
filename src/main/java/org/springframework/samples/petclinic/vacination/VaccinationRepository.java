package org.springframework.samples.petclinic.vacination;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VaccinationRepository extends CrudRepository<Vaccination,Integer> {
    @Query("SELECT vaccination FROM Vaccination vaccination ORDER BY vaccination.date")
    List<Vaccination> findAll();

    @Query("SELECT vaccine FROM Vaccine vaccine ORDER BY vaccine.name")
    List<Vaccine> findAllVaccines();

    Optional<Vaccination> findById(int id);

    @Query("SELECT vaccine FROM Vaccine vaccine WHERE vaccine.name= :name")
    Vaccine getVaccine(@Param("name") String name);

    Vaccination save(Vaccination p);
}
