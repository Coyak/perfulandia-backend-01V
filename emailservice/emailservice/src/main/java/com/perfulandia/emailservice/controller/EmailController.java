package com.perfulandia.emailservice.controller;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.model.CompraRequest;
import com.perfulandia.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Controlador REST para el manejo de envío de emails
 * 
 * Esta clase expone endpoints HTTP para gestionar las operaciones
 * relacionadas con el envío de correos electrónicos. Proporciona
 * funcionalidades para enviar emails simples y emails de confirmación
 * de compra personalizados.
 * 
 * Endpoints disponibles:
 * - POST /api/email/enviar - Enviar email simple
 * - POST /api/email/compra - Enviar email de confirmación de compra
 * 
 * Funcionalidades principales:
 * - Envío de emails de notificación general
 * - Envío de emails de confirmación de compra con detalles
 * - Validación de datos de entrada
 * - Manejo de errores de envío
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@RestController // Marca esta clase como un controlador REST que devuelve respuestas en formato JSON automáticamente
@RequestMapping("/api/email") // Define la ruta base para todos los endpoints
@RequiredArgsConstructor // Genera un constructor con los campos final (inyección de dependencias)
public class EmailController {
    
    /**
     * Servicio que contiene la lógica de negocio para envío de emails
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final EmailService emailService;
    
    /**
     * Envía un email simple con los datos proporcionados
     * 
     * Este endpoint permite enviar un email básico con destinatario,
     * asunto y mensaje personalizados. Es útil para notificaciones
     * generales del sistema.
     * 
     * @param request Objeto EmailRequest con los datos del email
     * @return ResponseEntity<String> con mensaje de confirmación o error
     */
    @PostMapping("/enviar") // Mapea este método a peticiones POST en la ruta especificada
    public ResponseEntity<String> enviarEmail(@RequestBody EmailRequest request) { // Extrae el cuerpo de la petición HTTP y lo convierte a EmailRequest
        try {
            // Validar que la solicitud tenga datos válidos
            if (!request.tieneCamposObligatorios()) {
                return ResponseEntity.badRequest()
                    .body("Error: Todos los campos son obligatorios (para, asunto, mensaje)");
            }
            
            // Validar formato de email
            if (!request.tieneFormatoEmailValido()) {
                return ResponseEntity.badRequest()
                    .body("Error: Formato de email inválido");
            }
            
            // Delegar el envío al servicio
            emailService.enviarCorreo(request);
            
            return ResponseEntity.ok("Email enviado exitosamente");
            
        } catch (Exception e) {
            // Manejar errores de envío
            return ResponseEntity.internalServerError()
                .body("Error al enviar email: " + e.getMessage());
        }
    }
    
    /**
     * Envía un email de confirmación de compra personalizado
     * 
     * Este endpoint permite enviar un email de confirmación de compra
     * que incluye detalles específicos del pedido, productos comprados
     * y información del cliente.
     * 
     * @param request Objeto CompraRequest con los datos de la compra
     * @return ResponseEntity<String> con mensaje de confirmación o error
     */
    @PostMapping("/compra") // Mapea este método a peticiones POST en la ruta especificada
    public ResponseEntity<String> enviarEmailCompra(@RequestBody CompraRequest request) { // Extrae el cuerpo de la petición HTTP y lo convierte a CompraRequest
        try {
            // Validar que la solicitud tenga datos válidos
            if (!request.tieneDatosValidos()) {
                return ResponseEntity.badRequest()
                    .body("Error: Datos de compra incompletos o inválidos");
            }
            
            // Validar que el usuario tenga datos válidos
            if (!request.getUsuario().tieneDatosValidos()) {
                return ResponseEntity.badRequest()
                    .body("Error: Datos de usuario incompletos o inválidos");
            }
            
            // Validar formato de email del usuario
            if (!request.getUsuario().tieneFormatoEmailValido()) {
                return ResponseEntity.badRequest()
                    .body("Error: Formato de email del usuario inválido");
            }
            
            // Delegar el envío al servicio
            emailService.enviarEmailCompra(request);
            
            return ResponseEntity.ok("Email de confirmación de compra enviado exitosamente");
            
        } catch (Exception e) {
            // Manejar errores de envío
            return ResponseEntity.internalServerError()
                .body("Error al enviar email de compra: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint de prueba para verificar que el servicio esté funcionando
     * 
     * Este endpoint es útil para verificar que el microservicio esté
     * ejecutándose correctamente y respondiendo a peticiones HTTP.
     * 
     * @return ResponseEntity<String> con mensaje de estado del servicio
     */
    @GetMapping("/status") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Email Service está funcionando correctamente");
    }
}
