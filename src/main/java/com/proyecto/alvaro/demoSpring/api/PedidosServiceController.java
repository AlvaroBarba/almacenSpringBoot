package com.proyecto.alvaro.demoSpring.api;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Pedidos;
import com.proyecto.alvaro.demoSpring.services.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class PedidosServiceController {

    @Autowired
    PedidosService service;

    @GetMapping
    public ResponseEntity<List<Pedidos>> getAllOrders(){
        List<Pedidos> pedidos = service.getAllOrders();
        return new ResponseEntity<List<Pedidos>>(pedidos, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getOrderById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Pedidos pedidos = service.getOrderById(id);
        return new ResponseEntity<Pedidos>(pedidos, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedidos> createOrder(@Valid @RequestBody Pedidos pedidos) throws RecordNotFoundException{
        Pedidos aux = service.createOrder(pedidos);
        return new ResponseEntity<Pedidos>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Pedidos> updateOrder(@Valid @RequestBody Pedidos pedidos) throws  RecordNotFoundException{
        Pedidos aux = service.updateOrder(pedidos);
        return new ResponseEntity<Pedidos>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClientById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteOrderById(id);
        return HttpStatus.FORBIDDEN;
    }
}
