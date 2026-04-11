package com.bibliotecams.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.bibliotecams.user.dto.request.UsuarioAdminRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioRegistroPublicoRequestDTO;
import com.bibliotecams.user.dto.request.UsuarioUpdateRequestDTO;
import com.bibliotecams.user.dto.response.UsuarioAdminResponseDTO;
import com.bibliotecams.user.dto.response.UsuarioBasicoResponseDTO;
import com.bibliotecams.user.dto.response.UsuarioRegistroResponseDTO;
import com.bibliotecams.user.entity.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegistroMapper {
    
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Usuario publicRequestToEntity(UsuarioRegistroPublicoRequestDTO dto);
    
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Usuario adminRequestToEntity(UsuarioAdminRequestDTO dto);
    
    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioRegistroResponseDTO toPublicResponse(Usuario usuario);
    
    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioAdminResponseDTO toAdminResponse(Usuario usuario);
    
    List<UsuarioAdminResponseDTO> toAdminResponseList(List<Usuario> usuarios);
    
    @Mapping(source = "idUsuario", target = "idUsuario")
    @Mapping(source = "dni", target = "dni")
    @Mapping(target = "nombreCompleto", source = "usuario", qualifiedByName = "mapNombreCompleto")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "estado", target = "estado")
    UsuarioBasicoResponseDTO toBasicoResponse(Usuario usuario);
    
    @Named("mapNombreCompleto")
    default String mapNombreCompleto(Usuario usuario) {
        return usuario.getNombre() + " " + usuario.getApellido();
    }
    
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "dni", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntityFromDto(UsuarioUpdateRequestDTO dto, @MappingTarget Usuario usuario);
}