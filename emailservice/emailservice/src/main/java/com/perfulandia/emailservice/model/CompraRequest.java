package com.perfulandia.emailservice.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Modelo de datos para solicitudes de envío de email de confirmación de compra
 * 
 * Esta clase representa la estructura de datos necesaria para
 * enviar un email de confirmación de compra que incluye detalles
 * del pedido, productos comprados e información del cliente.
 * Se utiliza en el endpoint /api/email/compra para procesar
 * solicitudes de envío de emails de confirmación.
 * 
 * Campos obligatorios:
 * - usuario: Información del cliente que realizó la compra
 * - productos: Lista de productos comprados
 * - numeroPedido: Número único del pedido
 * - fechaCompra: Fecha y hora de la compra
 * - total: Monto total de la compra
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructor con todos los parámetros
@NoArgsConstructor // Genera constructor sin parámetros (requerido para deserialización JSON)
@Builder // Implementa el patrón Builder para crear instancias de forma fluida
public class CompraRequest {
    
    /**
     * Información del usuario que realizó la compra
     * 
     * Este campo contiene todos los datos del cliente necesarios
     * para personalizar el email de confirmación.
     * 
     * Es un campo obligatorio que debe contener datos válidos.
     */
    private Usuario usuario;
    
    /**
     * Lista de productos comprados
     * 
     * Este campo contiene todos los productos que el cliente
     * ha comprado en esta transacción.
     * 
     * Es un campo obligatorio que no puede estar vacío.
     */
    private List<Producto> productos;
    
    /**
     * Número único del pedido
     * 
     * Este campo contiene el identificador único del pedido
     * que se utiliza para seguimiento y referencia.
     * 
     * Es un campo obligatorio que debe ser único.
     */
    private String numeroPedido;
    
    /**
     * Fecha y hora de la compra
     * 
     * Este campo registra cuándo se realizó la compra
     * y se incluye en el email de confirmación.
     * 
     * Es un campo obligatorio que debe ser una fecha válida.
     */
    private LocalDateTime fechaCompra;
    
    /**
     * Monto total de la compra
     * 
     * Este campo contiene la suma total de todos los productos
     * comprados, incluyendo impuestos y descuentos si aplican.
     * 
     * Es un campo obligatorio que debe ser un valor positivo.
     */
    private Double total;
    
    /**
     * Verifica si la solicitud tiene datos válidos
     * 
     * Este método valida que todos los campos obligatorios
     * estén presentes y contengan datos válidos.
     * 
     * @return true si la solicitud tiene datos válidos, false en caso contrario
     */
    public boolean tieneDatosValidos() {
        return usuario != null &&
               productos != null && !productos.isEmpty() &&
               numeroPedido != null && !numeroPedido.trim().isEmpty() &&
               fechaCompra != null &&
               total != null && total > 0;
    }
    
    /**
     * Verifica si la lista de productos es válida
     * 
     * Este método valida que todos los productos en la lista
     * tengan datos válidos.
     * 
     * @return true si todos los productos son válidos, false en caso contrario
     */
    public boolean tieneProductosValidos() {
        if (productos == null || productos.isEmpty()) {
            return false;
        }
        
        return productos.stream().allMatch(Producto::tieneDatosValidos);
    }
    
    /**
     * Calcula el total de la compra basado en los productos
     * 
     * Este método suma los precios de todos los productos
     * para verificar que coincida con el total proporcionado.
     * 
     * @return El total calculado de la compra
     */
    public Double calcularTotal() {
        if (productos == null || productos.isEmpty()) {
            return 0.0;
        }
        
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}
