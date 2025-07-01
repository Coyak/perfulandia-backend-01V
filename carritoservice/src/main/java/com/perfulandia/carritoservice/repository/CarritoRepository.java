package com.perfulandia.carritoservice.repository;

import com.perfulandia.carritoservice.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para operaciones de base de datos con la entidad Carrito
 * 
 * Esta interfaz extiende JpaRepository para proporcionar métodos CRUD básicos
 * y operaciones de consulta personalizadas para la entidad Carrito.
 * Spring Data JPA implementa automáticamente esta interfaz en tiempo de ejecución.
 * 
 * Métodos heredados de JpaRepository:
 * - save(Carrito): Guarda o actualiza un carrito
 * - findById(Long): Busca un carrito por su ID
 * - findAll(): Obtiene todos los carritos
 * - delete(Carrito): Elimina un carrito
 * - count(): Cuenta el total de carritos
 * 
 * Métodos personalizados:
 * - findByUsuarioIdAndEstado: Busca carritos por usuario y estado
 * 
 * Convenciones de nomenclatura:
 * - findBy + Campo: Busca por un campo específico
 * - findBy + Campo1 + And + Campo2: Busca por múltiples campos con AND
 * - findBy + Campo + OrderBy + CampoOrden: Busca y ordena por un campo
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Repository // Marca esta interfaz como un repositorio de Spring, permitiendo la inyección de dependencias y el manejo de excepciones específicas de persistencia
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
    /**
     * Busca un carrito activo de un usuario específico
     * 
     * Este método utiliza la convención de nomenclatura de Spring Data JPA
     * para generar automáticamente una consulta SQL basada en el nombre del método.
     * 
     * La consulta generada será equivalente a:
     * SELECT * FROM carritos WHERE usuario_id = ? AND estado = ?
     * 
     * @param usuarioId ID del usuario del cual se quiere obtener el carrito
     * @param estado Estado del carrito (ej: "ACTIVO", "COMPLETADO")
     * @return Carrito que coincida con los criterios o null si no existe
     */
    Carrito findByUsuarioIdAndEstado(Long usuarioId, String estado);
    
    /**
     * Busca todos los carritos de un usuario específico
     * 
     * Este método retorna todos los carritos (activos, completados, cancelados)
     * que pertenecen a un usuario específico.
     * 
     * @param usuarioId ID del usuario del cual se quieren obtener los carritos
     * @return Lista de carritos del usuario (puede estar vacía)
     */
    List<Carrito> findByUsuarioId(Long usuarioId);
    
    /**
     * Busca carritos por estado
     * 
     * Este método es útil para obtener todos los carritos que están
     * en un estado específico (ej: todos los carritos activos).
     * 
     * @param estado Estado de los carritos a buscar
     * @return Lista de carritos con el estado especificado
     */
    List<Carrito> findByEstado(String estado);
    
    /**
     * Cuenta el número de carritos activos de un usuario
     * 
     * Este método es útil para validar que un usuario no tenga
     * múltiples carritos activos simultáneamente.
     * 
     * @param usuarioId ID del usuario
     * @return Número de carritos activos del usuario
     */
    long countByUsuarioIdAndEstado(Long usuarioId, String estado);
} 