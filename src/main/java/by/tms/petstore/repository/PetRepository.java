package by.tms.petstore.repository;

import by.tms.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
