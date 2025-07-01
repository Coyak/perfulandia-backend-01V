package com.perfulandia.emailservice.model;

import lombok.*;

/**
 * Modelo de datos que representa un producto del sistema
 * 
 * Esta clase contiene la información básica de un producto que
 * se utiliza para generar emails de confirmación de compra.
 * Se incluye en las notificaciones para mostrar al cliente
 * qué productos ha comprado.
 * 
 * Campos obligatorios:
 * - id: Identificador único del producto
 * - nombre: Nombre del producto
 * - precio: Precio unitario del producto
 * - stock: Cantidad disponible en inventario
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido para deserialización JSON)
@Builder // Implementa el patrón Builder para crear instancias de forma fluida
public class Producto {
    
    /**
     * Identificador único del producto
     * 
     * Este campo es la clave primaria que identifica
     * de manera única a cada producto en el sistema.
     * 
     * Se utiliza para referenciar al producto en otros
     * microservicios y en las bases de datos.
     */
    private String id;
    
    /**
     * Nombre del producto
     * 
     * Este campo contiene el nombre descriptivo del producto
     * y se utiliza para mostrar al cliente qué ha comprado
     * en los emails de confirmación.
     * 
     * Ejemplos: "Laptop HP Pavilion", "Mouse inalámbrico Logitech"
     */
    private String nombre;
    
    /**
     * Precio unitario del producto
     * 
     * Este campo contiene el precio de una unidad del producto
     * en la moneda local. Se utiliza para calcular totales
     * y mostrar precios en los emails de confirmación.
     * 
     * El precio debe ser un valor positivo.
     */
    private double precio;
    
    /**
     * Cantidad disponible en inventario
     * 
     * Este campo indica cuántas unidades del producto
     * están disponibles para la venta. Se utiliza para
     * control de inventario y para informar al cliente
     * sobre la disponibilidad.
     * 
     * El stock debe ser un valor no negativo.
     */
    private int stock;
    
    /**
     * Verifica si el producto tiene datos válidos
     * 
     * Este método valida que el producto tenga la información
     * mínima necesaria para ser incluido en emails.
     * 
     * @return true si el producto tiene datos válidos, false en caso contrario
     */
    public boolean tieneDatosValidos() {
        return id != null && !id.trim().isEmpty() &&
               nombre != null && !nombre.trim().isEmpty() &&
               precio >= 0 && stock >= 0;
    }
    
    /**
     * Verifica si el producto está disponible
     * 
     * Un producto está disponible si tiene stock mayor que cero.
     * 
     * @return true si el producto tiene stock disponible, false en caso contrario
     */
    public boolean estaDisponible() {
        return stock > 0;
    }
    
    /**
     * Verifica si el producto tiene un precio válido
     * 
     * Un precio válido es mayor que cero.
     * 
     * @return true si el precio es válido, false en caso contrario
     */
    public boolean tienePrecioValido() {
        return precio > 0;
    }
}
