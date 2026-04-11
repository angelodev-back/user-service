package com.bibliotecams.user.service;

import java.time.LocalDate;
import java.util.List;

import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;

public interface IUsuarioBibliotecarioService {

	List<UsuarioAdminResponseDTO> listarLectores();
    UsuarioAdminResponseDTO buscarLectorPorId(Integer id);
    List<UsuarioAdminResponseDTO> buscarLectoresPorNombreOApellido(String termino);
    List<UsuarioAdminResponseDTO> buscarLectoresPorRangoFechas(LocalDate inicio, LocalDate fin);
}
