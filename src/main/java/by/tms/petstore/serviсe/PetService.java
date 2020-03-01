package by.tms.petstore.serviсe;

import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet findPet(int petId){
        petRepository.save(new Pet(1,new Category(1,"CAT"),"Tom", null, Pet.Status.AVAILABLE));
        Optional<Pet> byId = petRepository.findById(petId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    public Pet updPet(int petId, String name, Pet.Status status) {
        petRepository.save(new Pet(1,new Category(1,"CAT"),"Tom", null, Pet.Status.AVAILABLE));
        Optional<Pet> byId = petRepository.findById(petId);
        if(byId.isPresent()){
            byId.get().setName(name);
            byId.get().setStatus(status);
            return byId.get();
        }
        return null;
    }

    public boolean deletePet(int petId) {
        petRepository.save(new Pet(1,new Category(1,"CAT"),"Tom", null, Pet.Status.AVAILABLE));
        Optional<Pet> byId = petRepository.findById(petId);
        if(byId.isPresent()){
            petRepository.delete(byId.get());
            return true;
        }
        return false;
    }

    public boolean addPet(Pet pet){
        Optional<Pet> byId = petRepository.findById(pet.getId());
        if(!byId.isPresent()){
            petRepository.save(pet);
            return true;
        }
        return false;
    }

    public boolean updPetFull(Pet pet){
        petRepository.save(new Pet(1,new Category(1,"CAT"),"Tom", null, Pet.Status.AVAILABLE));
        Optional<Pet> byId = petRepository.findById(pet.getId());
        if(byId.isPresent()){
           petRepository.deleteById(pet.getId());
           petRepository.save(pet);
        }
        return false;
    }

    public List<Pet> findPetByStatus(Pet.Status status){
        petRepository.save(new Pet(4,new Category(1,"CAT"),"Tommy",null, Pet.Status.AVAILABLE));
        petRepository.save(new Pet(5,new Category(1,"CAT"),"Timmy", null, Pet.Status.AVAILABLE));
        List<Pet> petsFoundByStatus = new ArrayList<>();
        List<Pet> pets = petRepository.findAll();
       for(Pet pet1 : pets){
           if(pet1.getStatus().equals(status)){
               petsFoundByStatus.add(pet1);
           }
       }
       return petsFoundByStatus;
    }

}
