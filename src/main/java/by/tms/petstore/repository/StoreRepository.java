package by.tms.petstore.repository;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Order, Integer> {
}
