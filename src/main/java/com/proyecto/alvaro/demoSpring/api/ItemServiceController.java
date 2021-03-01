package com.proyecto.alvaro.demoSpring.api;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Item;
import com.proyecto.alvaro.demoSpring.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Item")
public class ItemServiceController {

    @Autowired
    ItemService service;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items = service.getAllItems();
        return new ResponseEntity<List<Item>>(items, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Item item = service.getItemById(id);
        return new ResponseEntity<Item>(item, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Item>> getItemsByName(@PathVariable("name") String name) throws RecordNotFoundException{
        List<Item> items = service.getItemByName(name);
        return new ResponseEntity<List<Item>>(items, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/price/asc")
    public ResponseEntity<List<Item>> getItemsByPriceASC() throws RecordNotFoundException{
        List<Item> items = service.getItemsByPriceASC();
        return new ResponseEntity<List<Item>>(items, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/price/desc")
    public ResponseEntity<List<Item>> getItemsByPriceDESC() throws RecordNotFoundException{
        List<Item> items = service.getItemsByPriceDESC();
        return new ResponseEntity<List<Item>>(items, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) throws RecordNotFoundException{
        Item aux = service.createItem(item);
        return new ResponseEntity<Item>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) throws  RecordNotFoundException{
        Item aux = service.updateItem(item);
        return new ResponseEntity<Item>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteItemById(id);
        return HttpStatus.FORBIDDEN;
    }
}
