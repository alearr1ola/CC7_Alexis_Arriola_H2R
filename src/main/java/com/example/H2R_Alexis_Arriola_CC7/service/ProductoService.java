package com.example.H2R_Alexis_Arriola_CC7.service;

import com.example.H2R_Alexis_Arriola_CC7.exception.CustomException;
import com.example.H2R_Alexis_Arriola_CC7.model.Estado;
import com.example.H2R_Alexis_Arriola_CC7.model.Pedido;
import com.example.H2R_Alexis_Arriola_CC7.model.Producto;
import com.example.H2R_Alexis_Arriola_CC7.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Producto desactivarProducto(Long productoId){

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException(""));

        Pedido pedido = new Pedido();

        if(pedido.getEstado() == Estado.PENDIENTE){
            throw new CustomException("No puedes cancelar este pedido");
        }

        producto.setActivo(false);
        return productoRepository.save(producto);

    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public List<Producto> listarActivos(boolean activos){
        return productoRepository.findAllByActivoTrue(activos);
    }
}
