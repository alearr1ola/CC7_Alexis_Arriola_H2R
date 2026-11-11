package com.example.H2R_Alexis_Arriola_CC7.controller;

import com.example.H2R_Alexis_Arriola_CC7.controller.dto.RequestPedidoDTO;
import com.example.H2R_Alexis_Arriola_CC7.model.Estado;
import com.example.H2R_Alexis_Arriola_CC7.model.Pedido;
import com.example.H2R_Alexis_Arriola_CC7.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController {

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@Valid @RequestBody RequestPedidoDTO payload){
        return ResponseEntity
                .status(201)
                .body(pedidoService.registrarPedido(payload));
    }

    @PostMapping("/cancelar/{pedidoId}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long pedidoId){
        return ResponseEntity
                .status(200)
                .body(pedidoService.cancelarPedido(pedidoId));
    }

    @GetMapping("/consulta/{estado}")
    public ResponseEntity<Estado> consultaPorEstado(@PathVariable Estado estado){
        return ResponseEntity
                .status(200)
                .body(pedidoService.consultaPorEstado(estado));
    }

    @PostMapping("/cancelar-todos/{productoId}")
    public ResponseEntity<String> cancelarTodosLosPedidos(@PathVariable Long productoId){
        return ResponseEntity
                .status(200)
                .body(pedidoService.cancelarTodosLosPedidos(productoId));
    }
}