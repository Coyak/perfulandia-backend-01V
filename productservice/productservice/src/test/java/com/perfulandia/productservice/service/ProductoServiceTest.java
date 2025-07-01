package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
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

public class ProductoServiceTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        producto = Producto.builder()
                .id(1L)
                .nombre("Perfume Test")
                .precio(29.99)
                .stock(10)
                .build();
    }

    @Test
    @DisplayName("Testing Service 1 - Listar productos")
    void testListar() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        when(repo.findAll()).thenReturn(productos);

        // Act
        List<Producto> result = service.listar();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(producto, result.get(0));
        verify(repo).findAll();
    }

    @Test
    @DisplayName("Testing Service 2 - Guardar producto")
    void testGuardar() {
        // Arrange
        Producto productoNuevo = Producto.builder()
                .nombre("Nuevo Perfume")
                .precio(39.99)
                .stock(5)
                .build();
        
        when(repo.save(any(Producto.class))).thenReturn(producto);

        // Act
        Producto result = service.guardar(productoNuevo);

        // Assert
        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
        assertEquals(producto.getNombre(), result.getNombre());
        verify(repo).save(productoNuevo);
    }

    @Test
    @DisplayName("Testing Service 3 - Buscar producto por ID")
    void testBuscarPorId() {
        // Arrange
        when(repo.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        Producto result = service.bucarPorId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(producto.getId(), result.getId());
        assertEquals(producto.getNombre(), result.getNombre());
        verify(repo).findById(1L);
    }

    @Test
    @DisplayName("Testing Service 4 - Buscar producto que no existe")
    void testBuscarPorIdNoExiste() {
        // Arrange
        when(repo.findById(999L)).thenReturn(Optional.empty());

        // Act
        Producto result = service.bucarPorId(999L);

        // Assert
        assertNull(result);
        verify(repo).findById(999L);
    }

    @Test
    @DisplayName("Testing Service 5 - Eliminar producto")
    void testEliminar() {
        // Arrange
        doNothing().when(repo).deleteById(1L);

        // Act
        service.eliminar(1L);

        // Assert
        verify(repo).deleteById(1L);
    }

    @Test
    @DisplayName("Testing Service 6 - Listar productos vacío")
    void testListarVacio() {
        // Arrange
        when(repo.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Producto> result = service.listar();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repo).findAll();
    }

    @Test
    @DisplayName("Testing Service 7 - Guardar producto con datos mínimos")
    void testGuardarDatosMinimos() {
        // Arrange
        Producto productoMinimo = new Producto();
        productoMinimo.setNombre("Perfume Mínimo");
        
        when(repo.save(any(Producto.class))).thenReturn(producto);

        // Act
        Producto result = service.guardar(productoMinimo);

        // Assert
        assertNotNull(result);
        verify(repo).save(productoMinimo);
    }
} 