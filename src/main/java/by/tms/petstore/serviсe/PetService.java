package by.tms.petstore.serviсe;


import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetService {

    private List<Pet> pets = new ArrayList<>();

    public List<Pet> getPets() {
        return pets;
    }



    public Pet findPet(int petId){
        pets.add(new Pet(1,new Category(1,"CAT"),"Tom",null,null, Pet.Status.AVAILABLE));
        for(Pet pet1: pets){
            if(pet1.getId()==petId){
                return pet1;
            }
        }
        return null;
    }

    public Pet updPet(int petId, String name, Pet.Status status) {
        pets.add(new Pet(1,new Category(1,"CAT"),"Tom",null,null, Pet.Status.AVAILABLE));
        for (Pet pet1 : pets) {
            if (pet1.getId() == petId) {
                pet1.setName(name);
                pet1.setStatus(status);
                return pet1;
            }
        }
        return null;
    }

    public boolean deletePet(int petId) {
        pets.add(new Pet(1,new Category(1,"CAT"),"Tom",null,null, Pet.Status.AVAILABLE));
        Pet pd = new Pet();
        for (Pet pet1 : pets) {
            if (pet1.getId() == petId) {
                pd = pet1;
            }
        }
        if (pets.remove(pd)) {
            return true;
        }
        return false;
    }

    public boolean addPet(Pet pet){
        if (pets.contains(pet)) {
            return false;
        }
            pets.add(pet);
            return true;
    }

    public boolean updPetFull(Pet pet){
        pets.add(new Pet(1,new Category(1,"CAT"),"Tom",null,null, Pet.Status.AVAILABLE));
        boolean petExist = false;
        for (Pet pet1 : pets) {
            if (pet1.getId() == pet.getId()) {
              petExist = true;
            }
        }
        if (petExist) {
            pets.remove(pet);
            pets.add(pet);
            return true;
        }
        return false;
    }

    public List<Pet> findPetByStatus(Pet.Status status){
        pets.add(new Pet(4,new Category(1,"CAT"),"Tommy",null,null, Pet.Status.AVAILABLE));
        pets.add(new Pet(5,new Category(1,"CAT"),"Timmy",null,null, Pet.Status.AVAILABLE));
        List<Pet> petsFoundByStatus = new ArrayList<>();
        for (Pet pet1 : pets) {
            if (pet1.getStatus() == status) {
                petsFoundByStatus.add(pet1);
            }
        }
        return petsFoundByStatus;
    }

}
