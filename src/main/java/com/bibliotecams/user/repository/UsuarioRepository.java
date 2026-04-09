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
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByDni(String dni);

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);

    List<Usuario> findByEstado(String estado);
    
    List<Usuario> findByActivoTrue();
    
    /**
     * Busca todos los usuarios que tienen un rol específico
     **/
    @Query(value ="""
    		SELECT u.* FROM usuario u 
    		INNER JOIN rol r ON u.id_rol = r.id_rol 
    		WHERE r.nombre = :rol
    		""",
    		nativeQuery = true)
    List<Usuario> findByRolNombre(@Param("rol") String rol);
    
    
    /**
     * Busca usuarios por nombre o apellido
     */
    
    @Query(value = """
            SELECT * FROM usuario 
            WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) 
               OR LOWER(apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))
            """, 
            nativeQuery = true)
    List<Usuario> searchByNameOrSurname(@Param("nombre") String nombre,
                                        @Param("apellido") String apellido);
	
    
    
    /**
     * Busca usuarios registrados en un rango de fechas
     */
    @Query(value = """
    		SELECT * FROM usuario u
    		WHERE fecha_registro BETWEEN :fechaInicio AND :fechaFin
    		""",
    		nativeQuery = true)
    List<Usuario> buscarPorRangosFechas(@Param("fechaInicio") LocalDate fechaInicio,
    									@Param("fechaFin") LocalDate fechaFin);
    
}