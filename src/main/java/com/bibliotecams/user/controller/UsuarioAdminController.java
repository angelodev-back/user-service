package com.bibliotecams.user.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecams.user.dto.request.UsuarioEstadoRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRolRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioUpdateRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.service.IUsuarioAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioAdminController {

    private final IUsuarioAdminService usuarioAdminService;

    public UsuarioAdminController(IUsuarioAdminService usuarioAdminService) {
        this.usuarioAdminService = usuarioAdminService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioAdminResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioAdminService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdminResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioAdminService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioAdminResponseDTO>> buscarPorTermino(
            @RequestParam String termino) {
        return ResponseEntity.ok(usuarioAdminService.buscarPorNombreOApellido(termino));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioAdminResponseDTO>> buscarPorRol(@PathVariable String rol) {
        return ResponseEntity.ok(usuarioAdminService.buscarPorRol(rol));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<UsuarioAdminResponseDTO>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(usuarioAdminService.buscarPorRangoFechas(inicio, fin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioAdminResponseDTO> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioUpdateRequestDTO request) {
        return ResponseEntity.ok(usuarioAdminService.actualizarUsuario(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioEstadoRequestDTO request) {
        usuarioAdminService.cambiarEstado(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rol")
    public ResponseEntity<Void> cambiarRol(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRolRequestDTO request) {
        usuarioAdminService.cambiarRol(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioAdminService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}