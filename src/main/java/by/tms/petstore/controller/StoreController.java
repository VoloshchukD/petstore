package by.tms.petstore.controller;

import by.tms.petstore.exception.petException.InvalidInputException;
import by.tms.petstore.exception.petException.PetNotFoundException;
import by.tms.petstore.exception.storeException.InvalidOrderException;
import by.tms.petstore.exception.storeException.OrderNotFoundException;
import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Order;
import by.tms.petstore.serviсe.PetService;
import by.tms.petstore.serviсe.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
@Validated
public class StoreController {

   private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map> retMap() {
      return new ResponseEntity(storeService.retMap(), HttpStatus.OK);
    }

    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<Order> findOrder(@PathVariable("orderId") @Min(1) @Max(10) int orderId) {
       if(storeService.findOrder(orderId).equals(null)){
           throw new OrderNotFoundException();
       }
        return new ResponseEntity(storeService.findOrder(orderId).equals(null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/order/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("orderId")  @Min(1)int orderId) {
        if(!storeService.deleteOrder(orderId)){
            throw new OrderNotFoundException();
        }
        return new ResponseEntity( new ApiResponse("Successfully deleted"), HttpStatus.OK );
    }

    @PostMapping(path = "/order")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody @NotNull Order order){
        if(order.getId()<=0 || order.getPetId()<=0 || order.getQuantity()<=0 || order.getShipDate()==null){
            throw new InvalidOrderException();
        }
       if(!storeService.addOrder(order)){
           throw new InvalidInputException();
       }
        return new ResponseEntity( new ApiResponse("Successfully added"), HttpStatus.CREATED);
    }

}

