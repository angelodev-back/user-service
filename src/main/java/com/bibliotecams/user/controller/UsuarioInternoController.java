package com.bibliotecams.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecams.user.dto.response.UsuarioBasicoResponseDTO;
import com.bibliotecams.user.service.IUsuarioInternoService;

@RestController
@RequestMapping("/api/internal/usuarios")
public class UsuarioInternoController {

    private final IUsuarioInternoService usuarioInternoService;

    public UsuarioInternoController(IUsuarioInternoService usuarioInternoService) {
        this.usuarioInternoService = usuarioInternoService;
    }

    @GetMapping("/{id}/basico")
    public ResponseEntity<UsuarioBasicoResponseDTO> obtenerDatosBasicos(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioInternoService.obtenerDatosBasicos(id));
    }

    @GetMapping("/{id}/validar")
    public ResponseEntity<Boolean> validarUsuarioActivo(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioInternoService.validarUsuarioActivo(id));
    }

    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existeUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioInternoService.existeUsuario(id));
    }
}