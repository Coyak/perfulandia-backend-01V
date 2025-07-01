package com.perfulandia.usuarioservice.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un usuario en el sistema
 * 
 * Esta clase mapea la tabla 'usuarios' en la base de datos y representa
 * un usuario registrado en el sistema. Cada usuario tiene información
 * básica como nombre, correo electrónico y rol en el sistema.
 * 
 * Campos obligatorios:
 * - nombre: Nombre completo del usuario
 * - correo: Dirección de correo electrónico del usuario
 * - rol: Rol del usuario en el sistema (ADMIN, GERENTE, USUARIO)
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Entity // Marca esta clase como una entidad JPA que se mapea a una tabla
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido por JPA)
@Builder // Implementa el patrón Builder para crear instancias de forma flexible
public class Usuario {
    
    /**
     * Identificador único del usuario
     * Se genera automáticamente usando estrategia de auto-incremento
     */
    @Id // Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación automática del ID
    private long id;
    
    /**
     * Nombre completo del usuario
     * 
     * Este campo contiene el nombre completo del usuario
     * y se utiliza para identificar al usuario en el sistema.
     * 
     * Es un campo obligatorio que no puede estar vacío.
     */
    private String nombre;
    
    /**
     * Dirección de correo electrónico del usuario
     * 
     * Este campo contiene la dirección de email donde se
     * pueden enviar notificaciones y confirmaciones.
     * 
     * Debe ser una dirección de email válida en formato
     * estándar (ejemplo@dominio.com).
     */
    private String correo;
    
    /**
     * Rol del usuario en el sistema
     * 
     * Este campo define los permisos y responsabilidades
     * del usuario dentro del sistema.
     * 
     * Valores posibles: "ADMIN", "GERENTE", "USUARIO"
     */
    private String rol; // ADMIN, GERENTE, USUARIO
}
