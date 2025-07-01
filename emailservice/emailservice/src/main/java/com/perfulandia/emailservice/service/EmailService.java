package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.model.CompraRequest;
import com.perfulandia.emailservice.model.Usuario;
import com.perfulandia.emailservice.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene la lógica de negocio para el envío de emails
 * 
 * Esta clase implementa todas las operaciones relacionadas con el envío
 * de correos electrónicos, incluyendo emails simples y emails de
 * confirmación de compra personalizados.
 * 
 * Dependencias inyectadas:
 * - JavaMailSender: Para el envío real de emails
 * - ProductoService: Para obtener información de productos
 * - UsuarioService: Para obtener información de usuarios
 * 
 * Responsabilidades principales:
 * - Envío de emails simples con destinatario, asunto y mensaje
 * - Envío de emails por ID de usuario
 * - Envío de emails de confirmación de compra
 * - Validación de datos de entrada
 * - Manejo de errores de envío
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
public class EmailService {

    /**
     * Bean de Spring para el envío de emails
     * Configurado automáticamente con los parámetros SMTP de Gmail
     */
    @Autowired // Inyecta automáticamente el bean JavaMailSender
    private JavaMailSender mailSender;
    
    /**
     * Servicio para obtener información de productos
     * Se utiliza para incluir detalles de productos en emails de compra
     */
    @Autowired // Inyecta automáticamente el bean ProductoService
    private ProductoService productoService;
    
    /**
     * Servicio para obtener información de usuarios
     * Se utiliza para personalizar emails con datos del cliente
     */
    @Autowired // Inyecta automáticamente el bean UsuarioService
    private UsuarioService usuarioService;

    /**
     * Envía un email simple con los datos proporcionados
     * 
     * Este método valida que todos los campos obligatorios estén presentes
     * y envía un email usando el JavaMailSender configurado.
     * 
     * @param request Objeto EmailRequest con los datos del email
     * @throws NullPointerException si algún campo obligatorio es null
     * @throws RuntimeException si hay error en el envío
     */
    public void enviarCorreo(EmailRequest request) {
        // Validar que los campos no sean nulos
        if (request.getPara() == null) {
            throw new NullPointerException("El campo 'para' no puede ser nulo");
        }
        if (request.getAsunto() == null) {
            throw new NullPointerException("El campo 'asunto' no puede ser nulo");
        }
        if (request.getMensaje() == null) {
            throw new NullPointerException("El campo 'mensaje' no puede ser nulo");
        }

        // Crear el mensaje de email
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(request.getPara());
        mensaje.setSubject(request.getAsunto());
        mensaje.setText(request.getMensaje());
        mensaje.setFrom("perfulandia.comercial@gmail.com");

        // Enviar el email
        mailSender.send(mensaje);
    }

    /**
     * Envía un email a un usuario específico por su ID
     * 
     * Este método obtiene la información del usuario por su ID
     * y envía un email personalizado con su nombre.
     * 
     * @param id ID del usuario destinatario
     * @param subject Asunto del email
     * @param body Cuerpo del email (se personaliza con el nombre del usuario)
     * @throws RuntimeException si el usuario no existe o hay error en el envío
     */
    public void enviarPorId(Long id, String subject, String body) {
        // Obtener información del usuario
        Usuario user = usuarioService.getUserById(id);

        // Personalizar el mensaje con el nombre del usuario
        String nombre = user.getNombre();
        String mensajePersonalizado = "Hola " + nombre + "\n" + body;

        // Crear y enviar el email
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(user.getEmail());
        mensaje.setSubject(subject);
        mensaje.setText(mensajePersonalizado);
        mensaje.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mensaje);
    }

    /**
     * Envía un email de confirmación de compra exitosa
     * 
     * Este método envía un email de confirmación cuando un usuario
     * completa una compra exitosamente. Incluye detalles del producto
     * comprado y un mensaje personalizado.
     * 
     * @param userId ID del usuario que realizó la compra
     * @param productoId ID del producto comprado
     * @throws RuntimeException si el usuario o producto no existen
     */
    public void enviarCorreoCompraExitosa(Long userId, Long productoId) {
        // Obtener información del usuario y producto
        Usuario user = usuarioService.getUserById(userId);
        Producto producto = productoService.obtenerProductoPorId(productoId);

        // Crear el mensaje personalizado
        String asunto = "¡Compra realizada con éxito!";
        String mensaje = String.format(
                "Hola %s\n\nTu compra fue exitosa.\n\nProducto: %s\nPrecio: $%.2f\n\nGracias por tu compra.",
                user.getNombre(), producto.getNombre(), producto.getPrecio()
        );

        // Crear y enviar el email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(asunto);
        mail.setText(mensaje);
        mail.setFrom("perfulandia.comercial@gmail.com");

        mailSender.send(mail);
    }
    
    /**
     * Envía un email de confirmación de compra con detalles completos
     * 
     * Este método envía un email de confirmación de compra que incluye
     * información detallada del pedido, todos los productos comprados
     * y el total de la compra.
     * 
     * @param request Objeto CompraRequest con todos los datos de la compra
     * @throws RuntimeException si hay error en el envío
     */
    public void enviarEmailCompra(CompraRequest request) {
        // Obtener datos del usuario y productos
        Usuario usuario = request.getUsuario();
        
        // Crear el asunto del email
        String asunto = "Confirmación de compra - Pedido #" + request.getNumeroPedido();
        
        // Construir el mensaje con detalles de la compra
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Hola ").append(usuario.getNombre()).append(",\n\n");
        mensaje.append("Gracias por tu compra. Tu pedido ha sido confirmado.\n\n");
        mensaje.append("Detalles del pedido:\n");
        mensaje.append("Número de pedido: ").append(request.getNumeroPedido()).append("\n");
        mensaje.append("Fecha: ").append(request.getFechaCompra()).append("\n\n");
        
        // Agregar lista de productos
        mensaje.append("Productos comprados:\n");
        for (Producto producto : request.getProductos()) {
            mensaje.append("- ").append(producto.getNombre())
                   .append(" - $").append(String.format("%.2f", producto.getPrecio()))
                   .append("\n");
        }
        
        mensaje.append("\nTotal de la compra: $").append(String.format("%.2f", request.getTotal()));
        mensaje.append("\n\nGracias por elegir Perfulandia.\n");
        mensaje.append("Te mantendremos informado sobre el estado de tu pedido.");
        
        // Crear y enviar el email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(usuario.getEmail());
        mail.setSubject(asunto);
        mail.setText(mensaje.toString());
        mail.setFrom("perfulandia.comercial@gmail.com");
        
        mailSender.send(mail);
    }
}