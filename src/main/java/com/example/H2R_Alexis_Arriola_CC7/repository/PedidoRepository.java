package com.example.H2R_Alexis_Arriola_CC7.repository;

import com.example.H2R_Alexis_Arriola_CC7.model.Estado;
import com.example.H2R_Alexis_Arriola_CC7.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Estado findByEstado(Estado estado);
}