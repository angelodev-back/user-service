package com.bibliotecams.user.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecams.user.dto.request.UsuarioAdminRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRegistroPublicoRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.dto.response.UsuarioRegistroResponseDTO;
import com.bibliotecams.user.entity.Rol;
import com.bibliotecams.user.entity.RolNombre;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.exception.DuplicateResourceException;
import com.bibliotecams.user.exception.RolNotFoundException;
import com.bibliotecams.user.mapper.RegistroMapper;
import com.bibliotecams.user.repository.RolRepository;
import com.bibliotecams.user.repository.UsuarioBaseRepository;
import com.bibliotecams.user.service.IRegistroService;

@Service
public class RegistroServiceImpl implements IRegistroService {

    private final UsuarioBaseRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final RegistroMapper registroMapper;
    private final PasswordEncoder passwordEncoder;

    public RegistroServiceImpl(UsuarioBaseRepository usuarioRepository,
                               RolRepository rolRepository,
                               RegistroMapper registroMapper,
                               PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.registroMapper = registroMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UsuarioRegistroResponseDTO registroPublico(UsuarioRegistroPublicoRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
        
        if (usuarioRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("El DNI ya está registrado");
        }
        
        Rol rolLector = rolRepository.findByNombre(RolNombre.LECTOR)
            .orElseThrow(() -> new RolNotFoundException("Rol LECTOR no encontrado"));
        
        Usuario usuario = registroMapper.publicRequestToEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rolLector);
        usuario.setEstado("ACTIVO");
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setCreatedBy(dto.getEmail());
        usuario.setCreatedDate(LocalDateTime.now());
        
        Usuario saved = usuarioRepository.save(usuario);
        
        return registroMapper.toPublicResponse(saved);
    }

    @Override
    @Transactional
    public UsuarioAdminResponseDTO registroForAdmins(UsuarioAdminRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
        
        if (usuarioRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("El DNI ya está registrado");
        }
        
        Rol rol = rolRepository.findById(dto.getIdRol())
            .orElseThrow(() -> new RolNotFoundException("Rol no encontrado"));
        
        Usuario usuario = registroMapper.adminRequestToEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rol);
        usuario.setEstado("ACTIVO");
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setCreatedBy("ADMIN");
        usuario.setCreatedDate(LocalDateTime.now());
        
        Usuario saved = usuarioRepository.save(usuario);
        
        return registroMapper.toAdminResponse(saved);
    }
}