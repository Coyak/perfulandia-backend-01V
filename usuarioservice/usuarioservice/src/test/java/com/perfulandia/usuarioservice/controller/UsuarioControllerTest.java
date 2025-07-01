package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .id(1L)
                .nombre("Usuario Test")
                .correo("test@example.com")
                .rol("ADMIN")
                .build();
    }

    @Test
    @DisplayName("Testing Controller 1 - Listar usuarios")
    void testListar() throws Exception {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(service.listar()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Usuario Test"))
                .andExpect(jsonPath("$[0].correo").value("test@example.com"))
                .andExpect(jsonPath("$[0].rol").value("ADMIN"));

        verify(service).listar();
    }

    @Test
    @DisplayName("Testing Controller 2 - Guardar usuario")
    void testGuardar() throws Exception {
        when(service.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Usuario Test"))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("ADMIN"));

        verify(service).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Testing Controller 3 - Buscar usuario por ID")
    void testBuscar() throws Exception {
        when(service.buscar(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Usuario Test"))
                .andExpect(jsonPath("$.correo").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value("ADMIN"));

        verify(service).buscar(1L);
    }

    @Test
    @DisplayName("Testing Controller 4 - Buscar usuario que no existe")
    void testBuscarNoExiste() throws Exception {
        when(service.buscar(999L)).thenReturn(null);

        mockMvc.perform(get("/api/usuarios/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(service).buscar(999L);
    }

    @Test
    @DisplayName("Testing Controller 5 - Eliminar usuario existente")
    void testEliminarExistente() throws Exception {
        when(service.buscar(1L)).thenReturn(usuario);
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminar(1L);
    }

    @Test
    @DisplayName("Testing Controller 5b - Eliminar usuario que no existe")
    void testEliminarNoExiste() throws Exception {
        when(service.buscar(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNotFound());

        verify(service, never()).eliminar(1L);
    }

    @Test
    @DisplayName("Testing Controller 6 - Listar usuarios vacío")
    void testListarVacio() throws Exception {
        when(service.listar()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(service).listar();
    }

    @Test
    @DisplayName("Testing Controller 7 - Guardar usuario con datos mínimos")
    void testGuardarDatosMinimos() throws Exception {
        Usuario usuarioMinimo = new Usuario();
        usuarioMinimo.setNombre("Usuario Mínimo");
        usuarioMinimo.setCorreo("minimo@example.com");

        when(service.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioMinimo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(service).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Testing Controller 8 - Guardar usuario con rol específico")
    void testGuardarConRol() throws Exception {
        Usuario usuarioConRol = Usuario.builder()
                .nombre("Gerente Test")
                .correo("gerente@example.com")
                .rol("GERENTE")
                .build();

        when(service.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioConRol)))
                .andExpect(status().isOk());

        verify(service).guardar(any(Usuario.class));
    }
} 