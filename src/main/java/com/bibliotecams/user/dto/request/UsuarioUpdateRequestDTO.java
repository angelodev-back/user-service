package com.bibliotecams.user.dto.request;

import com.bibliotecams.user.constants.AppConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateRequestDTO {
    
	@NotBlank(message = "El nombre es obligatorio")
    @Size(min = AppConstants.MIN_NOMBRE, max = AppConstants.MAX_NOMBRE, 
          message = "El nombre debe tener entre " + AppConstants.MIN_NOMBRE + " y " + AppConstants.MAX_NOMBRE + " caracteres")
    @Pattern(regexp = AppConstants.REGEX_SOLO_LETRAS, message = AppConstants.MSG_NOMBRE)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = AppConstants.MIN_NOMBRE, max = AppConstants.MAX_NOMBRE, 
          message = "El apellido debe tener entre " + AppConstants.MIN_NOMBRE + " y " + AppConstants.MAX_NOMBRE + " caracteres")
    @Pattern(regexp = AppConstants.REGEX_SOLO_LETRAS, message = AppConstants.MSG_NOMBRE)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = AppConstants.MSG_EMAIL)
    @Pattern(regexp = AppConstants.REGEX_EMAIL, message = AppConstants.MSG_EMAIL)
    private String email;
    
    public UsuarioUpdateRequestDTO() {}

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
}