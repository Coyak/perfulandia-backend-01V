package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService servicio;

    @MockBean
    private RestTemplate restTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    private Producto producto;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .nombre("Perfume Test")
                .precio(29.99)
                .stock(10)
                .build();

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("test@example.com");
    }

    @Test
    @DisplayName("Testing Controller 1 - Listar productos")
    void testListar() throws Exception {
        List<Producto> productos = Arrays.asList(producto);
        when(servicio.listar()).thenReturn(productos);

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Perfume Test"))
                .andExpect(jsonPath("$[0].precio").value(29.99))
                .andExpect(jsonPath("$[0].stock").value(10));

        verify(servicio).listar();
    }

    @Test
    @DisplayName("Testing Controller 2 - Guardar producto")
    void testGuardar() throws Exception {
        when(servicio.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Perfume Test"))
                .andExpect(jsonPath("$.precio").value(29.99));

        verify(servicio).guardar(any(Producto.class));
    }

    @Test
    @DisplayName("Testing Controller 3 - Buscar producto por ID")
    void testBuscar() throws Exception {
        when(servicio.bucarPorId(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Perfume Test"))
                .andExpect(jsonPath("$.precio").value(29.99));

        verify(servicio).bucarPorId(1L);
    }

    @Test
    @DisplayName("Testing Controller 4 - Buscar producto que no existe")
    void testBuscarNoExiste() throws Exception {
        when(servicio.bucarPorId(999L)).thenReturn(null);

        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(servicio).bucarPorId(999L);
    }

    @Test
    @DisplayName("Testing Controller 5 - Eliminar producto")
    void testEliminar() throws Exception {
        doNothing().when(servicio).eliminar(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());

        verify(servicio).eliminar(1L);
    }

    @Test
    @DisplayName("Testing Controller 6 - Obtener usuario")
    void testObtenerUsuario() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(get("/api/productos/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Usuario Test"))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(restTemplate).getForObject("http://localhost:8081/api/usuarios/1", Usuario.class);
    }

    @Test
    @DisplayName("Testing Controller 7 - Obtener usuario que no existe")
    void testObtenerUsuarioNoExiste() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Usuario.class))).thenReturn(null);

        mockMvc.perform(get("/api/productos/usuario/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(restTemplate).getForObject("http://localhost:8081/api/usuarios/999", Usuario.class);
    }

    @Test
    @DisplayName("Testing Controller 8 - Error en RestTemplate")
    void testErrorRestTemplate() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Usuario.class)))
                .thenThrow(new RuntimeException("Error de conexión"));

        mockMvc.perform(get("/api/productos/usuario/1"))
                .andExpect(status().isInternalServerError());
    }
} 