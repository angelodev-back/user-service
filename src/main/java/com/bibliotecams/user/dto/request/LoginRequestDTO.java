package com.bibliotecams.user.dto.request;

import com.bibliotecams.user.constants.AppConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

	@NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Pattern(regexp = AppConstants.REGEX_EMAIL, message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
