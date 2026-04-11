package com.bibliotecams.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bibliotecams.user.entity.Rol;
import com.bibliotecams.user.entity.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(RolNombre nombre);
}