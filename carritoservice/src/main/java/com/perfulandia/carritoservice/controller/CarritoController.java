package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el manejo de carritos de compras
 * 
 * Esta clase expone endpoints HTTP para gestionar las operaciones
 * relacionadas con carritos de compras. Proporciona funcionalidades
 * para crear, consultar, modificar y completar carritos.
 * 
 * Endpoints disponibles:
 * - POST /api/carrito/usuario/{usuarioId} - Crear un nuevo carrito
 * - GET /api/carrito/usuario/{usuarioId}/activo - Obtener carrito activo
 * - POST /api/carrito/{carritoId}/items - Agregar item al carrito
 * - GET /api/carrito/{carritoId}/items - Obtener items del carrito
 * - POST /api/carrito/{carritoId}/completar - Completar carrito
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@RestController // Marca esta clase como un controlador REST que devuelve respuestas en formato JSON automáticamente
@RequestMapping("/api/carrito") // Define la ruta base para todos los endpoints
@RequiredArgsConstructor // Genera un constructor con los campos final (inyección de dependencias)
public class CarritoController {
    
    /**
     * Servicio que contiene la lógica de negocio para carritos
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final CarritoService carritoService;
    
    /**
     * Crea un nuevo carrito de compras para un usuario específico
     * 
     * Este endpoint permite a un usuario iniciar un nuevo carrito de compras.
     * Si el usuario ya tiene un carrito activo, se puede implementar lógica
     * para manejarlo según los requerimientos del negocio.
     * 
     * @param usuarioId ID del usuario para el cual se creará el carrito
     * @return ResponseEntity<Carrito> con el carrito creado y estado HTTP 200
     */
    @PostMapping("/usuario/{usuarioId}") // Mapea este método a peticiones POST en la ruta especificada
    public ResponseEntity<Carrito> crearCarrito(@PathVariable Long usuarioId) { // Extrae el valor de la URL y lo convierte a Long
        // Delega la lógica de creación al servicio
        Carrito carrito = carritoService.crearCarrito(usuarioId);
        return ResponseEntity.ok(carrito);
    }
    
    /**
     * Obtiene el carrito activo de un usuario específico
     * 
     * Este endpoint permite consultar si un usuario tiene un carrito
     * activo (no completado) y obtener sus detalles.
     * 
     * @param usuarioId ID del usuario del cual se quiere obtener el carrito
     * @return ResponseEntity<Carrito> con el carrito activo o null si no existe
     */
    @GetMapping("/usuario/{usuarioId}/activo") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<Carrito> obtenerCarritoActivo(@PathVariable Long usuarioId) { // Extrae el valor de la URL y lo convierte a Long
        // Delega la búsqueda al servicio
        Carrito carrito = carritoService.obtenerCarritoActivo(usuarioId);
        return ResponseEntity.ok(carrito);
    }
    
    /**
     * Agrega un item (producto) a un carrito específico
     * 
     * Este endpoint permite agregar productos al carrito con su cantidad
     * y precio unitario. Incluye validaciones básicas para asegurar
     * que los datos sean válidos.
     * 
     * @param carritoId ID del carrito al cual se agregará el item
     * @param productoId ID del producto que se agregará
     * @param cantidad Cantidad del producto (debe ser mayor que 0)
     * @param precioUnitario Precio unitario del producto (debe ser mayor que 0)
     * @return ResponseEntity<ItemCarrito> con el item creado o error 400 si los datos son inválidos
     */
    @PostMapping("/{carritoId}/items") // Mapea este método a peticiones POST en la ruta especificada
    public ResponseEntity<ItemCarrito> agregarItem(
            @PathVariable Long carritoId, // Extrae el valor de la URL y lo convierte a Long
            @RequestParam Long productoId, // Extrae el parámetro de la query string
            @RequestParam Integer cantidad, // Extrae el parámetro de la query string
            @RequestParam Double precioUnitario) { // Extrae el parámetro de la query string
        
        // Validaciones básicas de los parámetros
        if (cantidad == null || cantidad <= 0 || precioUnitario == null || precioUnitario <= 0) {
            // Retorna error 400 (Bad Request) si los datos son inválidos
            return ResponseEntity.badRequest().build();
        }
        
        // Delega la lógica de agregar item al servicio
        ItemCarrito item = carritoService.agregarItem(carritoId, productoId, cantidad, precioUnitario);
        return ResponseEntity.ok(item);
    }
    
    /**
     * Obtiene todos los items de un carrito específico
     * 
     * Este endpoint permite consultar todos los productos que están
     * en un carrito específico.
     * 
     * @param carritoId ID del carrito del cual se quieren obtener los items
     * @return ResponseEntity<List<ItemCarrito>> con la lista de items del carrito
     */
    @GetMapping("/{carritoId}/items") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<List<ItemCarrito>> obtenerItemsCarrito(@PathVariable Long carritoId) { // Extrae el valor de la URL y lo convierte a Long
        // Delega la búsqueda al servicio
        List<ItemCarrito> items = carritoService.obtenerItemsCarrito(carritoId);
        return ResponseEntity.ok(items);
    }
    
    /**
     * Completa un carrito de compras
     * 
     * Este endpoint marca un carrito como completado, lo que significa
     * que el usuario ha finalizado su compra. Un carrito completado
     * no puede ser modificado.
     * 
     * @param carritoId ID del carrito que se completará
     * @return ResponseEntity<Void> con estado HTTP 200 si se completa exitosamente
     */
    @PostMapping("/{carritoId}/completar") // Mapea este método a peticiones POST en la ruta especificada
    public ResponseEntity<Void> completarCarrito(@PathVariable Long carritoId) { // Extrae el valor de la URL y lo convierte a Long
        // Delega la lógica de completar carrito al servicio
        carritoService.completarCarrito(carritoId);
        return ResponseEntity.ok().build();
    }
} 