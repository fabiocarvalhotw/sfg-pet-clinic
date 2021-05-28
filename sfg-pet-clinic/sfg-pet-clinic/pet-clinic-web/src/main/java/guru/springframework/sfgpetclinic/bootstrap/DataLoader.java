package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Initialize data
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner michael = new Owner();
        michael.setId(1L);
        michael.setFirstName("Michael");
        michael.setLastName("Weston");

        Owner fiona = new Owner();
        fiona.setId(2l);
        fiona.setFirstName("Fiona");
        fiona.setLastName("Glenanne");

        ownerService.save(michael);
        ownerService.save(fiona);

        Vet vetSam = new Vet();
        vetSam.setId(1L);
        vetSam.setFirstName("Sam");
        vetSam.setLastName("Axe");

        Vet vetJessie = new Vet();
        vetJessie.setId(2L);
        vetJessie.setFirstName("Jessie");
        vetJessie.setLastName("Cobra");

        vetService.save(vetSam);
        vetService.save(vetJessie);

    }
}
