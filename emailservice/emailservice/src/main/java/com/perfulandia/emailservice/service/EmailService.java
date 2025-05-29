package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.model.Usuario;
import com.perfulandia.emailservice.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ProductoService productoService;

    public void enviarCorreo(EmailRequest request) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(request.getPara());
        mensaje.setSubject(request.getAsunto());
        mensaje.setText(request.getMensaje());
        mensaje.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mensaje);
    }

    @Autowired
    private UsuarioService usuarioService;

    public void enviarPorId(Long id, String subject, String body) {
        Usuario user = usuarioService.getUserById(id);

        String nombre = user.getNombre(); // Definir nombre del usuario
        String mensajePersonalizado = "Hola " + nombre + "\n" + body;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(user.getCorreo());
        mensaje.setSubject(subject);
        mensaje.setText(mensajePersonalizado);
        mensaje.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mensaje);
    }


    public void enviarCorreoCompraExitosa(Long userId, Long productoId) {
        Usuario user = usuarioService.getUserById(userId);
        Producto producto = productoService.obtenerProductoPorId(productoId);

        String asunto = "¡Compra realizada con éxito!";
        String mensaje = String.format(
                "Hola %s\n\nTu compra fue exitosa.\n\nProducto: %s\nPrecio: $%.2f\n\nGracias por tu compra.",
                user.getNombre(), producto.getNombre(), producto.getPrecio()
        );

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getCorreo());
        mail.setSubject(asunto);
        mail.setText(mensaje);
        mail.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mail);
    }
}