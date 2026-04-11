package com.bibliotecams.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bibliotecams.user.dto.request.LoginRequestDTO;
import com.bibliotecams.user.dto.response.LoginResponseDTO;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.repository.UsuarioBaseRepository;
import com.bibliotecams.user.security.JwtService;
import com.bibliotecams.user.service.IAuthService;


@Service
public class AuthServiceImpl implements IAuthService {

    private final UsuarioBaseRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UsuarioBaseRepository usuarioRepository,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        String token = jwtService.generateToken(usuario.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol().getNombre().toString());

        return response;
    }

    @Override
    public void logout(String token) {
        jwtService.invalidateToken(token);
    }
}