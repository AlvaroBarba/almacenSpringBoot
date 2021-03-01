package com.proyecto.alvaro.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties(value = {"clientPedidos"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonIgnoreProperties(value = {"pedidos"}, allowSetters = true)
    @OneToMany(mappedBy = "pedidos", cascade = {CascadeType.MERGE})
    private List<Item_Order> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        List<Pedidos> pedidos = this.client.getOrders();
        if(pedidos == null){
            pedidos = new ArrayList<>();
        }
        if(!pedidos.contains(this)){
            pedidos.add(this);
        }
    }

    public List<Item_Order> getItems() {
        return items;
    }

    public void setItems(List<Item_Order> pedidos) {
        this.items = pedidos;
        for(Item_Order a : pedidos){
            a.setOrder(this);
        }
    }
}
