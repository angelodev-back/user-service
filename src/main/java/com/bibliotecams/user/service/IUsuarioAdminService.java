package com.bibliotecams.user.service;

import java.time.LocalDate;
import java.util.List;

import com.bibliotecams.user.dto.request.UsuarioEstadoRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRolRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioUpdateRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;

public interface IUsuarioAdminService {

	List<UsuarioAdminResponseDTO> listarTodos();
    UsuarioAdminResponseDTO buscarPorId(Integer id);
    List<UsuarioAdminResponseDTO> buscarPorNombreOApellido(String termino);
    List<UsuarioAdminResponseDTO> buscarPorRol(String rol);
    List<UsuarioAdminResponseDTO> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
    UsuarioAdminResponseDTO actualizarUsuario(Integer id, UsuarioUpdateRequestDTO dto);
    void cambiarEstado(Integer id, UsuarioEstadoRequestDTO dto);
    void cambiarRol(Integer id, UsuarioRolRequestDTO dto);
    void eliminarUsuario(Integer id);
}
