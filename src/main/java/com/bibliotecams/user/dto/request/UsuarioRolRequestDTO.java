package com.bibliotecams.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UsuarioRolRequestDTO {
    
	@NotNull(message = "El ID del rol es obligatorio")
    @Min(value = 1, message = "El ID del rol debe ser mayor a 0")
    private Integer idRol;
    
    public UsuarioRolRequestDTO() {}
    
    public UsuarioRolRequestDTO(Integer idRol) {
        this.idRol = idRol;
    }
    
    public Integer getIdRol() { 
    	return idRol; 
    }
    
    public void setIdRol(Integer idRol) { 
    	this.idRol = idRol; 
    }
}