package com.bibliotecams.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bibliotecams.user.dto.to.UsuarioRequestDTO;
import com.bibliotecams.user.dto.to.UsuarioResponseDTO;
import com.bibliotecams.user.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioResponseDTO toDTO(Usuario usuario);

    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    
    Usuario toEntity(UsuarioRequestDTO dto);
    
}