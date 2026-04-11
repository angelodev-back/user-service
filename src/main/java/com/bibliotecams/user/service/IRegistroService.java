package com.bibliotecams.user.service;

import com.bibliotecams.user.dto.request.UsuarioAdminRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRegistroPublicoRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.dto.response.UsuarioRegistroResponseDTO;

public interface IRegistroService {

	UsuarioRegistroResponseDTO registroPublico(UsuarioRegistroPublicoRequestDTO request);
    UsuarioAdminResponseDTO registroForAdmins(UsuarioAdminRequestDTO request);
    
}
