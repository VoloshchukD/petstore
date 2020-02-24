package by.tms.petstore.controller;

import by.tms.petstore.exception.petException.InvalidInputException;
import by.tms.petstore.exception.storeException.OrderNotFoundException;
import by.tms.petstore.model.ApiResponse;
import by.tms.petstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    @Qualifier("map")
    public Map<String, Integer> map;

    @Autowired
    @Qualifier("orders")
    public List<Order> orders;

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map> retMap() {
        map.put("additionalProp1", 0);
        map.put("additionalProp2", 0);
        map.put("additionalProp3", 0);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<Order> findOrder(@PathVariable("orderId") int orderId) {
        if (orderId > 10 || orderId < 1) {
            throw new by.tms.petshop.exception.InvalidIDException();
        }
        for (Order order1 : orders) {
            if (order1.getId() == orderId) {
                return new ResponseEntity(order1, HttpStatus.OK);
            }
        }
        throw new OrderNotFoundException();
    }

    @DeleteMapping(path = "/order/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("orderId") int orderId) {
        if(orderId<=0){
            throw new by.tms.petshop.exception.InvalidIDException();
        }
        for(Order order1: orders){
            if(order1.getId()==orderId){
                orders.remove(order1);
            } else {
                throw new OrderNotFoundException();
            }
        }
        return new ResponseEntity( new ApiResponse(200,"Response","Successfully deleted"), HttpStatus.OK );
    }

    @PostMapping(path = "/order")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody Order order){
        if(order.getId()<=0 || order.getPetId()<=0 || order.getQuantity()<=0 || order.getShipDate()==null){
            throw new InvalidInputException();
        }
        for(Order order1 : orders){
            if(order1.getId() == order.getId() || order.getPetId() == order1.getPetId()){
                throw new InvalidInputException();
            }
        }
        orders.add(order);
        return new ResponseEntity( new ApiResponse(200,"Result","Successfully added"), HttpStatus.OK );
    }

}

