package com.bibliotecams.user.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bibliotecams.user.entity.Usuario;

@Repository
public interface UsuarioAdminRepository extends JpaRepository<Usuario, Integer> {
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol
            ORDER BY u.fecha_registro DESC
            """, nativeQuery = true)
    List<Usuario> findAllUsuarios();
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol 
            WHERE u.id_usuario = :id
            """, nativeQuery = true)
    Optional<Usuario> findByIdWithRol(@Param("id") Integer id);
    
    @Query(value = """
            SELECT * FROM usuario 
            WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :termino, '%')) 
               OR LOWER(apellido) LIKE LOWER(CONCAT('%', :termino, '%'))
            """, nativeQuery = true)
    List<Usuario> searchByTerm(@Param("termino") String termino);
    
    @Query(value = """
            SELECT u.* FROM usuario u
            INNER JOIN rol r ON u.id_rol = r.id_rol
            WHERE u.fecha_registro BETWEEN :fechaInicio AND :fechaFin
            ORDER BY u.fecha_registro DESC
            """, nativeQuery = true)
    List<Usuario> findByFechaRegistroBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol 
            WHERE r.nombre = :rol
            """, nativeQuery = true)
    List<Usuario> findByRol(@Param("rol") String rol);
}