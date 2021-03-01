package com.proyecto.alvaro.demoSpring.services;

import com.proyecto.alvaro.demoSpring.execption.RecordNotFoundException;
import com.proyecto.alvaro.demoSpring.model.Client;
import com.proyecto.alvaro.demoSpring.model.Item;
import com.proyecto.alvaro.demoSpring.repositories.ItemRepost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepost repository;

    public Item createItem(Item item){
        item = repository.save(item);
        return item;
    }

    public Item updateItem(Item item){
        if(item.getId() != null){
            Optional<Item> aux = repository.findById(item.getId());
            if(aux.isPresent()){
                Item newItem = aux.get();
                newItem.setName(item.getName());
                newItem.setPrice(item.getPrice());
                newItem.setItems(item.getItems());

                newItem = repository.save(newItem);

                return newItem;
            }else{
                throw new RecordNotFoundException("Producto no encontrado", item.getId());
            }
        }else{
            throw new RecordNotFoundException("Id vacío", 0l);
        }
    }

    public void deleteItemById(Long id) throws RecordNotFoundException{
        Optional<Item> item = repository.findById(id);

        if(item.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No hay productos con esa id", id);
        }
    }

    public List<Item> getAllItems(){
        List<Item> items = repository.findAll();
        if(items.size() > 0){
            return items;
        }else{
            return new ArrayList<>();
        }
    }

    public Item getItemById(Long id){
        Item item = null;
        item = repository.findById(id).get();
        if(item != null){
            return item;
        }else{
            return item = new Item();
        }
    }

    public List<Item> getItemByName(String name){
        List<Item> items = repository.getItemsByName(name);
        if(items.size() > 0){
            return items;
        }else{
            return new ArrayList<>();
        }
    }

    public List<Item> getItemsByPriceASC(){
        List<Item> items = repository.getItemsByPriceASC();
        if(items.size() > 0){
            return items;
        }else{
            return new ArrayList<>();
        }
    }

    public List<Item> getItemsByPriceDESC(){
        List<Item> items = repository.getItemsByPriceDESC();
        if(items.size() > 0){
            return items;
        }else{
            return new ArrayList<>();
        }
    }
}
