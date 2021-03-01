package com.proyecto.alvaro.demoSpring.api;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Item_Order;
import com.proyecto.alvaro.demoSpring.services.Item_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Item_Order")
public class Item_OrderServiceController {

    @Autowired
    Item_OrderService service;

    @GetMapping
    public ResponseEntity<List<Item_Order>> getAllItemOrders(){
        List<Item_Order> item_orders = service.getAllItemOrders();
        return new ResponseEntity<List<Item_Order>>(item_orders, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item_Order> getItemOrderById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Item_Order item_order = service.getItemOrderById(id);
        return new ResponseEntity<Item_Order>(item_order, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/quantity/desc")
    public ResponseEntity<List<Item_Order>> getItemOrdersByQuantityDESC() throws RecordNotFoundException{
        List<Item_Order> item_orders = service.getItemOrdersByQuantityDESC();
        return new ResponseEntity<List<Item_Order>>(item_orders, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/quantity/asc")
    public ResponseEntity<List<Item_Order>> getItemOrdersByQuantityASC() throws RecordNotFoundException{
        List<Item_Order> item_orders = service.getItemOrdersByQuantityASC();
        return new ResponseEntity<List<Item_Order>>(item_orders, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Integer> getItemOrdersCount(@PathVariable("id") Long id) throws RecordNotFoundException{
        int aux = service.getItemOrdersCount(id);
        return new ResponseEntity<Integer>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item_Order> createItemOrder(@Valid @RequestBody Item_Order item_order) throws RecordNotFoundException{
        Item_Order aux = service.createItemOrder(item_order);
        return new ResponseEntity<Item_Order>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item_Order> updateItemOrder(@Valid @RequestBody Item_Order item_order) throws  RecordNotFoundException{
        Item_Order aux = service.updateItemOrder(item_order);
        return new ResponseEntity<Item_Order>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteItemOrderById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteItemOrderById(id);
        return HttpStatus.FORBIDDEN;
    }
}
