package com.bibliotecams.user.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecams.user.constants.AppConstants;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.exception.UsuarioNotFoundException;
import com.bibliotecams.user.mapper.RegistroMapper;
import com.bibliotecams.user.repository.UsuarioBibliotecarioRepository;
import com.bibliotecams.user.service.IUsuarioBibliotecarioService;

@Service
public class UsuarioBibliotecarioServiceImpl implements IUsuarioBibliotecarioService {

    private final UsuarioBibliotecarioRepository bibliotecarioRepository;
    private final RegistroMapper registroMapper;

    public UsuarioBibliotecarioServiceImpl(UsuarioBibliotecarioRepository bibliotecarioRepository,
                                           RegistroMapper registroMapper) {
        this.bibliotecarioRepository = bibliotecarioRepository;
        this.registroMapper = registroMapper;
    }

    @Override
    public List<UsuarioAdminResponseDTO> listarLectores() {
        List<Usuario> lectores = bibliotecarioRepository.findAllLectores();
        return registroMapper.toAdminResponseList(lectores);
    }

    @Override
    public UsuarioAdminResponseDTO buscarLectorPorId(Integer id) {
        Usuario lector = bibliotecarioRepository.findLectorById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Lector no encontrado con ID: " + id));
        return registroMapper.toAdminResponse(lector);
    }

    @Override
    public List<UsuarioAdminResponseDTO> buscarLectoresPorNombreOApellido(String termino) {
        List<Usuario> lectores = bibliotecarioRepository.searchLectores(termino);
        return registroMapper.toAdminResponseList(lectores);
    }

    @Override
    public List<UsuarioAdminResponseDTO> buscarLectoresPorRangoFechas(LocalDate inicio, LocalDate fin) {
        List<Usuario> lectores = bibliotecarioRepository.findLectoresByFechaRegistroBetween(inicio, fin);
        return registroMapper.toAdminResponseList(lectores);
    }
}