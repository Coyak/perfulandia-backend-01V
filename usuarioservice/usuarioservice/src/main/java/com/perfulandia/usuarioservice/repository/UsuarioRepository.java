package com.perfulandia.usuarioservice.repository;

import com.perfulandia.usuarioservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones de base de datos con la entidad Usuario
 * 
 * Esta interfaz extiende JpaRepository para proporcionar métodos CRUD básicos
 * y operaciones de consulta personalizadas para la entidad Usuario.
 * Spring Data JPA implementa automáticamente esta interfaz en tiempo de ejecución.
 * 
 * Métodos heredados de JpaRepository:
 * - save(Usuario): Guarda o actualiza un usuario
 * - findById(Long): Busca un usuario por su ID
 * - findAll(): Obtiene todos los usuarios
 * - delete(Usuario): Elimina un usuario
 * - count(): Cuenta el total de usuarios
 * - existsById(Long): Verifica si existe un usuario con el ID especificado
 * 
 * Nota: En una implementación completa, se agregarían métodos personalizados
 * como findByCorreo, findByRol, findByNombreContainingIgnoreCase, etc.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Repository // Marca esta interfaz como un repositorio de Spring, permitiendo la inyección de dependencias y el manejo de excepciones específicas de persistencia
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Métodos personalizados que se pueden agregar en el futuro:
    // Usuario findByCorreo(String correo);
    // List<Usuario> findByRol(String rol);
    // List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    // boolean existsByCorreo(String correo);
    
}
