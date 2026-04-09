package com.bibliotecams.user.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bibliotecams.user.dto.to.UsuarioRequestDTO;
import com.bibliotecams.user.dto.to.UsuarioResponseDTO;
import com.bibliotecams.user.dto.to.UsuarioUpdateDTO;
import com.bibliotecams.user.entity.Rol;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.exception.ResourceNotFoundException;
import com.bibliotecams.user.mapper.UsuarioMapper;
import com.bibliotecams.user.repository.RolRepository;
import com.bibliotecams.user.repository.UsuarioRepository;
import com.bibliotecams.user.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              RolRepository rolRepository,
                              BCryptPasswordEncoder passwordEncoder,
                              UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO registrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByDni(dto.getDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rol);
        usuario.setEstado("ACTIVO");
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDate.now());

        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO buscarXID(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizar(Integer id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());

        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO cambiarEstado(Integer id, String estado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setEstado(estado);
        usuario.setActivo(estado.equals("ACTIVO"));

        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }
}