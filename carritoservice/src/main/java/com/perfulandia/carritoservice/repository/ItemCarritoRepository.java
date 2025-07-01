package com.perfulandia.carritoservice.repository;

import com.perfulandia.carritoservice.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para operaciones de base de datos con la entidad ItemCarrito
 * 
 * Esta interfaz extiende JpaRepository para proporcionar métodos CRUD básicos
 * y operaciones de consulta personalizadas para la entidad ItemCarrito.
 * Spring Data JPA implementa automáticamente esta interfaz en tiempo de ejecución.
 * 
 * Métodos heredados de JpaRepository:
 * - save(ItemCarrito): Guarda o actualiza un item del carrito
 * - findById(Long): Busca un item por su ID
 * - findAll(): Obtiene todos los items
 * - delete(ItemCarrito): Elimina un item
 * - count(): Cuenta el total de items
 * 
 * Métodos personalizados:
 * - findByCarritoId: Busca items por ID del carrito
 * - findByProductoId: Busca items por ID del producto
 * - deleteByCarritoId: Elimina todos los items de un carrito
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Repository // Marca esta interfaz como un repositorio de Spring, permitiendo la inyección de dependencias y el manejo de excepciones específicas de persistencia
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
    
    /**
     * Busca todos los items de un carrito específico
     * 
     * Este método utiliza la convención de nomenclatura de Spring Data JPA
     * para generar automáticamente una consulta SQL basada en el nombre del método.
     * 
     * La consulta generada será equivalente a:
     * SELECT * FROM items_carrito WHERE carrito_id = ?
     * 
     * @param carritoId ID del carrito del cual se quieren obtener los items
     * @return Lista de items del carrito (puede estar vacía si el carrito no tiene items)
     */
    List<ItemCarrito> findByCarritoId(Long carritoId);
    
    /**
     * Busca todos los items que contienen un producto específico
     * 
     * Este método es útil para encontrar en qué carritos está presente
     * un producto específico, o para análisis de productos más populares.
     * 
     * @param productoId ID del producto a buscar
     * @return Lista de items que contienen el producto especificado
     */
    List<ItemCarrito> findByProductoId(Long productoId);
    
    /**
     * Busca un item específico en un carrito específico
     * 
     * Este método es útil para verificar si un producto ya existe
     * en un carrito antes de agregarlo, o para actualizar la cantidad
     * de un producto existente.
     * 
     * @param carritoId ID del carrito
     * @param productoId ID del producto
     * @return Item del carrito que coincida con los criterios o null si no existe
     */
    ItemCarrito findByCarritoIdAndProductoId(Long carritoId, Long productoId);
    
    /**
     * Cuenta el número de items en un carrito específico
     * 
     * Este método es útil para mostrar al usuario cuántos productos
     * diferentes tiene en su carrito.
     * 
     * @param carritoId ID del carrito
     * @return Número de items en el carrito
     */
    long countByCarritoId(Long carritoId);
    
    /**
     * Elimina todos los items de un carrito específico
     * 
     * Este método es útil cuando se quiere vaciar completamente un carrito
     * o cuando se completa una compra y se quiere limpiar el carrito.
     * 
     * @param carritoId ID del carrito del cual se eliminarán todos los items
     */
    void deleteByCarritoId(Long carritoId);
    
    /**
     * Busca items por carrito y ordena por ID
     * 
     * Este método es útil para obtener los items de un carrito
     * en un orden específico, por ejemplo, por fecha de agregado.
     * 
     * @param carritoId ID del carrito
     * @return Lista de items del carrito ordenados por ID
     */
    List<ItemCarrito> findByCarritoIdOrderById(Long carritoId);
} 