package com.proyecto.alvaro.demoSpring.services;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Client;
import com.proyecto.alvaro.demoSpring.model.Pedidos;
import com.proyecto.alvaro.demoSpring.repositories.ClientRepost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepost repository;

    public Client createClient(Client client){
        client = repository.save(client);
        return client;
    }

    public Client updateClient(Client client){
        if(client.getId() != null){
            Optional<Client> aux = repository.findById(client.getId());
            if(aux.isPresent()){
                Client newClient = aux.get();
                newClient.setName(client.getName());
                newClient.setLastName(client.getLastName());
                newClient.setPhone(client.getPhone());
                newClient.setAddress(client.getAddress());
                newClient.setOrders(client.getOrders());

                newClient = repository.save(newClient);

                return newClient;
            }else{
                throw new RecordNotFoundException("Cliente no encontrado", client.getId());
            }
        }else{
            throw new RecordNotFoundException("Id vacío", 0l);
        }
    }

    public void deleteClientById(Long id) throws RecordNotFoundException{
        Optional<Client> client = repository.findById(id);

        if(client.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No hay clientes con esa id", id);
        }
    }

    public List<Client> getAllClients(){
        List<Client> clients = repository.findAll();
        if(clients.size() > 0){
            return clients;
        }else{
            return new ArrayList<>();
        }
    }

    public Client getClientById(Long id){
        Client client = null;
        client = repository.findById(id).get();
        if(client != null){
            return client;
        }else{
           return client = new Client();
        }
    }

    public List<Client> getClientByName(String name){
        List<Client> clients = repository.getClientByName(name);
        if(clients.size() > 0){
            return clients;
        }else{
            return new ArrayList<>();
        }
    }

    public List<Pedidos> getOrdersOfClient(Long id){
        List<Pedidos> pedidos = repository.getClientOrders(id);
        if(pedidos.size() > 0){
            return pedidos;
        }else{
            return new ArrayList<>();
        }
    }
}
