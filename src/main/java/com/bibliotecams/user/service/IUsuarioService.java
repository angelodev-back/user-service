package com.bibliotecams.user.service;

import com.bibliotecams.user.dto.to.UsuarioRequestDTO;
import com.bibliotecams.user.dto.to.UsuarioResponseDTO;
import com.bibliotecams.user.dto.to.UsuarioUpdateDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioResponseDTO registrar(UsuarioRequestDTO dto);
    
    List<UsuarioResponseDTO> listarTodos();
    
    UsuarioResponseDTO buscarXID(Integer id);
    
    UsuarioResponseDTO actualizar(Integer id, UsuarioUpdateDTO dto);
    
    UsuarioResponseDTO cambiarEstado(Integer id, String estado);
    
}