package com.proyecto.alvaro.demoSpring.api;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Client;
import com.proyecto.alvaro.demoSpring.model.Pedidos;
import com.proyecto.alvaro.demoSpring.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientServiceController {

    @Autowired
    ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient(){
        List<Client> clients = service.getAllClients();
        return new ResponseEntity<List<Client>>(clients, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Client client = service.getClientById(id);
        return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable("name") String name) throws RecordNotFoundException{
        List<Client> clients = service.getClientByName(name);
        return new ResponseEntity<List<Client>>(clients, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) throws RecordNotFoundException{
        Client aux = service.createClient(client);
        return new ResponseEntity<Client>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) throws  RecordNotFoundException{
        Client aux = service.updateClient(client);
        return new ResponseEntity<Client>(aux, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClientById(@PathVariable("id") Long id) throws RecordNotFoundException{
        service.deleteClientById(id);
        return HttpStatus.FORBIDDEN;
    }

}
