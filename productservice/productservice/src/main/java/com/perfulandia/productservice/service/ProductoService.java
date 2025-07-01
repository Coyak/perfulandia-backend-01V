package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para el manejo de productos
 * 
 * Esta clase implementa todas las operaciones relacionadas con productos,
 * incluyendo creación, consulta, modificación y eliminación de productos
 * del catálogo. Actúa como intermediario entre el controlador y el
 * repositorio, aplicando las reglas de negocio necesarias.
 * 
 * Responsabilidades principales:
 * - Crear nuevos productos en el catálogo
 * - Buscar productos por diferentes criterios
 * - Actualizar información de productos existentes
 * - Eliminar productos del catálogo
 * - Aplicar reglas de negocio y validaciones
 * - Gestionar el inventario de productos
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
@RequiredArgsConstructor // Genera un constructor con los campos final para la inyección de dependencias
public class ProductoService {
    
    /**
     * Repositorio para operaciones de base de datos con productos
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final ProductoRepository productoRepository;
    
    /**
     * Obtiene todos los productos disponibles
     * 
     * Este método retorna una lista con todos los productos
     * que están en el catálogo, sin importar su stock.
     * 
     * @return Lista de todos los productos en el catálogo
     */
    public List<Producto> listar() {
        return productoRepository.findAll();
    }
    
    /**
     * Busca un producto por su ID
     * 
     * Este método busca un producto específico usando su
     * identificador único.
     * 
     * @param id ID del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    public Producto bucarPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }
    
    /**
     * Guarda un nuevo producto en el catálogo
     * 
     * Este método crea un nuevo producto en la base de datos.
     * Si el producto ya tiene un ID, se actualiza en lugar de crear.
     * 
     * @param producto Producto a guardar
     * @return Producto guardado con ID generado automáticamente
     */
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
    
    /**
     * Elimina un producto del catálogo
     * 
     * Este método elimina permanentemente un producto
     * de la base de datos.
     * 
     * @param id ID del producto a eliminar
     */
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    
    /**
     * Busca productos por nombre
     * 
     * Este método busca productos que contengan el nombre
     * especificado en su descripción.
     * 
     * @param nombre Nombre o parte del nombre del producto a buscar
     * @return Lista de productos que coinciden con el criterio de búsqueda
     */
    public List<Producto> buscarProductosPorNombre(String nombre) {
        // Implementación básica usando findAll y filtrado en memoria
        // En una implementación real, se agregarían métodos al repositorio
        return productoRepository.findAll().stream()
                .filter(p -> p.getNombre() != null && 
                           p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
    
    /**
     * Obtiene productos disponibles (con stock > 0)
     * 
     * Este método retorna solo los productos que tienen
     * stock disponible para la venta.
     * 
     * @return Lista de productos disponibles
     */
    public List<Producto> obtenerProductosDisponibles() {
        // Implementación básica usando findAll y filtrado en memoria
        // En una implementación real, se agregarían métodos al repositorio
        return productoRepository.findAll().stream()
                .filter(p -> p.getStock() != null && p.getStock() > 0)
                .toList();
    }
    
    /**
     * Actualiza el stock de un producto
     * 
     * Este método se utiliza para actualizar la cantidad
     * disponible de un producto específico.
     * 
     * @param id ID del producto
     * @param nuevoStock Nueva cantidad de stock
     * @return true si se actualizó exitosamente, false si el producto no existe
     */
    public boolean actualizarStock(Long id, Integer nuevoStock) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setStock(nuevoStock);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }
    
    /**
     * Reduce el stock de un producto
     * 
     * Este método se utiliza cuando se vende una unidad
     * del producto para actualizar el inventario.
     * 
     * @param id ID del producto
     * @param cantidad Cantidad a reducir del stock
     * @return true si se pudo reducir el stock, false si no hay suficiente
     */
    public boolean reducirStock(Long id, Integer cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si un producto existe
     * 
     * Este método verifica si un producto con el ID especificado
     * existe en el catálogo.
     * 
     * @param id ID del producto a verificar
     * @return true si el producto existe, false en caso contrario
     */
    public boolean productoExiste(Long id) {
        return productoRepository.existsById(id);
    }
    
    /**
     * Cuenta el total de productos en el catálogo
     * 
     * @return Número total de productos
     */
    public long contarProductos() {
        return productoRepository.count();
    }
}
