package com.bibliotecams.user.dto.to;

import com.bibliotecams.user.constants.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioEstadoDTO {

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACTIVO|INACTIVO|SUSPENDIDO)$", 
             message = "Estado inválido. Valores permitidos: ACTIVO, INACTIVO, SUSPENDIDO")
    private String estado;

    // Constructores
    public UsuarioEstadoDTO() {
    }

    public UsuarioEstadoDTO(String estado) {
        this.estado = estado;
    }

    // Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}