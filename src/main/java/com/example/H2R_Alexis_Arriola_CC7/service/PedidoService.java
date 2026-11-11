package com.example.H2R_Alexis_Arriola_CC7.service;

import com.example.H2R_Alexis_Arriola_CC7.controller.dto.RequestPedidoDTO;
import com.example.H2R_Alexis_Arriola_CC7.exception.CustomException;
import com.example.H2R_Alexis_Arriola_CC7.model.Estado;
import com.example.H2R_Alexis_Arriola_CC7.model.Pedido;
import com.example.H2R_Alexis_Arriola_CC7.model.Producto;
import com.example.H2R_Alexis_Arriola_CC7.repository.PedidoRepository;
import com.example.H2R_Alexis_Arriola_CC7.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Pedido registrarPedido(RequestPedidoDTO payload){

        Producto producto = productoRepository.findById(payload.getProductoId())
                .orElseThrow(() -> new CustomException("No existe el producto"));

        if(payload.getCantidad() < 0){
            throw new CustomException("Debe ser mayor a 0");
        }


        Pedido newPedido = new Pedido();
        newPedido.setEstado(Estado.PENDIENTE);
        producto.setStock(-1);
        newPedido.setCantidad(payload.getCantidad());
        newPedido.setNombreCliente(payload.getNombreCliente());


        return pedidoRepository.save(newPedido);
    }

    @Transactional
    public Pedido cancelarPedido(Long pedidoId){

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new CustomException( "El pedido no existe"));

        if(pedido.getEstado() != Estado.PENDIENTE){
            throw new CustomException("Solo puedes cancelar pedidos pendientes");
        }

        pedido.setEstado(Estado.CANCELADO);

        Producto producto = pedido.getProducto();
        producto.setStock(+1);
        productoRepository.save(producto);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido procesarPedido(Long pedidoId){

       Pedido pedido = pedidoRepository.findById(pedidoId)
               .orElseThrow(() -> new CustomException("No existe el pedido"));

       if(pedido.getEstado() != Estado.PENDIENTE){
           throw new CustomException("Solo se pueden procesar solicitudes pendientes");
       }

       pedido.setEstado(Estado.PROCESADO);

       return pedidoRepository.save(pedido);
    }

    @Transactional
    public String cancelarTodosLosPedidos(Long productoId){

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new CustomException("No existe el producto"));

        for(Pedido pedido: pedidoRepository.findAll()){
            if(pedido.getEstado() == Estado.PENDIENTE){
                pedido.setEstado(Estado.CANCELADO);
                producto.setStock(+1);
                productoRepository.save(producto);
                pedidoRepository.save(pedido);
            }
        }
        return "Cancelados con exito";
    }

    public Estado consultaPorEstado(Estado estado){
        if(estado != Estado.PENDIENTE && estado != Estado.CANCELADO && estado != Estado.PROCESADO){
            throw new CustomException("El estado es invalido");
        }
        return pedidoRepository.findByEstado(estado);
    }
}