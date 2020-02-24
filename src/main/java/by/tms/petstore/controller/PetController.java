package by.tms.petstore.controller;

import by.tms.petshop.exception.InvalidIDException;
import by.tms.petstore.exception.petException.*;
import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.Tag;
import by.tms.petstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    @Qualifier("pets")
    public List<Pet> pets;

    @GetMapping(path = "/{petId}")
    public ResponseEntity<Pet> findPet(@PathVariable("petId") int petId){
        List<String> photoUrls = null;
        List<Tag> tags = null;
        pets.add(new Pet(12345,new Category(1,"CAT"),"Tom",photoUrls,tags, Pet.Status.AVAILABLE));
        pets.add(new Pet(12344,new Category(1,"CAT"),"Tom",photoUrls,tags, Pet.Status.AVAILABLE));

        for(Pet pet1: pets){
            if(pet1.getId()==petId){
                return new ResponseEntity(pet1, HttpStatus.OK);
            }
        }
        throw new PetNotFoundException();
    }

    @PostMapping(path = "/{petId}")
    public ResponseEntity<ApiResponse> updatePet(@PathVariable("petId") int petId,
                                                 @RequestParam String name, @RequestParam Pet.Status status) {
        for(Pet pet1: pets) {
            if (pet1.getId() == petId) {
                pet1.setName(name);
                pet1.setStatus(status);
                return new ResponseEntity(new ApiResponse(200, "Response", "Successfully updated"), HttpStatus.OK);
            }
        }
        throw new InvalidInputException();
    }

    @DeleteMapping(path = "/{petId}")
    public ResponseEntity<ApiResponse> deletePet(@RequestParam String apiKey, @PathVariable("petId") int petId){
        if(petId<=0){
            throw new InvalidIDException();
        }
        for(Pet pet1: pets){
            if(pet1.getId()==petId){
                pets.remove(pet1);
            } else {
                throw new PetNotFoundException();
            }
        }
        return new ResponseEntity( new ApiResponse(200,"Response","Successfully deleted"), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPet(@RequestBody Pet pet){
        if(pets.contains(pet)){
            throw new InvalidInputException();
        }
        pets.add(pet);
        return new ResponseEntity( new ApiResponse(200,"Result","Successfully added"), HttpStatus.OK );
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updPet(@RequestBody Pet pet){
        if(pet.getId() <= 0){
            throw new InvalidIDException();
        }
        if(pet.getName()==null || pet.getCategory()==null || pet.getStatus()==null){
            throw new ValidationException();
        }
        for(Pet pet1: pets){
            if(pet1.getId()==pet.getId()){
                pets.remove(pet1);
                pets.add(pet);
            } else {
                throw new PetNotFoundException();
            }
        }
        return new ResponseEntity( new ApiResponse(200,"Result","Successfully updated"), HttpStatus.OK );
    }

    @GetMapping(path = "/findByStatus")
    public ResponseEntity<ApiResponse> findPetByStatus(@RequestParam Pet.Status status){
        List<Pet> lst = new ArrayList<>();
        if (status == null) {
            throw new InvalidStatusException();
        }
        for(Pet pet1: pets){
            if(pet1.getStatus()==status){
                lst.add(pet1);
            }
        }
        return new ResponseEntity(lst,HttpStatus.OK);
    }

}
