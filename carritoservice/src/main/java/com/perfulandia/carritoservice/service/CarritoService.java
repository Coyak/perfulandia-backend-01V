package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.ItemCarrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;
import com.perfulandia.carritoservice.repository.ItemCarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para el manejo de carritos de compras
 * 
 * Esta clase implementa todas las operaciones relacionadas con carritos de compras,
 * incluyendo creación, consulta, modificación y finalización de carritos. Actúa
 * como intermediario entre el controlador y los repositorios, aplicando las
 * reglas de negocio necesarias.
 * 
 * Responsabilidades principales:
 * - Crear nuevos carritos para usuarios
 * - Buscar carritos activos de usuarios
 * - Agregar productos a carritos existentes
 * - Consultar items de carritos
 * - Completar carritos (finalizar compra)
 * - Aplicar reglas de negocio y validaciones
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
@RequiredArgsConstructor // Genera un constructor con los campos final para la inyección de dependencias
public class CarritoService {
    
    /**
     * Repositorio para operaciones de base de datos con carritos
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final CarritoRepository carritoRepository;
    
    /**
     * Repositorio para operaciones de base de datos con items de carrito
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final ItemCarritoRepository itemCarritoRepository;
    
    /**
     * Crea un nuevo carrito de compras para un usuario específico
     * 
     * Este método crea un nuevo carrito con estado "ACTIVO" y la fecha
     * de creación actual. Un usuario puede tener solo un carrito activo
     * a la vez.
     * 
     * @param usuarioId ID del usuario para el cual se creará el carrito
     * @return Carrito creado con ID generado automáticamente
     */
    public Carrito crearCarrito(Long usuarioId) {
        // Crear un nuevo carrito con los datos básicos
        Carrito carrito = Carrito.builder()
                .usuarioId(usuarioId)
                .estado("ACTIVO") // Estado inicial del carrito
                .fechaCreacion(LocalDateTime.now()) // Fecha y hora actual
                .build();
        
        // Guardar el carrito en la base de datos y retornarlo
        return carritoRepository.save(carrito);
    }
    
    /**
     * Obtiene el carrito activo de un usuario específico
     * 
     * Este método busca un carrito que esté en estado "ACTIVO" para
     * el usuario especificado. Si no existe, retorna null.
     * 
     * @param usuarioId ID del usuario del cual se quiere obtener el carrito
     * @return Carrito activo del usuario o null si no existe
     */
    public Carrito obtenerCarritoActivo(Long usuarioId) {
        // Buscar carrito activo del usuario usando el repositorio
        return carritoRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO");
    }
    
    /**
     * Agrega un item (producto) a un carrito específico
     * 
     * Este método crea un nuevo item en el carrito con el producto,
     * cantidad y precio especificados. Incluye validaciones para
     * asegurar que el carrito existe y está activo.
     * 
     * @param carritoId ID del carrito al cual se agregará el item
     * @param productoId ID del producto que se agregará
     * @param cantidad Cantidad del producto
     * @param precioUnitario Precio unitario del producto
     * @return ItemCarrito creado con ID generado automáticamente
     * @throws RuntimeException si el carrito no existe o no está activo
     */
    public ItemCarrito agregarItem(Long carritoId, Long productoId, Integer cantidad, Double precioUnitario) {
        // Buscar el carrito en la base de datos
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        
        // Verificar que el carrito esté activo
        if (!"ACTIVO".equals(carrito.getEstado())) {
            throw new RuntimeException("No se puede agregar items a un carrito no activo");
        }
        
        // Crear el nuevo item del carrito
        ItemCarrito item = ItemCarrito.builder()
                .carrito(carrito)
                .productoId(productoId)
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .build();
        
        // Guardar el item en la base de datos y retornarlo
        return itemCarritoRepository.save(item);
    }
    
    /**
     * Obtiene todos los items de un carrito específico
     * 
     * Este método retorna una lista con todos los productos que están
     * en el carrito especificado.
     * 
     * @param carritoId ID del carrito del cual se quieren obtener los items
     * @return Lista de items del carrito
     */
    public List<ItemCarrito> obtenerItemsCarrito(Long carritoId) {
        // Buscar todos los items del carrito usando el repositorio
        return itemCarritoRepository.findByCarritoId(carritoId);
    }
    
    /**
     * Completa un carrito de compras
     * 
     * Este método marca un carrito como "COMPLETADO", lo que significa
     * que el usuario ha finalizado su compra. Un carrito completado
     * no puede ser modificado posteriormente.
     * 
     * @param carritoId ID del carrito que se completará
     * @throws RuntimeException si el carrito no existe
     */
    public void completarCarrito(Long carritoId) {
        // Buscar el carrito en la base de datos
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        
        // Cambiar el estado del carrito a "COMPLETADO"
        carrito.setEstado("COMPLETADO");
        
        // Guardar los cambios en la base de datos
        carritoRepository.save(carrito);
    }
} 