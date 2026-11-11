package com.example.H2R_Alexis_Arriola_CC7.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPedidoDTO {

    @NotNull(message = "mensaje de error")
    private Long productoId;

    @NotBlank(message = "El nombre del cliente no puede ir vacio: ")
    private String nombreCliente;

    @NotNull(message = "Debe haber una cantidad")
    private Integer cantidad;

}
