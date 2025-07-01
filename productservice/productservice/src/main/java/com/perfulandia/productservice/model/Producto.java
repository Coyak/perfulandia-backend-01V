package com.perfulandia.productservice.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un producto en el sistema
 * 
 * Esta clase mapea la tabla 'productos' en la base de datos y representa
 * un producto del catálogo que puede ser comprado por los usuarios.
 * Cada producto tiene información básica como nombre, descripción,
 * precio y stock disponible.
 * 
 * Campos obligatorios:
 * - nombre: Nombre del producto
 * - descripcion: Descripción detallada del producto
 * - precio: Precio unitario del producto
 * - stock: Cantidad disponible en inventario
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Entity // Marca esta clase como una entidad JPA que se mapea a una tabla
@Table(name = "productos") // Especifica el nombre de la tabla en la base de datos
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido por JPA)
@Builder // Implementa el patrón Builder para crear instancias
public class Producto {
    
    /**
     * Identificador único del producto
     * Se genera automáticamente usando estrategia de auto-incremento
     */
    @Id // Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación automática del ID
    private Long id;
    
    /**
     * Nombre del producto
     * 
     * Este campo contiene el nombre descriptivo del producto
     * que se muestra a los usuarios en el catálogo.
     * 
     * Es un campo obligatorio que no puede estar vacío.
     */
    @Column(nullable = false) // Especifica que la columna no puede ser null
    private String nombre;
    
    /**
     * Descripción detallada del producto
     * 
     * Este campo contiene información detallada sobre las
     * características y especificaciones del producto.
     * 
     * Es un campo obligatorio que no puede estar vacío.
     */
    @Column(nullable = false) // Especifica que la columna no puede ser null
    private String descripcion;
    
    /**
     * Precio unitario del producto
     * 
     * Este campo contiene el precio de una unidad del producto
     * en la moneda local. Se utiliza para cálculos de totales
     * y para mostrar precios a los usuarios.
     * 
     * El precio debe ser un valor positivo.
     */
    @Column(nullable = false) // Especifica que la columna no puede ser null
    private Double precio;
    
    /**
     * Cantidad disponible en inventario
     * 
     * Este campo indica cuántas unidades del producto
     * están disponibles para la venta. Se utiliza para
     * control de inventario y para informar a los usuarios
     * sobre la disponibilidad.
     * 
     * El stock debe ser un valor no negativo.
     */
    @Column(nullable = false) // Especifica que la columna no puede ser null
    private Integer stock;
    
    /**
     * Verifica si el producto tiene datos válidos
     * 
     * Este método valida que el producto tenga la información
     * mínima necesaria para ser incluido en el catálogo.
     * 
     * @return true si el producto tiene datos válidos, false en caso contrario
     */
    public boolean tieneDatosValidos() {
        return nombre != null && !nombre.trim().isEmpty() &&
               descripcion != null && !descripcion.trim().isEmpty() &&
               precio != null && precio > 0 &&
               stock != null && stock >= 0;
    }
    
    /**
     * Verifica si el producto está disponible
     * 
     * Un producto está disponible si tiene stock mayor que cero.
     * 
     * @return true si el producto tiene stock disponible, false en caso contrario
     */
    public boolean estaDisponible() {
        return stock != null && stock > 0;
    }
    
    /**
     * Verifica si el producto tiene un precio válido
     * 
     * Un precio válido es mayor que cero.
     * 
     * @return true si el precio es válido, false en caso contrario
     */
    public boolean tienePrecioValido() {
        return precio != null && precio > 0;
    }
    
    /**
     * Reduce el stock del producto
     * 
     * Este método se utiliza cuando se vende una unidad
     * del producto para actualizar el inventario.
     * 
     * @param cantidad Cantidad a reducir del stock
     * @return true si se pudo reducir el stock, false si no hay suficiente
     */
    public boolean reducirStock(int cantidad) {
        if (stock != null && stock >= cantidad) {
            stock -= cantidad;
            return true;
        }
        return false;
    }
    
    /**
     * Aumenta el stock del producto
     * 
     * Este método se utiliza para reponer inventario
     * o cuando se devuelven productos.
     * 
     * @param cantidad Cantidad a agregar al stock
     */
    public void aumentarStock(int cantidad) {
        if (stock == null) {
            stock = 0;
        }
        stock += cantidad;
    }
}

