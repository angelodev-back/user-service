package com.bibliotecams.user.dto.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioPasswordDTO {

    @NotBlank(message = "La contraseña actual es obligatoria")
    private String passwordActual;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 6, message = "La nueva contraseña debe tener al menos 6 caracteres")
    private String passwordNuevo;

    // Constructores
    public UsuarioPasswordDTO() {
    }

    public UsuarioPasswordDTO(String passwordActual, String passwordNuevo) {
        this.passwordActual = passwordActual;
        this.passwordNuevo = passwordNuevo;
    }

    // Getters y Setters
    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }
}
