package com.perfulandia.emailservice.model;

import lombok.*;

/**
 * Modelo de datos para las solicitudes de envío de emails
 * 
 * Esta clase representa la estructura de datos que se recibe cuando
 * se solicita el envío de un email. Contiene toda la información
 * necesaria para enviar un correo electrónico.
 * 

 * 
 * Campos obligatorios:
 * - para: Dirección de correo del destinatario
 * - asunto: Título del email
 * - mensaje: Contenido del email
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido para deserialización JSON)
@Builder // Implementa el patrón Builder para crear instancias de forma fluida
public class EmailRequest {
    
    /**
     * Dirección de correo electrónico del destinatario
     * 
     * Este campo es obligatorio y debe ser una dirección de email válida.
     * Ejemplos: "usuario@ejemplo.com", "cliente@perfulandia.com"
     * 
     * Validaciones aplicadas:
     * - No puede ser null
     * - Debe tener formato de email válido
     */
    private String para;
    
    /**
     * Asunto o título del email
     * 
     * Este campo es obligatorio y debe contener un texto descriptivo
     * que indique el propósito del email.
     * 
     * Ejemplos:
     * - "Confirmación de compra"
     * - "Su pedido ha sido enviado"
     * - "Bienvenido a Perfulandia"
     * 
     * Validaciones aplicadas:
     * - No puede ser null
     * - No puede estar vacío
     */
    private String asunto;
    
    /**
     * Contenido o cuerpo del email
     * 
     * Este campo es obligatorio y contiene el mensaje que se enviará
     * al destinatario. Puede incluir texto plano o HTML.
     * 
     * Ejemplos:
     * - "Gracias por su compra. Su pedido #12345 ha sido confirmado."
     * - "Su pedido ha sido enviado y llegará en 3-5 días hábiles."
     * 
     * Validaciones aplicadas:
     * - No puede ser null
     * - No puede estar vacío
     */
    private String mensaje;
    
    /**
     * Método que verifica si todos los campos obligatorios están presentes
     * 
     * Este método es útil para validar que la solicitud de email
     * contenga toda la información necesaria antes de procesarla.
     * 
     * @return true si todos los campos obligatorios están presentes, false en caso contrario
     */
    public boolean tieneCamposObligatorios() {
        return para != null && !para.trim().isEmpty() &&
               asunto != null && !asunto.trim().isEmpty() &&
               mensaje != null && !mensaje.trim().isEmpty();
    }
    
    /**
     * Método que verifica si la dirección de email tiene formato válido
     * 
     * Este método realiza una validación básica del formato de email
     * verificando que contenga el símbolo @ y al menos un punto después.
     * 
     * @return true si el formato parece válido, false en caso contrario
     */
    public boolean tieneFormatoEmailValido() {
        if (para == null || para.trim().isEmpty()) {
            return false;
        }
        
        String email = para.trim();
        return email.contains("@") && email.contains(".") && 
               email.indexOf("@") < email.lastIndexOf(".");
    }
}
