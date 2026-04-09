package com.bibliotecams.user.constants;

public final class AppConstants {
	
	private AppConstants() {
        throw new IllegalStateException("Clase de constantes - No instanciar");
    }
	
	
	/** DNI peruano: exactamente 8 dígitos numéricos */
    public static final String REGEX_DNI = "^[0-9]{8}$";
    
    /** Solo letras (incluye tildes y ñ) y espacios */
    public static final String REGEX_SOLO_LETRAS = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";
    
    /** Formato de email válido */
    public static final String REGEX_EMAIL = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$";
    
    /** Password: mínimo 6 caracteres */
    public static final String REGEX_PASSWORD = "^.{6,}$";

    // ============================================================
    // ESTADOS DE USUARIO
    // ============================================================
    
    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_INACTIVO = "INACTIVO";
    public static final String ESTADO_SUSPENDIDO = "SUSPENDIDO";

    // ============================================================
    // ROLES DEL SISTEMA
    // ============================================================
    
    public static final String ROL_ADMIN = "ADMIN";
    public static final String ROL_BIBLIOTECARIO = "BIBLIOTECARIO";
    public static final String ROL_LECTOR = "LECTOR";

    // ============================================================
    // MENSAJES DE ERROR
    // ============================================================
    
    public static final String MSG_USUARIO_NO_ENCONTRADO = "Usuario no encontrado con el ID proporcionado";
    public static final String MSG_USUARIO_NO_ENCONTRADO_DNI = "Usuario no encontrado con el DNI proporcionado";
    public static final String MSG_USUARIO_NO_ENCONTRADO_EMAIL = "Usuario no encontrado con el email proporcionado";
    public static final String MSG_ROL_NO_ENCONTRADO = "Rol no encontrado";
    
    public static final String MSG_DNI_DUPLICADO = "El DNI ingresado ya está registrado en el sistema";
    public static final String MSG_EMAIL_DUPLICADO = "El email ingresado ya está registrado en el sistema";
    
    public static final String MSG_CREDENCIALES_INCORRECTAS = "Credenciales incorrectas";
    public static final String MSG_USUARIO_INACTIVO = "El usuario se encuentra inactivo. Contacte al administrador";
    public static final String MSG_USUARIO_SUSPENDIDO = "El usuario se encuentra suspendido. Contacte al administrador";
    
    public static final String MSG_PASSWORD_ACTUAL_INCORRECTA = "La contraseña actual es incorrecta";
    public static final String MSG_PASSWORD_IGUAL = "La nueva contraseña no puede ser igual a la actual";
    
    public static final String MSG_ESTADO_INVALIDO = "Estado inválido. Valores permitidos: ACTIVO, INACTIVO, SUSPENDIDO";
    public static final String MSG_USUARIO_YA_INACTIVO = "El usuario ya se encuentra inactivo";
    
    // ============================================================
    // VALORES DE NEGOCIO
    // ============================================================
    
    /** Máximo de préstamos activos permitidos por usuario */
    public static final int MAX_PRESTAMOS_ACTIVOS = 3;
    
    /** Tiempo de expiración del token JWT: 8 horas en milisegundos */
    public static final long JWT_EXPIRACION_MS = 1000L * 60 * 60 * 8;

    // ============================================================
    // MENSAJES DE ÉXITO
    // ============================================================
    
    public static final String MSG_USUARIO_CREADO = "Usuario registrado exitosamente";
    public static final String MSG_USUARIO_ACTUALIZADO = "Usuario actualizado exitosamente";
    public static final String MSG_USUARIO_ELIMINADO = "Usuario desactivado exitosamente";
    public static final String MSG_PASSWORD_ACTUALIZADA = "Contraseña actualizada exitosamente";
}