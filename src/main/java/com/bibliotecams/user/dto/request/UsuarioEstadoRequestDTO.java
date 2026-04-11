package com.bibliotecams.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioEstadoRequestDTO {
    
	@NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "ACTIVO|INACTIVO|SUSPENDIDO", message = "Estado debe ser ACTIVO, INACTIVO o SUSPENDIDO")
    private String estado;
    
    public UsuarioEstadoRequestDTO() {}
    
    public UsuarioEstadoRequestDTO(String estado) {
        this.estado = estado;
    }
    
    // Getter y Setter
    public String getEstado() { 
    	return estado; 
    }
    
    public void setEstado(String estado) { 
    	this.estado = estado; 
    }
}