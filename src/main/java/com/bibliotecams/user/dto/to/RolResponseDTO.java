package com.bibliotecams.user.dto.to;

public class RolResponseDTO {

    private Integer idRol;
    private String nombre;

    public RolResponseDTO() {
    }

    public RolResponseDTO(Integer idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}