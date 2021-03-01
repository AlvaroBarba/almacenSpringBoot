package com.proyecto.alvaro.demoSpring.repositories;

import com.proyecto.alvaro.demoSpring.model.Client;
import com.proyecto.alvaro.demoSpring.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepost extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM client WHERE name = ?1", nativeQuery = true)
    public List<Client> getClientByName(String name);


}
