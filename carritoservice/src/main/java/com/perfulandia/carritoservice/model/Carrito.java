package com.perfulandia.carritoservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un carrito de compras en el sistema
 * 
 * Esta clase mapea la tabla 'carritos' en la base de datos y representa
 * el carrito de compras de un usuario específico. Un carrito puede contener
 * múltiples items (productos) y tiene un estado que indica si está activo
 * o completado.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Entity // Marca esta clase como una entidad JPA que se mapea a una tabla
@Table(name = "carritos") // Especifica el nombre de la tabla en la base de datos
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido por JPA)
@Builder // Implementa el patrón Builder para crear instancias
public class Carrito {
    
    /**
     * Identificador único del carrito
     * Se genera automáticamente usando estrategia de auto-incremento
     */
    @Id // Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación automática del ID
    private Long id;
    
    /**
     * ID del usuario propietario del carrito
     * Este campo establece la relación con el microservicio de usuarios
     */
    @Column(name = "usuario_id", nullable = false) // Especifica detalles de la columna en la base de datos
    private Long usuarioId;
    
    /**
     * Estado actual del carrito
     * Valores posibles: "ACTIVO", "COMPLETADO", "CANCELADO"
     * Por defecto se establece como "ACTIVO" cuando se crea un nuevo carrito
     */
    @Column(nullable = false) // Especifica que la columna no puede ser null
    private String estado;
    
    /**
     * Fecha y hora de creación del carrito
     * Se establece automáticamente cuando se crea el carrito
     */
    @Column(name = "fecha_creacion", nullable = false) // Especifica detalles de la columna en la base de datos
    private LocalDateTime fechaCreacion;
    
    /**
     * Lista de items (productos) en el carrito
     * Relación uno-a-muchos: un carrito puede tener múltiples items
     */
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Define la relación uno-a-muchos
    private List<ItemCarrito> items;
    
    /**
     * Método que calcula el total del carrito sumando todos los items
     * 
     * @return El precio total del carrito, o 0.0 si no hay items
     */
    public Double getTotal() {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        return items.stream()
                .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
    }
    
    /**
     * Método que verifica si el carrito está activo
     * 
     * @return true si el carrito está activo, false en caso contrario
     */
    public boolean isActivo() {
        return "ACTIVO".equals(estado);
    }
    
    /**
     * Método que verifica si el carrito está completado
     * 
     * @return true si el carrito está completado, false en caso contrario
     */
    public boolean isCompletado() {
        return "COMPLETADO".equals(estado);
    }
} 