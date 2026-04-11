package com.bibliotecams.user.service;

import com.bibliotecams.user.dto.request.LoginRequestDTO;
import com.bibliotecams.user.dto.response.LoginResponseDTO;

public interface IAuthService {

	LoginResponseDTO login(LoginRequestDTO loginRequest);
    void logout(String token);
    
}
