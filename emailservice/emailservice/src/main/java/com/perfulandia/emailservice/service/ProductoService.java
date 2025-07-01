package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.Producto;
import org.springframework.stereotype.Service;

/**
 * Servicio para obtener información de productos
 * 
 * Esta clase simula la obtención de datos de productos desde
 * el microservicio de productos. En una implementación real,
 * este servicio haría llamadas HTTP al microservicio de productos
 * para obtener la información necesaria.
 * 
 * Responsabilidades principales:
 * - Obtener información de productos por ID
 * - Simular datos de productos para testing
 * - Manejo de casos donde el producto no existe
 * 
 * Nota: Esta es una implementación simulada para el propósito
 * de testing y demostración. En producción, se conectaría
 * al microservicio real de productos.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
public class ProductoService {
    
    /**
     * Obtiene un producto por su ID
     * 
     * Este método simula la búsqueda de un producto en la base de datos
     * o en el microservicio de productos. Para el propósito de testing,
     * retorna datos simulados basados en el ID proporcionado.
     * 
     * @param id ID del producto a buscar
     * @return Producto con los datos correspondientes al ID
     * @throws RuntimeException si el producto no existe
     */
    public Producto obtenerProductoPorId(Long id) {
        // Simulación de datos de productos para testing
        // En una implementación real, esto haría una llamada HTTP
        // al microservicio de productos
        
        if (id == null) {
            throw new RuntimeException("ID de producto no puede ser null");
        }
        
        // Simular diferentes productos basados en el ID
        switch (id.intValue()) {
            case 1:
                return Producto.builder()
                    .id("1")
                    .nombre("Laptop Gaming")
                    .precio(1299.99)
                    .stock(10)
                    .build();
            case 2:
                return Producto.builder()
                    .id("2")
                    .nombre("Smartphone Pro")
                    .precio(899.99)
                    .stock(15)
                    .build();
            case 3:
                return Producto.builder()
                    .id("3")
                    .nombre("Auriculares Wireless")
                    .precio(199.99)
                    .stock(25)
                    .build();
            case 4:
                return Producto.builder()
                    .id("4")
                    .nombre("Tablet Ultra")
                    .precio(499.99)
                    .stock(8)
                    .build();
            case 5:
                return Producto.builder()
                    .id("5")
                    .nombre("Smartwatch Sport")
                    .precio(299.99)
                    .stock(20)
                    .build();
            default:
                // Simular que el producto no existe
                throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
    
    /**
     * Verifica si un producto existe
     * 
     * Este método verifica si un producto con el ID especificado
     * existe en el sistema.
     * 
     * @param id ID del producto a verificar
     * @return true si el producto existe, false en caso contrario
     */
    public boolean productoExiste(Long id) {
        try {
            obtenerProductoPorId(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    
    /**
     * Obtiene el nombre de un producto por su ID
     * 
     * Este método es un helper que obtiene solo el nombre
     * de un producto específico.
     * 
     * @param id ID del producto
     * @return Nombre del producto
     * @throws RuntimeException si el producto no existe
     */
    public String getNombreById(Long id) {
        Producto producto = obtenerProductoPorId(id);
        return producto.getNombre();
    }
    
    /**
     * Obtiene el precio de un producto por su ID
     * 
     * Este método es un helper que obtiene solo el precio
     * de un producto específico.
     * 
     * @param id ID del producto
     * @return Precio del producto
     * @throws RuntimeException si el producto no existe
     */
    public Double getPrecioById(Long id) {
        Producto producto = obtenerProductoPorId(id);
        return producto.getPrecio();
    }
    
    /**
     * Verifica si un producto está disponible (tiene stock)
     * 
     * Este método verifica si un producto tiene stock disponible
     * para la venta.
     * 
     * @param id ID del producto
     * @return true si el producto tiene stock disponible, false en caso contrario
     * @throws RuntimeException si el producto no existe
     */
    public boolean productoDisponible(Long id) {
        Producto producto = obtenerProductoPorId(id);
        return producto.estaDisponible();
    }
}
