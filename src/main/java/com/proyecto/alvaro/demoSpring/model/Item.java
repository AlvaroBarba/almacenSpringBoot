package com.proyecto.alvaro.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @JsonIgnoreProperties(value = {"item"}, allowSetters = true)
    @OneToMany(mappedBy = "item", cascade = {CascadeType.MERGE})
    private List<Item_Order> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Item_Order> getItems() {
        return items;
    }

    public void setItems(List<Item_Order> items) {
        this.items = items;
        for(Item_Order a : items){
            a.setItem(this);
        }
    }
}
