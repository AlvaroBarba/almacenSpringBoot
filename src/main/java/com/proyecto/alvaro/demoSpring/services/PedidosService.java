package com.proyecto.alvaro.demoSpring.services;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Pedidos;
import com.proyecto.alvaro.demoSpring.repositories.PedidosRepost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    PedidosRepost repository;

    public Pedidos createOrder(Pedidos pedidos){
        pedidos = repository.save(pedidos);
        return pedidos;
    }

    public Pedidos updateOrder(Pedidos pedidos){
        if(pedidos.getId() != null){
            Optional<Pedidos> aux = repository.findById(pedidos.getId());
            if(aux.isPresent()){
                Pedidos newPedidos = aux.get();
                newPedidos.setClient(pedidos.getClient());
                newPedidos.setItems(pedidos.getItems());

                newPedidos = repository.save(newPedidos);

                return newPedidos;
            }else{
                throw new RecordNotFoundException("Pedido no encontrado", pedidos.getId());
            }
        }else{
            throw new RecordNotFoundException("Id vacío", 0l);
        }
    }

    public void deleteOrderById(Long id) throws RecordNotFoundException{
        Optional<Pedidos> order = repository.findById(id);

        if(order.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No hay pedidos con esa id", id);
        }
    }

    public List<Pedidos> getAllOrders(){
        List<Pedidos> pedidos = repository.findAll();
        if(pedidos.size() > 0){
            return pedidos;
        }else{
            return new ArrayList<>();
        }
    }

    public Pedidos getOrderById(Long id){
        Pedidos pedidos = null;
        pedidos = repository.findById(id).get();
        if(pedidos != null){
            return pedidos;
        }else{
            return pedidos = new Pedidos();
        }
    }
}
