package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import com.perfulandia.carritoservice.repository.ItemCarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarritoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private ItemCarritoRepository itemCarritoRepository;

    @InjectMocks
    private CarritoService service;

    private Carrito carrito;
    private ItemCarrito itemCarrito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        carrito = Carrito.builder()
                .id(1L)
                .usuarioId(1L)
                .fechaCreacion(LocalDateTime.now())
                .estado("ACTIVO")
                .build();

        itemCarrito = ItemCarrito.builder()
                .id(1L)
                .carrito(carrito)
                .productoId(1L)
                .cantidad(2)
                .precioUnitario(29.99)
                .build();
    }

    @Test
    void testCrearCarrito() {
        // Arrange
        Long usuarioId = 1L;
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        // Act
        Carrito result = service.crearCarrito(usuarioId);

        // Assert
        assertNotNull(result);
        assertEquals(usuarioId, result.getUsuarioId());
        assertEquals("ACTIVO", result.getEstado());
        verify(carritoRepository).save(any(Carrito.class));
    }

    @Test
    void testObtenerCarritoActivo() {
        // Arrange
        Long usuarioId = 1L;
        when(carritoRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO"))
                .thenReturn(carrito);

        // Act
        Carrito result = service.obtenerCarritoActivo(usuarioId);

        // Assert
        assertNotNull(result);
        assertEquals(usuarioId, result.getUsuarioId());
        verify(carritoRepository).findByUsuarioIdAndEstado(usuarioId, "ACTIVO");
    }

    @Test
    void testAgregarItem() {
        // Arrange
        Long carritoId = 1L;
        Long productoId = 1L;
        Integer cantidad = 2;
        Double precioUnitario = 29.99;

        when(carritoRepository.findById(carritoId)).thenReturn(Optional.of(carrito));
        when(itemCarritoRepository.save(any(ItemCarrito.class))).thenReturn(itemCarrito);

        // Act
        ItemCarrito result = service.agregarItem(carritoId, productoId, cantidad, precioUnitario);

        // Assert
        assertNotNull(result);
        assertEquals(productoId, result.getProductoId());
        assertEquals(cantidad, result.getCantidad());
        assertEquals(precioUnitario, result.getPrecioUnitario());
        verify(carritoRepository).findById(carritoId);
        verify(itemCarritoRepository).save(any(ItemCarrito.class));
    }

    @Test
    void testAgregarItem_CarritoNoExiste() {
        // Arrange
        Long carritoId = 999L;
        when(carritoRepository.findById(carritoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            service.agregarItem(carritoId, 1L, 1, 10.0);
        });
        verify(carritoRepository).findById(carritoId);
        verify(itemCarritoRepository, never()).save(any(ItemCarrito.class));
    }

    @Test
    void testObtenerItemsCarrito() {
        // Arrange
        Long carritoId = 1L;
        List<ItemCarrito> items = Arrays.asList(itemCarrito);
        when(itemCarritoRepository.findByCarritoId(carritoId)).thenReturn(items);

        // Act
        List<ItemCarrito> result = service.obtenerItemsCarrito(carritoId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(itemCarrito, result.get(0));
        verify(itemCarritoRepository).findByCarritoId(carritoId);
    }

    @Test
    void testCompletarCarrito() {
        // Arrange
        Long carritoId = 1L;
        when(carritoRepository.findById(carritoId)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        // Act
        service.completarCarrito(carritoId);

        // Assert
        assertEquals("COMPLETADO", carrito.getEstado());
        verify(carritoRepository).findById(carritoId);
        verify(carritoRepository).save(carrito);
    }

    @Test
    void testCompletarCarrito_CarritoNoExiste() {
        // Arrange
        Long carritoId = 999L;
        when(carritoRepository.findById(carritoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            service.completarCarrito(carritoId);
        });
        verify(carritoRepository).findById(carritoId);
        verify(carritoRepository, never()).save(any(Carrito.class));
    }
} 