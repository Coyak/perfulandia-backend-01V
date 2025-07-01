package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para el manejo de usuarios
 * 
 * Esta clase implementa todas las operaciones relacionadas con usuarios,
 * incluyendo creación, consulta, modificación y eliminación de usuarios
 * del sistema. Actúa como intermediario entre el controlador y el
 * repositorio, aplicando las reglas de negocio necesarias.
 * 
 * Responsabilidades principales:
 * - Crear nuevos usuarios en el sistema
 * - Buscar usuarios por diferentes criterios
 * - Actualizar información de usuarios existentes
 * - Eliminar usuarios del sistema
 * - Aplicar reglas de negocio y validaciones
 * - Gestionar roles y permisos de usuarios
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
@RequiredArgsConstructor // Genera un constructor con los campos final para la inyección de dependencias
public class UsuarioService {
    
    /**
     * Repositorio para operaciones de base de datos con usuarios
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final UsuarioRepository repo;
    
    /**
     * Obtiene todos los usuarios registrados
     * 
     * Este método retorna una lista con todos los usuarios
     * que están registrados en el sistema.
     * 
     * @return Lista de todos los usuarios registrados
     */
    public List<Usuario> listar() {
        return repo.findAll();
    }
    
    /**
     * Guarda un nuevo usuario en el sistema
     * 
     * Este método crea un nuevo usuario en la base de datos.
     * Si el usuario ya tiene un ID, se actualiza en lugar de crear.
     * 
     * @param usuario Usuario a guardar
     * @return Usuario guardado con ID generado automáticamente
     */
    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }
    
    /**
     * Busca un usuario por su ID
     * 
     * Este método busca un usuario específico usando su
     * identificador único.
     * 
     * @param id ID del usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscar(long id) {
        Optional<Usuario> usuario = repo.findById(id);
        return usuario.orElse(null);
    }
    
    /**
     * Elimina un usuario del sistema
     * 
     * Este método elimina permanentemente un usuario
     * de la base de datos.
     * 
     * @param id ID del usuario a eliminar
     */
    public void eliminar(long id) {
        repo.deleteById(id);
    }
    
    /**
     * Verifica si un usuario existe
     * 
     * Este método verifica si un usuario con el ID especificado
     * existe en el sistema.
     * 
     * @param id ID del usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    public boolean usuarioExiste(long id) {
        return repo.existsById(id);
    }
    
    /**
     * Cuenta el total de usuarios en el sistema
     * 
     * @return Número total de usuarios registrados
     */
    public long contarUsuarios() {
        return repo.count();
    }
    
    /**
     * Busca usuarios por rol
     * 
     * Este método busca usuarios que tengan el rol
     * especificado.
     * 
     * @param rol Rol de los usuarios a buscar
     * @return Lista de usuarios con el rol especificado
     */
    public List<Usuario> buscarPorRol(String rol) {
        // Implementación básica usando findAll y filtrado en memoria
        // En una implementación real, se agregaría un método al repositorio
        return repo.findAll().stream()
                .filter(u -> rol.equals(u.getRol()))
                .toList();
    }
    
    /**
     * Busca usuarios por correo electrónico
     * 
     * Este método busca un usuario específico por su
     * dirección de correo electrónico.
     * 
     * @param correo Dirección de correo electrónico a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscarPorCorreo(String correo) {
        // Implementación básica usando findAll y filtrado en memoria
        // En una implementación real, se agregaría un método al repositorio
        return repo.findAll().stream()
                .filter(u -> correo.equals(u.getCorreo()))
                .findFirst()
                .orElse(null);
    }
}
