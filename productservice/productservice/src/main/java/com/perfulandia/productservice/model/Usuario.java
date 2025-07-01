package com.perfulandia.productservice.model;

import lombok.*;

/**
 * Modelo de datos que representa un usuario del sistema
 * 
 * Esta clase contiene la información básica de un usuario que
 * se obtiene desde el microservicio de usuarios. Se utiliza
 * para mostrar información del cliente en el contexto de productos.
 * 
 * Campos principales:
 * - id: Identificador único del usuario
 * - nombre: Nombre completo del usuario
 * - email: Dirección de email del usuario
 * 
 * Nota: Esta clase no es una entidad JPA ya que los datos
 * se obtienen desde el microservicio de usuarios.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido para deserialización JSON)
@Builder // Implementa el patrón Builder para crear instancias de forma fluida
public class Usuario {
    
    /**
     * Identificador único del usuario
     * 
     * Este campo es la clave primaria que identifica
     * de manera única a cada usuario en el sistema.
     * 
     * Se utiliza para referenciar al usuario en otros
     * microservicios y en las bases de datos.
     */
    private Long id;
    
    /**
     * Nombre completo del usuario
     * 
     * Este campo contiene el nombre completo del usuario
     * y se utiliza para mostrar información personalizada
     * en las interfaces de usuario.
     * 
     * Ejemplos: "Juan Pérez", "María García López"
     */
    private String nombre;
    
    /**
     * Dirección de email del usuario
     * 
     * Este campo contiene la dirección de email donde se
     * pueden enviar notificaciones y confirmaciones.
     * 
     * Debe ser una dirección de email válida en formato
     * estándar (ejemplo@dominio.com).
     */
    private String email;
    
    /**
     * Verifica si el usuario tiene datos válidos
     * 
     * Este método valida que el usuario tenga la información
     * mínima necesaria para ser utilizado en el sistema.
     * 
     * @return true si el usuario tiene datos válidos, false en caso contrario
     */
    public boolean tieneDatosValidos() {
        return id != null &&
               nombre != null && !nombre.trim().isEmpty() &&
               email != null && !email.trim().isEmpty();
    }
    
    /**
     * Verifica si el campo email tiene un formato válido
     * 
     * Este método realiza una validación básica del formato de email
     * verificando que contenga el símbolo '@' y un dominio.
     * 
     * @return true si el formato de email es válido, false en caso contrario
     */
    public boolean tieneFormatoEmailValido() {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Validación básica de formato de email
        String emailValidar = email.trim();
        return emailValidar.contains("@") && 
               emailValidar.indexOf("@") > 0 && 
               emailValidar.indexOf("@") < emailValidar.length() - 1 &&
               emailValidar.contains(".");
    }
    
    /**
     * Obtiene el nombre formateado para saludos
     * 
     * Este método extrae el primer nombre del usuario
     * para usarlo en saludos personalizados.
     * 
     * @return El primer nombre del usuario o el nombre completo si no se puede extraer
     */
    public String getPrimerNombre() {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Usuario";
        }
        
        String nombreCompleto = nombre.trim();
        int espacioIndex = nombreCompleto.indexOf(" ");
        
        if (espacioIndex > 0) {
            return nombreCompleto.substring(0, espacioIndex);
        }
        
        return nombreCompleto;
    }
}
