package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el manejo de usuarios
 * 
 * Esta clase expone endpoints HTTP para gestionar las operaciones
 * relacionadas con usuarios del sistema. Proporciona funcionalidades
 * para crear, consultar, modificar y eliminar usuarios.
 * 
 * Endpoints disponibles:
 * - GET /api/usuarios - Obtener todos los usuarios
 * - GET /api/usuarios/{id} - Obtener usuario por ID
 * - POST /api/usuarios - Crear nuevo usuario
 * - PUT /api/usuarios/{id} - Actualizar usuario existente
 * - DELETE /api/usuarios/{id} - Eliminar usuario
 * - GET /api/usuarios/buscar/{nombre} - Buscar usuarios por nombre
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@RestController // Marca esta clase como un controlador REST que devuelve respuestas en formato JSON automáticamente
@RequestMapping("/api/usuarios") // Define la ruta base para todos los endpoints
@RequiredArgsConstructor // Genera un constructor con los campos final (inyección de dependencias)
public class UsuarioController {
    
    /**
     * Servicio que contiene la lógica de negocio para usuarios
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final UsuarioService usuarioService;
    
    /**
     * Obtiene todos los usuarios registrados
     * 
     * Este endpoint retorna una lista con todos los usuarios
     * que están registrados en el sistema.
     * 
     * @return ResponseEntity<List<Usuario>> con la lista de usuarios
     */
    @GetMapping // Mapea este método a peticiones GET en la ruta base
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        try {
            // Delega la búsqueda al servicio
            List<Usuario> usuarios = usuarioService.listar();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Obtiene un usuario específico por su ID
     * 
     * Este endpoint permite consultar los detalles de un usuario
     * específico usando su identificador único.
     * 
     * @param id ID del usuario a buscar
     * @return ResponseEntity<Usuario> con el usuario encontrado o error 404 si no existe
     */
    @GetMapping("/{id}") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) { // Extrae el valor de la URL y lo convierte a Long
        try {
            // Delega la búsqueda al servicio
            Usuario usuario = usuarioService.buscar(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Crea un nuevo usuario en el sistema
     * 
     * Este endpoint permite registrar un nuevo usuario.
     * Incluye validaciones básicas para asegurar que los datos sean válidos.
     * 
     * @param usuario Objeto Usuario con los datos del nuevo usuario
     * @return ResponseEntity<Usuario> con el usuario creado o error 400 si los datos son inválidos
     */
    @PostMapping // Mapea este método a peticiones POST en la ruta base
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) { // Extrae el cuerpo de la petición HTTP y lo convierte a Usuario
        try {
            // Validar que el usuario tenga datos válidos
            if (usuario == null || usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Delegar la creación al servicio
            Usuario usuarioCreado = usuarioService.guardar(usuario);
            return ResponseEntity.ok(usuarioCreado);
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Actualiza un usuario existente
     * 
     * Este endpoint permite modificar los datos de un usuario
     * existente en el sistema.
     * 
     * @param id ID del usuario a actualizar
     * @param usuario Objeto Usuario con los nuevos datos
     * @return ResponseEntity<Usuario> con el usuario actualizado o error 404 si no existe
     */
    @PutMapping("/{id}") // Mapea este método a peticiones PUT en la ruta especificada
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id, // Extrae el valor de la URL y lo convierte a Long
            @RequestBody Usuario usuario) { // Extrae el cuerpo de la petición HTTP y lo convierte a Usuario
        try {
            // Validar que el usuario tenga datos válidos
            if (usuario == null || usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Verificar que el usuario existe
            Usuario usuarioExistente = usuarioService.buscar(id);
            if (usuarioExistente == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Actualizar los datos
            usuario.setId(id);
            Usuario usuarioActualizado = usuarioService.guardar(usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Elimina un usuario del sistema
     * 
     * Este endpoint permite eliminar permanentemente un usuario
     * del sistema.
     * 
     * @param id ID del usuario a eliminar
     * @return ResponseEntity<Void> con estado HTTP 204 si se elimina exitosamente
     */
    @DeleteMapping("/{id}") // Mapea este método a peticiones DELETE en la ruta especificada
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) { // Extrae el valor de la URL y lo convierte a Long
        try {
            // Verificar que el usuario existe
            Usuario usuario = usuarioService.buscar(id);
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Delegar la eliminación al servicio
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Busca usuarios por nombre
     * 
     * Este endpoint permite buscar usuarios que contengan
     * el nombre especificado en su información.
     * 
     * @param nombre Nombre o parte del nombre del usuario a buscar
     * @return ResponseEntity<List<Usuario>> con la lista de usuarios encontrados
     */
    @GetMapping("/buscar/{nombre}") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<List<Usuario>> buscarUsuariosPorNombre(@PathVariable String nombre) { // Extrae el valor de la URL
        try {
            // Validar que el nombre no esté vacío
            if (nombre == null || nombre.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Implementación básica usando listar y filtrado en memoria
            List<Usuario> todosUsuarios = usuarioService.listar();
            List<Usuario> usuariosFiltrados = todosUsuarios.stream()
                    .filter(u -> u.getNombre() != null && 
                               u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .toList();
            return ResponseEntity.ok(usuariosFiltrados);
        } catch (Exception e) {
            // Manejar errores y retornar HTTP 500
            return ResponseEntity.internalServerError().build();
        }
    }
}
