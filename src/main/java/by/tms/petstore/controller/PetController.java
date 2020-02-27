package by.tms.petstore.controller;

import by.tms.petshop.exception.InvalidIDException;
import by.tms.petstore.exception.petException.*;
import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.Tag;
import by.tms.petstore.model.Category;
import by.tms.petstore.serviсe.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Validated
public class PetController {
    PetService petService = new PetService();

    @GetMapping(path = "/{petId}")
    public ResponseEntity<Pet> findPet(@PathVariable("petId") @Min(1) int petId){
        if(petService.findPet(petId).equals(null)){
            throw new PetNotFoundException();
        }
            return new ResponseEntity<>(petService.findPet(petId), HttpStatus.OK);
    }

    @PostMapping(path = "/{petId}")
    public ResponseEntity<ApiResponse> updatePet(@PathVariable("petId") @Min(1) int petId,
                                                 @RequestParam @NotNull String name, @RequestParam Pet.Status status) {
        if(petService.updPet(petId, name, status) == null){
            throw new InvalidInputException();
        } else {
            return new ResponseEntity( new ApiResponse("Successfully deleted"), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{petId}")
    public ResponseEntity<ApiResponse> deletePet(@RequestParam @NotNull String apiKey, @PathVariable("petId") @Min(1) int petId){
        if(!petService.deletePet(petId)){
           throw new PetNotFoundException();
       }
        return new ResponseEntity( new ApiResponse("Successfully deleted"), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPet(@RequestBody Pet pet){
        if(!petService.addPet(pet)){
            System.out.println(petService.getPets().toString());
            throw new InvalidInputException();
        }
        return new ResponseEntity( new ApiResponse("Successfully added"), HttpStatus.OK );
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updPet(@NotNull @RequestBody Pet pet){
        if(pet.getId() <= 0){
            throw new InvalidIDException();
        }
        if(pet.getName()==null || pet.getCategory()==null || pet.getStatus()==null){
            throw new ValidationException();
        }
       if(!petService.updPetFull(pet)){
           throw new PetNotFoundException();
       }
       return new ResponseEntity( new ApiResponse("Successfully updated"), HttpStatus.OK );
    }


    @GetMapping(path = "/findByStatus")
    public ResponseEntity<ApiResponse> findPetByStatus(@RequestParam @NotNull Pet.Status status){
        return new ResponseEntity(petService.findPetByStatus(status),HttpStatus.OK);
    }

}
