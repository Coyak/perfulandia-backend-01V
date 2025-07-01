package com.perfulandia.productservice.repository;

import com.perfulandia.productservice.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones de base de datos con la entidad Producto
 * 
 * Esta interfaz extiende JpaRepository para proporcionar métodos CRUD básicos
 * y operaciones de consulta personalizadas para la entidad Producto.
 * Spring Data JPA implementa automáticamente esta interfaz en tiempo de ejecución.
 * 
 * Métodos heredados de JpaRepository:
 * - save(Producto): Guarda o actualiza un producto
 * - findById(Long): Busca un producto por su ID
 * - findAll(): Obtiene todos los productos
 * - delete(Producto): Elimina un producto
 * - count(): Cuenta el total de productos
 * - existsById(Long): Verifica si existe un producto con el ID especificado
 * 
 * Nota: En una implementación completa, se agregarían métodos personalizados
 * como findByNombreContainingIgnoreCase, findByStockGreaterThan, etc.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Repository // Marca esta interfaz como un repositorio de Spring, permitiendo la inyección de dependencias y el manejo de excepciones específicas de persistencia
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Métodos personalizados que se pueden agregar en el futuro:
    // List<Producto> findByNombreContainingIgnoreCase(String nombre);
    // List<Producto> findByStockGreaterThan(Integer stock);
    // List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);
    // List<Producto> findByPrecioLessThan(Double precio);
    
}
