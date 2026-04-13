package com.bibliotecams.user.dto.request;

import com.bibliotecams.user.constants.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRegistroPublicoRequestDTO {

	@NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = AppConstants.REGEX_DNI, message = AppConstants.MSG_DNI)
    private String dni;

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

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = AppConstants.MIN_PASSWORD, max = AppConstants.MAX_PASSWORD, 
          message = AppConstants.MSG_PASSWORD)
    private String password;

    public UsuarioRegistroPublicoRequestDTO() {}


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}