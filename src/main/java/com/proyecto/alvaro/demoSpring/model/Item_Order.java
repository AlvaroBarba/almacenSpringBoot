package com.proyecto.alvaro.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_order")
public class Item_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties(value = {"pedidos"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "id_item")
    private Item item;

    @JsonIgnoreProperties(value = {"items"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "id_pedido")
    private Pedidos pedidos;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        List<Item_Order> items = this.item.getPedidos();
        if(items == null){
            items = new ArrayList<>();
        }
        if(!items.contains(this)){
            items.add(this);
        }
    }

    public Pedidos getOrder() {
        return pedidos;
    }

    public void setOrder(Pedidos pedidos) {
        this.pedidos = pedidos;
        List<Item_Order> ItemPedidos = this.pedidos.getItems();
        if(ItemPedidos == null){
            ItemPedidos = new ArrayList<>();
        }
        if(!ItemPedidos.contains(this)){
            ItemPedidos.add(this);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
