package com.bibliotecams.user.controller;

import com.bibliotecams.user.dto.to.UsuarioEstadoDTO;
import com.bibliotecams.user.dto.to.UsuarioRequestDTO;
import com.bibliotecams.user.dto.to.UsuarioResponseDTO;
import com.bibliotecams.user.dto.to.UsuarioUpdateDTO;
import com.bibliotecams.user.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.registrar(dto));
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarXID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Integer id,
                                                          @Valid @RequestBody UsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<UsuarioResponseDTO> cambiarEstado(@PathVariable Integer id,
                                                             @Valid @RequestBody UsuarioEstadoDTO dto) {
        return ResponseEntity.ok(usuarioService.cambiarEstado(id, dto.getEstado()));
    }
	
}
