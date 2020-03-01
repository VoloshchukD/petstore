package by.tms.petstore.repository;

import by.tms.petstore.model.Pet;
import by.tms.petstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
