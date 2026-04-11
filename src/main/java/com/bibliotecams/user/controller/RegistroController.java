package com.bibliotecams.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecams.user.dto.request.UsuarioAdminRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRegistroPublicoRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.dto.response.UsuarioRegistroResponseDTO;
import com.bibliotecams.user.service.IRegistroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistroController {

    private final IRegistroService registroService;

    public RegistroController(IRegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping("/public/registro")
    public ResponseEntity<UsuarioRegistroResponseDTO> registroPublico(
            @Valid @RequestBody UsuarioRegistroPublicoRequestDTO request) {
        UsuarioRegistroResponseDTO response = registroService.registroPublico(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/admin/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioAdminResponseDTO> registroAdmin(
            @Valid @RequestBody UsuarioAdminRequestDTO request) {
        UsuarioAdminResponseDTO response = registroService.registroForAdmins(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}