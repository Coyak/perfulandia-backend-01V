package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.EmailRequest;
import com.perfulandia.emailservice.model.Usuario;
import com.perfulandia.emailservice.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private ProductoService productoService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private EmailService emailService;

    private EmailRequest emailRequest;
    private Usuario usuario;
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        emailRequest = new EmailRequest();
        emailRequest.setPara("test@example.com");
        emailRequest.setAsunto("Test Subject");
        emailRequest.setMensaje("Test Message");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@example.com");

        producto = new Producto();
        producto.setId("1");
        producto.setNombre("Perfume Test");
        producto.setPrecio(29.99);
        producto.setStock(10);
    }

    @Test
    @DisplayName("Testing Service 1 - Enviar correo")
    void testEnviarCorreo() {
        // Arrange
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        assertDoesNotThrow(() -> emailService.enviarCorreo(emailRequest));

        // Assert
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 2 - Enviar correo por ID")
    void testEnviarPorId() {
        // Arrange
        when(usuarioService.getUserById(1L)).thenReturn(usuario);
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        assertDoesNotThrow(() -> emailService.enviarPorId(1L, "Test Subject", "Test Message"));

        // Assert
        verify(usuarioService).getUserById(1L);
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 3 - Enviar correo por ID con usuario no encontrado")
    void testEnviarPorIdUsuarioNoEncontrado() {
        // Arrange
        when(usuarioService.getUserById(999L)).thenThrow(new NoSuchElementException("Usuario no encontrado"));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            emailService.enviarPorId(999L, "Test Subject", "Test Message");
        });

        verify(usuarioService).getUserById(999L);
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 4 - Enviar correo compra exitosa")
    void testEnviarCorreoCompraExitosa() {
        // Arrange
        when(usuarioService.getUserById(1L)).thenReturn(usuario);
        when(productoService.obtenerProductoPorId(1L)).thenReturn(producto);
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        assertDoesNotThrow(() -> emailService.enviarCorreoCompraExitosa(1L, 1L));

        // Assert
        verify(usuarioService).getUserById(1L);
        verify(productoService).obtenerProductoPorId(1L);
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 5 - Enviar correo compra exitosa con usuario no encontrado")
    void testEnviarCorreoCompraExitosaUsuarioNoEncontrado() {
        // Arrange
        when(usuarioService.getUserById(999L)).thenThrow(new NoSuchElementException("Usuario no encontrado"));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            emailService.enviarCorreoCompraExitosa(999L, 1L);
        });

        verify(usuarioService).getUserById(999L);
        verify(productoService, never()).obtenerProductoPorId(anyLong());
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 6 - Enviar correo compra exitosa con producto no encontrado")
    void testEnviarCorreoCompraExitosaProductoNoEncontrado() {
        // Arrange
        when(usuarioService.getUserById(1L)).thenReturn(usuario);
        when(productoService.obtenerProductoPorId(999L)).thenThrow(new NoSuchElementException("Producto no encontrado"));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            emailService.enviarCorreoCompraExitosa(1L, 999L);
        });

        verify(usuarioService).getUserById(1L);
        verify(productoService).obtenerProductoPorId(999L);
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 7 - Enviar correo con error de mail")
    void testEnviarCorreoConError() {
        // Arrange
        doThrow(new RuntimeException("Error de conexión SMTP")).when(mailSender).send(any(SimpleMailMessage.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            emailService.enviarCorreo(emailRequest);
        });

        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("Testing Service 8 - Enviar correo con datos nulos")
    void testEnviarCorreoConDatosNulos() {
        // Arrange
        EmailRequest requestNulo = new EmailRequest();
        requestNulo.setPara(null);
        requestNulo.setAsunto(null);
        requestNulo.setMensaje(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            emailService.enviarCorreo(requestNulo);
        });
    }
} 