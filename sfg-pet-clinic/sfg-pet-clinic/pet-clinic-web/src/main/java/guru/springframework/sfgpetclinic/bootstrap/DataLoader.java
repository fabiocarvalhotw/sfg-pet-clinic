package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//Initialize data
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            LocalData();
        }
    }

    private void LocalData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        System.out.println("load pet type...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

        System.out.println("Load specialities...");

        Owner michael = new Owner();
        michael.setFirstName("Michael");
        michael.setLastName("Weston");
        michael.setAddress("123 Brickerel");
        michael.setCity("Miami");
        michael.setTelephone("1232321231");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setOwner(michael);
        mikesPet.setName("Doug");
        mikesPet.setBirthDate(LocalDate.now());

        Owner fiona = new Owner();
        fiona.setFirstName("Fiona");
        fiona.setLastName("Glenanne");
        fiona.setAddress("123 Brickerel");
        fiona.setCity("Miami");
        fiona.setTelephone("1232321231");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(saveCatPetType);
        fionasPet.setOwner(fiona);
        fionasPet.setName("Harley");
        fionasPet.setBirthDate(LocalDate.now());

        ownerService.save(michael);
        ownerService.save(fiona);

        //Visit catVisit = new Visit();
        //catVisit.setPet(fionasPet);
        // catVisit.setDate(LocalDate.now());
        // catVisit.setDescription("Sneezy Kitty");
        // visitService.save(catVisit);


        System.out.println("Load owners....");

        Vet vetSam = new Vet();
        vetSam.setFirstName("Sam");
        vetSam.setLastName("Axe");
        vetSam.getSpecialities().add(saveRadiology);

        Vet vetJessie = new Vet();
        vetJessie.setFirstName("Jessie");
        vetJessie.setLastName("Cobra");
        vetJessie.getSpecialities().add(saveSurgery);

        vetService.save(vetSam);
        vetService.save(vetJessie);

        System.out.println("Load vets....");
    }
}
