package org.springframework.samples.petclinic.vacination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccinationService {

    private VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository){
        this.vaccinationRepository=vaccinationRepository;
    }
    @Transactional
    public List<Vaccination> getAll(){
        return vaccinationRepository.findAll();
    }

    @Transactional
    public List<Vaccine> getAllVaccines(){
        return vaccinationRepository.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vaccinationRepository.getVaccine(typeName);
    }
    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
        Vaccine v=vaccinationRepository.getVaccine(p.vaccine.name);
        if(p.vaccinatedPet.getType()==v.petType){
            return vaccinationRepository.save(p);
        }else{
           throw new UnfeasibleVaccinationException();
        }

    }

    
}
