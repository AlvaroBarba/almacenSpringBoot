package com.proyecto.alvaro.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;

    @JsonIgnoreProperties(value = {"client"}, allowSetters = true)
    @OneToMany(mappedBy = "client", cascade = {CascadeType.MERGE})
    private List<Pedidos> clientPedidos;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Pedidos> getOrders() {
        return clientPedidos;
    }

    public void setOrders(List<Pedidos> pedidos) {
        this.clientPedidos = pedidos;
        if(pedidos != null && !pedidos.isEmpty()){
            for(Pedidos a : pedidos){
                a.setClient(this);
            }
        }
    }
}
