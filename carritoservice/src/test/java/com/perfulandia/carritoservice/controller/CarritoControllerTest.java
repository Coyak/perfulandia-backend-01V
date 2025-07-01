package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.service.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarritoController.class)
@Import(MappingJackson2HttpMessageConverter.class)
class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Carrito carrito;
    private ItemCarrito itemCarrito;

    @BeforeEach
    void setUp() {
        carrito = Carrito.builder()
                .id(1L)
                .usuarioId(1L)
                .estado("ACTIVO")
                .fechaCreacion(LocalDateTime.now())
                .build();

        itemCarrito = ItemCarrito.builder()
                .id(1L)
                .carrito(carrito)
                .productoId(1L)
                .cantidad(2)
                .precioUnitario(10.0)
                .build();
    }

    @Test
    @DisplayName("Testing Controller 1 - Crear carrito")
    void testCrearCarrito() throws Exception {
        when(service.crearCarrito(anyLong())).thenReturn(carrito);

        mockMvc.perform(post("/api/carrito/usuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    @DisplayName("Testing Controller 2 - Obtener carrito activo")
    void testObtenerCarritoActivo() throws Exception {
        when(service.obtenerCarritoActivo(anyLong())).thenReturn(carrito);

        mockMvc.perform(get("/api/carrito/usuario/1/activo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    @DisplayName("Testing Controller 3 - Agregar item al carrito")
    void testAgregarItem() throws Exception {
        when(service.agregarItem(anyLong(), anyLong(), anyInt(), anyDouble())).thenReturn(itemCarrito);

        mockMvc.perform(post("/api/carrito/1/items")
                .param("productoId", "1")
                .param("cantidad", "2")
                .param("precioUnitario", "10.0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productoId").value(1))
                .andExpect(jsonPath("$.cantidad").value(2))
                .andExpect(jsonPath("$.precioUnitario").value(10.0));
    }

    @Test
    @DisplayName("Testing Controller 4 - Obtener items del carrito")
    void testObtenerItemsCarrito() throws Exception {
        List<ItemCarrito> items = Arrays.asList(itemCarrito);
        when(service.obtenerItemsCarrito(anyLong())).thenReturn(items);

        mockMvc.perform(get("/api/carrito/1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].productoId").value(1))
                .andExpect(jsonPath("$[0].cantidad").value(2));
    }

    @Test
    @DisplayName("Testing Controller 5 - Completar carrito")
    void testCompletarCarrito() throws Exception {
        mockMvc.perform(post("/api/carrito/1/completar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
} 