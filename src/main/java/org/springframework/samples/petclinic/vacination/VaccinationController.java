package org.springframework.samples.petclinic.vacination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;
    private PetService petService;
    private static final String VISTA="vaccination/createOrUpdateVaccinationForm";

    @Autowired
    public VaccinationController(VaccinationService vaccinationService, PetService petService) {
        this.vaccinationService = vaccinationService;
        this.petService=petService;
    }

    @GetMapping(value="/create")
    public String initCreationForm(ModelMap model) {
        Vaccination v = new Vaccination();
        model.put("vaccination", v);
        model.put("vaccines", vaccinationService.getAllVaccines());
        model.put("pets", petService.findAllPets());
        return VISTA;
    }

    @PostMapping(value="/create")
    public String processCreationForm(@Valid Vaccination vaccination, BindingResult result, ModelMap model) throws UnfeasibleVaccinationException {
        if(result.hasErrors()){
            model.put("vaccination", vaccination);
            model.put("vaccines", vaccinationService.getAllVaccines());
            model.put("pets", petService.findAllPets());
            return VISTA;
        } else{
            try{
                this.vaccinationService.save(vaccination);

            }catch(UnfeasibleVaccinationException ex){
                result.reject("La mascota seleccionada no puede recibir la vacuna especificada.");
                return VISTA;
            }
            return "welcome";


        }

    }

}
