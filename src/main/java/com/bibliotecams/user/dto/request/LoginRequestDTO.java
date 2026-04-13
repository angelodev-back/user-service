package com.bibliotecams.user.dto.request;

import com.bibliotecams.user.constants.AppConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

	@NotBlank(message = "El email es obligatorio")
    @Email(message = AppConstants.MSG_EMAIL)
    @Pattern(regexp = AppConstants.REGEX_EMAIL, message = AppConstants.MSG_EMAIL)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = AppConstants.MIN_PASSWORD, max = AppConstants.MAX_PASSWORD, 
          message = AppConstants.MSG_PASSWORD)
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
