package com.bibliotecams.user.dto.response;

import java.time.LocalDate;

public class UsuarioRegistroResponseDTO {

	private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private LocalDate fechaRegistro;

    public UsuarioRegistroResponseDTO() {}

    public UsuarioRegistroResponseDTO(String dni, String nombre, String apellido, String email,
                              String rol, LocalDate fechaRegistro) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}