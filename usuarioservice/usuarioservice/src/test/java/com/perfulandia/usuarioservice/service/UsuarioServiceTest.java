package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        usuario = Usuario.builder()
                .id(1L)
                .nombre("Usuario Test")
                .correo("test@example.com")
                .rol("ADMIN")
                .build();
    }

    @Test
    @DisplayName("Testing Service 1 - Listar usuarios")
    void testListar() {
        // Arrange
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(repo.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = service.listar();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuario, result.get(0));
        verify(repo).findAll();
    }

    @Test
    @DisplayName("Testing Service 2 - Guardar usuario")
    void testGuardar() {
        // Arrange
        Usuario usuarioNuevo = Usuario.builder()
                .nombre("Nuevo Usuario")
                .correo("nuevo@example.com")
                .rol("GERENTE")
                .build();
        
        when(repo.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario result = service.guardar(usuarioNuevo);

        // Assert
        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getNombre(), result.getNombre());
        verify(repo).save(usuarioNuevo);
    }

    @Test
    @DisplayName("Testing Service 3 - Buscar usuario por ID")
    void testBuscar() {
        // Arrange
        when(repo.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Usuario result = service.buscar(1L);

        // Assert
        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getNombre(), result.getNombre());
        assertEquals(usuario.getCorreo(), result.getCorreo());
        assertEquals(usuario.getRol(), result.getRol());
        verify(repo).findById(1L);
    }

    @Test
    @DisplayName("Testing Service 4 - Buscar usuario que no existe")
    void testBuscarNoExiste() {
        // Arrange
        when(repo.findById(999L)).thenReturn(Optional.empty());

        // Act
        Usuario result = service.buscar(999L);

        // Assert
        assertNull(result);
        verify(repo).findById(999L);
    }

    @Test
    @DisplayName("Testing Service 5 - Eliminar usuario")
    void testEliminar() {
        // Arrange
        doNothing().when(repo).deleteById(1L);

        // Act
        service.eliminar(1L);

        // Assert
        verify(repo).deleteById(1L);
    }

    @Test
    @DisplayName("Testing Service 6 - Listar usuarios vacío")
    void testListarVacio() {
        // Arrange
        when(repo.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Usuario> result = service.listar();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repo).findAll();
    }

    @Test
    @DisplayName("Testing Service 7 - Guardar usuario con datos mínimos")
    void testGuardarDatosMinimos() {
        // Arrange
        Usuario usuarioMinimo = new Usuario();
        usuarioMinimo.setNombre("Usuario Mínimo");
        usuarioMinimo.setCorreo("minimo@example.com");
        
        when(repo.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario result = service.guardar(usuarioMinimo);

        // Assert
        assertNotNull(result);
        verify(repo).save(usuarioMinimo);
    }

    @Test
    @DisplayName("Testing Service 8 - Guardar usuario con rol específico")
    void testGuardarConRol() {
        // Arrange
        Usuario usuarioConRol = Usuario.builder()
                .nombre("Gerente Test")
                .correo("gerente@example.com")
                .rol("GERENTE")
                .build();
        
        when(repo.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario result = service.guardar(usuarioConRol);

        // Assert
        assertNotNull(result);
        verify(repo).save(usuarioConRol);
    }

    @Test
    @DisplayName("Testing Service 9 - Buscar múltiples usuarios")
    void testBuscarMultiplesUsuarios() {
        // Arrange
        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .nombre("Usuario 2")
                .correo("usuario2@example.com")
                .rol("USUARIO")
                .build();
        
        List<Usuario> usuarios = Arrays.asList(usuario, usuario2);
        when(repo.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = service.listar();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(usuario, result.get(0));
        assertEquals(usuario2, result.get(1));
        verify(repo).findAll();
    }
} 