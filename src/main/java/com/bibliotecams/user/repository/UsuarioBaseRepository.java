package com.bibliotecams.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bibliotecams.user.entity.Usuario;

@Repository
public interface UsuarioBaseRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByDni(String dni);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = :email AND u.idUsuario != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Integer id);
    
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.dni = :dni AND u.idUsuario != :id")
    boolean existsByDniAndIdNot(@Param("dni") String dni, @Param("id") Integer id);
}
