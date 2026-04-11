package com.bibliotecams.user.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecams.user.dto.request.UsuarioEstadoRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRolRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioUpdateRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.entity.Rol;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.exception.DuplicateResourceException;
import com.bibliotecams.user.exception.RolNotFoundException;
import com.bibliotecams.user.exception.UsuarioNotFoundException;
import com.bibliotecams.user.mapper.RegistroMapper;
import com.bibliotecams.user.repository.RolRepository;
import com.bibliotecams.user.repository.UsuarioAdminRepository;
import com.bibliotecams.user.repository.UsuarioBaseRepository;
import com.bibliotecams.user.service.IUsuarioAdminService;

@Service
public class UsuarioAdminServiceImpl implements IUsuarioAdminService {

    private final UsuarioAdminRepository adminRepository;
    private final UsuarioBaseRepository baseRepository;
    private final RolRepository rolRepository;
    private final RegistroMapper registroMapper;

    public UsuarioAdminServiceImpl(UsuarioAdminRepository adminRepository,
                                   UsuarioBaseRepository baseRepository,
                                   RolRepository rolRepository,
                                   RegistroMapper registroMapper) {
        this.adminRepository = adminRepository;
        this.baseRepository = baseRepository;
        this.rolRepository = rolRepository;
        this.registroMapper = registroMapper;
    }

    @Override
    public List<UsuarioAdminResponseDTO> listarTodos() {
        List<Usuario> usuarios = adminRepository.findAllUsuarios();
        return registroMapper.toAdminResponseList(usuarios);
    }

    @Override
    public UsuarioAdminResponseDTO buscarPorId(Integer id) {
        Usuario usuario = adminRepository.findByIdWithRol(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        return registroMapper.toAdminResponse(usuario);
    }

    @Override
    public List<UsuarioAdminResponseDTO> buscarPorNombreOApellido(String termino) {
        List<Usuario> usuarios = adminRepository.searchByTerm(termino);
        return registroMapper.toAdminResponseList(usuarios);
    }

    @Override
    public List<UsuarioAdminResponseDTO> buscarPorRol(String rol) {
        List<Usuario> usuarios = adminRepository.findByRol(rol);
        return registroMapper.toAdminResponseList(usuarios);
    }

    @Override
    public List<UsuarioAdminResponseDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        List<Usuario> usuarios = adminRepository.findByFechaRegistroBetween(inicio, fin);
        return registroMapper.toAdminResponseList(usuarios);
    }

    @Override
    @Transactional
    public UsuarioAdminResponseDTO actualizarUsuario(Integer id, UsuarioUpdateRequestDTO dto) {
        Usuario usuario = baseRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        
        if (baseRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new DuplicateResourceException("El email ya está registrado por otro usuario");
        }
        
        registroMapper.updateEntityFromDto(dto, usuario);
        usuario.setModifiedBy("ADMIN");
        usuario.setLastModifiedDate(LocalDateTime.now());
        
        Usuario updated = baseRepository.save(usuario);
        return registroMapper.toAdminResponse(updated);
    }

    @Override
    @Transactional
    public void cambiarEstado(Integer id, UsuarioEstadoRequestDTO dto) {
        Usuario usuario = baseRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        
        usuario.setEstado(dto.getEstado());
        usuario.setActivo("ACTIVO".equals(dto.getEstado()));
        usuario.setModifiedBy("ADMIN");
        usuario.setLastModifiedDate(LocalDateTime.now());
        
        baseRepository.save(usuario);
    }

    @Override
    @Transactional
    public void cambiarRol(Integer id, UsuarioRolRequestDTO dto) {
        Usuario usuario = baseRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        
        Rol nuevoRol = rolRepository.findById(dto.getIdRol())
            .orElseThrow(() -> new RolNotFoundException("Rol no encontrado"));
        
        usuario.setRol(nuevoRol);
        usuario.setModifiedBy("ADMIN");
        usuario.setLastModifiedDate(LocalDateTime.now());
        
        baseRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        Usuario usuario = baseRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + id));
        
        usuario.setActivo(false);
        usuario.setEstado("INACTIVO");
        usuario.setModifiedBy("ADMIN");
        usuario.setLastModifiedDate(LocalDateTime.now());
        
        baseRepository.save(usuario);
    }
}