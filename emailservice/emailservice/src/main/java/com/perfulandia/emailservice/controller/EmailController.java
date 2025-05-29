package com.perfulandia.emailservice.controller;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.model.CompraRequest;
import com.perfulandia.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/notificaciones")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-por-email")
    public ResponseEntity<?> enviarPorCorreo(@RequestBody EmailRequest request) {
        if (request.getPara() == null || request.getAsunto() == null || request.getMensaje() == null) {
            return ResponseEntity.badRequest().body("Todos los campos 'para', 'asunto' y 'mensaje' son obligatorios.");
        }

        try {
            emailService.enviarCorreo(request);
            return ResponseEntity.ok("Correo enviado a: " + request.getPara());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo: " + e.getMessage());
        }
    }

    @PostMapping("/enviar-por-id")
    public ResponseEntity<?> enviarPorId(@RequestBody EmailRequest request) {
        if (request.getId() == null || request.getAsunto() == null || request.getMensaje() == null) {
            return ResponseEntity.badRequest().body("Los campos 'id', 'asunto' y 'mensaje' son obligatorios.");
        }
        try {
            emailService.enviarPorId(request.getId(), request.getAsunto(), request.getMensaje());
            return ResponseEntity.ok("Correo enviado al usuario con ID: " + request.getId());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Usuario no encontrado con ID: " + request.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo por id: " + e.getMessage());
        }
    }

    @PostMapping("/compra-exitosa")
    public ResponseEntity<?> enviarCompraExitosa(@RequestBody CompraRequest request) {
        if (request.getUserId() == null || request.getProductoId() == null) {
            return ResponseEntity.badRequest().body("Los campos 'userId' y 'productoId' son obligatorios.");
        }

        try {
            emailService.enviarCorreoCompraExitosa(request.getUserId(), request.getProductoId());
            return ResponseEntity.ok("Correo de compra exitosa enviado.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar correo: " + e.getMessage());
        }
    }
}
