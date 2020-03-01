package by.tms.petstore;

import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.serviсe.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetstoreApplicationTests {

//    PetService petService = new PetService(petRepository);
//
//    @Test
//    void findPet() {
//
//        assertEquals(1,petService.findPet(1).getId());
//
//    }
//
//    @Test
//    void addPet() {
//        assertTrue(petService.addPet(new Pet(2,new Category(1,"CAT"),"Tom2",null,null, Pet.Status.PENDING)));
//    }
//
//    @Test
//    void updPet() {
//        assertEquals(1,petService.updPet(1, "Tom", Pet.Status.AVAILABLE).getId());
//    }
//
//    @Test
//    void deletePet() {
//        assertTrue(petService.deletePet(1));
//    }
//
//    @Test
//    void updFullPet() {
//        assertTrue(petService.updPetFull(new Pet(1,new Category(1,"CAT"),"Tom2",null,null, Pet.Status.PENDING)));
//    }
//
//    @Test
//    void findPetByStatus() {
//        assertEquals(Pet.Status.AVAILABLE,petService.findPetByStatus(Pet.Status.AVAILABLE).get(1).getStatus());
//    }

}
