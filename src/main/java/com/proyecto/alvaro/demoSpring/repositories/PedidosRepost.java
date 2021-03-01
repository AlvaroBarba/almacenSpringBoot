package com.proyecto.alvaro.demoSpring.repositories;

import com.proyecto.alvaro.demoSpring.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepost extends JpaRepository<Pedidos, Long> {
}
