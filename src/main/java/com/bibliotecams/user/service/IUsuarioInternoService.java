package com.bibliotecams.user.service;

import com.bibliotecams.user.dto.response.UsuarioBasicoResponseDTO;

public interface IUsuarioInternoService {

	UsuarioBasicoResponseDTO obtenerDatosBasicos(Integer id);
    boolean validarUsuarioActivo(Integer id);
    boolean existeUsuario(Integer id);
    
}
