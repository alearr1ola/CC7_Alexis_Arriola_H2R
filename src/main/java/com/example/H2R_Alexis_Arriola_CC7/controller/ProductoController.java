package com.example.H2R_Alexis_Arriola_CC7.controller;

import com.example.H2R_Alexis_Arriola_CC7.controller.dto.RequestProductoDTO;
import com.example.H2R_Alexis_Arriola_CC7.model.Producto;
import com.example.H2R_Alexis_Arriola_CC7.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlador")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/cancel/{productoId}")
    public ResponseEntity<Producto> desactivarProducto(@PathVariable Long productoId){
        return ResponseEntity
                .status(200)
                .body(productoService.desactivarProducto(productoId));
    }
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodo(){
        return ResponseEntity
                .status(200)
                .body(productoService.listarTodos());
    }

    @GetMapping("/listarActivos/{activos}")
    public ResponseEntity<List<Producto>> listarActivos(@PathVariable boolean activos){
        return ResponseEntity
                .status(200)
                .body(productoService.listarActivos(activos));
    }

}
