package com.bibliotecams.user.dto.response;

public class UsuarioBasicoResponseDTO {
	
	private Integer idUsuario;
    private String dni;
    private String nombreCompleto;
    private String email;
    private String estado;
    
    public UsuarioBasicoResponseDTO() {}
    
    public UsuarioBasicoResponseDTO(Integer idUsuario, String dni, 
                                    String nombreCompleto, String email, String estado) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.estado = estado;
    }

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}