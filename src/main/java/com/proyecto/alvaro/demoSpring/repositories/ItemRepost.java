package com.proyecto.alvaro.demoSpring.repositories;

import com.proyecto.alvaro.demoSpring.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepost extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM item WHERE name = ?1", nativeQuery = true)
    public List<Item> getItemsByName(String name);

    @Query(value = "SELECT * FROM item ORDER BY price ASC", nativeQuery = true)
    public List<Item> getItemsByPriceASC();

    @Query(value = "SELECT * FROM item ORDER BY price DESC", nativeQuery = true)
    public List<Item> getItemsByPriceDESC();

}
