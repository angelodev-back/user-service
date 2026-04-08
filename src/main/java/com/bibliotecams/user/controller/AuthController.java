package com.bibliotecams.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecams.user.dto.to.LoginRequestDTO;
import com.bibliotecams.user.dto.to.LoginResponseDTO;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.repository.UsuarioRepository;
import com.bibliotecams.user.security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	
	private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public AuthController(UsuarioRepository usuarioRepository,
    		BCryptPasswordEncoder passwordEncoder, 
    		JwtUtil jwtUtil) {
    	
    	this.usuarioRepository = usuarioRepository;
    	this.passwordEncoder = passwordEncoder;
    	this.jwtUtil = jwtUtil;
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request){
    	Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
    			.orElse(null);
    	
    	if(usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
    		return ResponseEntity.status(401).body("Credenciales incorrectas");
    	}
    	
    	if(!usuario.getActivo()) {
    		return ResponseEntity.status(403).body("Usuario inactivo");
    	}
    	
    	String token = jwtUtil.generarToken(
    			usuario.getEmail(), 
    			usuario.getRol().getNombre().name()
    		);
    	
    	return ResponseEntity.ok(new LoginResponseDTO(
    			token,
    			usuario.getEmail(),
    			usuario.getRol().getNombre().name()
    		));
    	
    	
    	
    }
	
}
