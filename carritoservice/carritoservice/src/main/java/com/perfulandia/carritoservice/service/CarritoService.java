package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import com.perfulandia.carritoservice.repository.ItemCarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {
    
    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    
    public Carrito crearCarrito(Long usuarioId) {
        Carrito carrito = Carrito.builder()
                .usuarioId(usuarioId)
                .fechaCreacion(LocalDateTime.now())
                .estado("ACTIVO")
                .build();
        return carritoRepository.save(carrito);
    }
    
    public Carrito obtenerCarritoActivo(Long usuarioId) {
        return carritoRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO");
    }
    
    public ItemCarrito agregarItem(Long carritoId, Long productoId, Integer cantidad, Double precioUnitario) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
                
        ItemCarrito item = ItemCarrito.builder()
                .carrito(carrito)
                .productoId(productoId)
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .build();
                
        return itemCarritoRepository.save(item);
    }
    
    public List<ItemCarrito> obtenerItemsCarrito(Long carritoId) {
        return itemCarritoRepository.findByCarritoId(carritoId);
    }
    
    public void completarCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setEstado("COMPLETADO");
        carritoRepository.save(carrito);
    }
} 