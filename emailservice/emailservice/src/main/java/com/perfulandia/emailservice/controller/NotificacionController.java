package com.perfulandia.emailservice.controller;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public String enviarNotificacion(@RequestBody EmailRequest request) {
        emailService.enviarCorreo(request);
        return "Correo enviado a " + request.getPara();
    }
}
