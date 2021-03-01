package com.proyecto.alvaro.demoSpring.services;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Item;
import com.proyecto.alvaro.demoSpring.model.Item_Order;
import com.proyecto.alvaro.demoSpring.repositories.Item_Order_Repost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Item_OrderService {

    @Autowired
    Item_Order_Repost repository;

    public Item_Order createItemOrder(Item_Order item_order){
        item_order = repository.save(item_order);
        return item_order;
    }

    public Item_Order updateItemOrder(Item_Order item_order){
        if(item_order.getId() != null){
            Optional<Item_Order> aux = repository.findById(item_order.getId());
            if(aux.isPresent()){
                Item_Order newItemOrder = aux.get();
                newItemOrder.setItem(item_order.getItem());
                newItemOrder.setOrder(item_order.getOrder());
                newItemOrder.setQuantity(item_order.getQuantity());

                newItemOrder = repository.save(newItemOrder);

                return newItemOrder;
            }else{
                throw new RecordNotFoundException("EL pedido no se ha encontrado", item_order.getId());
            }
        }else{
            throw new RecordNotFoundException("Id vacío", 0l);
        }
    }

    public void deleteItemOrderById(Long id) throws RecordNotFoundException{
        Optional<Item_Order> item_order = repository.findById(id);

        if(item_order.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No hay pedidos con esa id", id);
        }
    }

    public List<Item_Order> getAllItemOrders(){
        List<Item_Order> item_orders = repository.findAll();
        if(item_orders.size() > 0){
            return item_orders;
        }else{
            return new ArrayList<>();
        }
    }

    public Item_Order getItemOrderById(Long id){
        Item_Order item_order = null;
        item_order = repository.findById(id).get();
        if(item_order != null){
            return item_order;
        }else{
            return item_order = new Item_Order();
        }
    }

    public List<Item_Order> getItemOrdersByQuantityDESC(){
        List<Item_Order> item_orders = repository.getItemOrdersByQuantityDESC();
        if(item_orders.size() > 0 ){
            return item_orders;
        }else{
            return item_orders = new ArrayList<>();
        }
    }

    public List<Item_Order> getItemOrdersByQuantityASC(){
        List<Item_Order> item_orders = repository.getItemOrdersByQuantityASC();
        if(item_orders.size() > 0 ){
            return item_orders;
        }else{
            return item_orders = new ArrayList<>();
        }
    }

    public int getItemOrdersCount(Long id){
        int item_orders = 0;
        item_orders = repository.getItemOrdersCount(id);
        return item_orders;
    }
}
