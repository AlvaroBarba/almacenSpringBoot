package com.proyecto.alvaro.demoSpring.repositories;

import com.proyecto.alvaro.demoSpring.model.Item_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Item_Order_Repost extends JpaRepository<Item_Order, Long> {

    @Query(value = "SELECT * FROM item_order ORDER BY quantity DESC", nativeQuery = true)
    public List<Item_Order> getItemOrdersByQuantityDESC();

    @Query(value = "SELECT * FROM item_order ORDER BY quantity ASC", nativeQuery = true)
    public List<Item_Order> getItemOrdersByQuantityASC();

    @Query(value = "SELECT COUNT(*) FROM item_order WHERE id_item = ?1", nativeQuery = true)
    public int getItemOrdersCount(Long id_item);

    }
