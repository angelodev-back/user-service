package com.bibliotecams.user.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.service.IUsuarioBibliotecarioService;

@RestController
@RequestMapping("/api/bibliotecario/lectores")
@PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
public class UsuarioBibliotecarioController {

    private final IUsuarioBibliotecarioService bibliotecarioService;

    public UsuarioBibliotecarioController(IUsuarioBibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioAdminResponseDTO>> listarLectores() {
        return ResponseEntity.ok(bibliotecarioService.listarLectores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdminResponseDTO> buscarLectorPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(bibliotecarioService.buscarLectorPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioAdminResponseDTO>> buscarLectoresPorTermino(
            @RequestParam String termino) {
        return ResponseEntity.ok(bibliotecarioService.buscarLectoresPorNombreOApellido(termino));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<UsuarioAdminResponseDTO>> buscarLectoresPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(bibliotecarioService.buscarLectoresPorRangoFechas(inicio, fin));
    }
}