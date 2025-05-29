package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {
    
    private final CarritoService carritoService;
    
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Carrito> crearCarrito(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.crearCarrito(usuarioId));
    }
    
    @GetMapping("/usuario/{usuarioId}/activo")
    public ResponseEntity<Carrito> obtenerCarritoActivo(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoActivo(usuarioId));
    }
    
    @PostMapping("/{carritoId}/items")
    public ResponseEntity<ItemCarrito> agregarItem(
            @PathVariable Long carritoId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam Double precioUnitario) {
        return ResponseEntity.ok(carritoService.agregarItem(carritoId, productoId, cantidad, precioUnitario));
    }
    
    @GetMapping("/{carritoId}/items")
    public ResponseEntity<List<ItemCarrito>> obtenerItemsCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(carritoService.obtenerItemsCarrito(carritoId));
    }
    
    @PostMapping("/{carritoId}/completar")
    public ResponseEntity<Void> completarCarrito(@PathVariable Long carritoId) {
        carritoService.completarCarrito(carritoId);
        return ResponseEntity.ok().build();
    }
} 