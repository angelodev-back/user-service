package com.bibliotecams.user.entity;

import com.bibliotecams.user.auditable.Auditable;
import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol extends Auditable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre", unique = true, nullable = false, length = 30)
    private RolNombre nombre;

    public Rol() {}

    public Rol(Integer idRol, RolNombre nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public RolNombre getNombre() {
        return nombre;
    }

    public void setNombre(RolNombre nombre) {
        this.nombre = nombre;
    }
}