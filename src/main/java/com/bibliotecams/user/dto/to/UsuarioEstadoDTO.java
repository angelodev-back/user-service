package com.bibliotecams.user.dto.to;

import jakarta.validation.constraints.NotBlank;

public class UsuarioEstadoDTO {

    @NotBlank(message =	"El estado es obligatorio")
    private String estado;

    public UsuarioEstadoDTO() {
    }

    public UsuarioEstadoDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}