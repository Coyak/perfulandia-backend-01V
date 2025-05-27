package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(EmailRequest request) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(request.getPara());
        mensaje.setSubject(request.getAsunto());
        mensaje.setText(request.getMensaje());
        mensaje.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mensaje);
    }
}
