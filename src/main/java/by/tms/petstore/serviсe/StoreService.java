package by.tms.petstore.serviсe;

import by.tms.petstore.model.Category;
import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import by.tms.petstore.repository.PetRepository;
import by.tms.petstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Autowired
    private Map<String, Integer> map;

    public Map retMap () {
        map.put("additionalProp1", 0);
        map.put("additionalProp2", 0);
        map.put("additionalProp3", 0);
        return map;
    }

    public Order findOrder(int orderId){
        Optional<Order> byId = storeRepository.findById(orderId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    public boolean deleteOrder(int orderId) {
        Optional<Order> byId = storeRepository.findById(orderId);
        if(byId.isPresent()){
            storeRepository.delete(byId.get());
            return true;
        }
        return false;
    }

    public boolean addOrder(Order order){
        if(!storeRepository.existsById(order.getId())){
            storeRepository.save(order);
            return true;
        }
        return false;
    }

}
