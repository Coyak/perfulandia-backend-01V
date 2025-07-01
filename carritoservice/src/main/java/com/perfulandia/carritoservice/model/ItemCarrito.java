package com.perfulandia.carritoservice.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entidad que representa un item (producto) dentro de un carrito de compras
 * 
 * Esta clase mapea la tabla 'items_carrito' en la base de datos y representa
 * un producto específico que ha sido agregado al carrito de un usuario.
 * Cada item contiene información sobre el producto, cantidad y precio.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Entity // Marca esta clase como una entidad JPA que se mapea a una tabla
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido por JPA)
@Builder // Implementa el patrón Builder para crear instancias
public class ItemCarrito {
    
    /**
     * Identificador único del item del carrito
     * Se genera automáticamente usando estrategia de auto-incremento
     */
    @Id // Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación automática del ID
    private Long id;
    
    /**
     * Carrito al que pertenece este item
     * Relación muchos-a-uno: múltiples items pueden pertenecer a un carrito
     */
    @ManyToOne // Define la relación muchos-a-uno
    @JoinColumn(name = "carrito_id") // Especifica la columna de clave foránea en la base de datos
    @JsonBackReference // Evita ciclos infinitos en serialización JSON
    private Carrito carrito;
    
    /**
     * ID del producto que representa este item
     * Este campo establece la relación con el microservicio de productos
     * No es una relación JPA directa, sino una referencia por ID
     */
    @Column(name = "producto_id") // Especifica detalles de la columna en la base de datos
    private Long productoId;
    
    /**
     * Cantidad del producto en el carrito
     * Debe ser un número positivo mayor que cero
     */
    private Integer cantidad;
    
    /**
     * Precio unitario del producto al momento de agregarlo al carrito
     * Se almacena para mantener el precio histórico, ya que el precio
     * del producto puede cambiar después de agregarlo al carrito
     */
    private Double precioUnitario;
    
    /**
     * Método que calcula el subtotal de este item
     * Multiplica la cantidad por el precio unitario
     * 
     * @return El subtotal del item (cantidad * precio unitario)
     */
    public Double getSubtotal() {
        if (cantidad == null || precioUnitario == null) {
            return 0.0;
        }
        return cantidad * precioUnitario;
    }
    
    /**
     * Método que verifica si el item tiene una cantidad válida
     * 
     * @return true si la cantidad es mayor que cero, false en caso contrario
     */
    public boolean tieneCantidadValida() {
        return cantidad != null && cantidad > 0;
    }
    
    /**
     * Método que verifica si el item tiene un precio válido
     * 
     * @return true si el precio unitario es mayor que cero, false en caso contrario
     */
    public boolean tienePrecioValido() {
        return precioUnitario != null && precioUnitario > 0;
    }
} 