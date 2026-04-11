package com.bibliotecams.user.service.impl;

import org.springframework.stereotype.Service;

import com.bibliotecams.user.dto.response.UsuarioBasicoResponseDTO;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.exception.UsuarioNotFoundException;
import com.bibliotecams.user.mapper.RegistroMapper;
import com.bibliotecams.user.repository.UsuarioBaseRepository;
import com.bibliotecams.user.service.IUsuarioInternoService;

@Service
public class UsuarioInternoServiceImpl implements IUsuarioInternoService {

    private final UsuarioBaseRepository usuarioRepository;
    private final RegistroMapper registroMapper;

    public UsuarioInternoServiceImpl(UsuarioBaseRepository usuarioRepository,
                                     RegistroMapper registroMapper) {
        this.usuarioRepository = usuarioRepository;
        this.registroMapper = registroMapper;
    }

    @Override
    public UsuarioBasicoResponseDTO obtenerDatosBasicos(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        return registroMapper.toBasicoResponse(usuario);
    }

    @Override
    public boolean validarUsuarioActivo(Integer id) {
        return usuarioRepository.findById(id)
            .map(Usuario::getActivo)
            .orElse(false);
    }

    @Override
    public boolean existeUsuario(Integer id) {
        return usuarioRepository.existsById(id);
    }
}