package com.example.H2R_Alexis_Arriola_CC7.repository;

import com.example.H2R_Alexis_Arriola_CC7.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByActivoTrue(boolean activo);
}