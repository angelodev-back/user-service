package com.bibliotecams.user.dto.request;

import com.bibliotecams.user.constants.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRegistroPublicoRequestDTO {

	@NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = AppConstants.REGEX_DNI, message = "El DNI debe contener exactamente 8 dígitos numéricos")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = AppConstants.REGEX_SOLO_LETRAS, message = "El nombre solo debe contener letras y espacios")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = AppConstants.REGEX_SOLO_LETRAS, message = "El apellido solo debe contener letras y espacios")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Pattern(regexp = AppConstants.REGEX_EMAIL, message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
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