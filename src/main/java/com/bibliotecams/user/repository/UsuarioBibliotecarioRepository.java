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
public interface UsuarioBibliotecarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol 
            WHERE r.nombre = 'LECTOR'
            ORDER BY u.fecha_registro DESC
            """, nativeQuery = true)
    List<Usuario> findAllLectores();
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol 
            WHERE u.id_usuario = :id AND r.nombre = 'LECTOR'
            """, nativeQuery = true)
    Optional<Usuario> findLectorById(@Param("id") Integer id);
    
    @Query(value = """
            SELECT u.* FROM usuario u 
            INNER JOIN rol r ON u.id_rol = r.id_rol 
            WHERE r.nombre = 'LECTOR' 
              AND (LOWER(u.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) 
               OR LOWER(u.apellido) LIKE LOWER(CONCAT('%', :termino, '%')))
            """, nativeQuery = true)
    List<Usuario> searchLectores(@Param("termino") String termino);
    
    @Query(value = """
            SELECT u.* FROM usuario u
            INNER JOIN rol r ON u.id_rol = r.id_rol
            WHERE r.nombre = 'LECTOR'
              AND u.fecha_registro BETWEEN :fechaInicio AND :fechaFin
            ORDER BY u.fecha_registro DESC
            """, nativeQuery = true)
    List<Usuario> findLectoresByFechaRegistroBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}
