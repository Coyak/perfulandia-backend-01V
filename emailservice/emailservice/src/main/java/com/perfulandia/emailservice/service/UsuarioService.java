package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.Usuario;
import org.springframework.stereotype.Service;

/**
 * Servicio para obtener información de usuarios
 * 
 * Esta clase simula la obtención de datos de usuarios desde
 * el microservicio de usuarios. En una implementación real,
 * este servicio haría llamadas HTTP al microservicio de usuarios
 * para obtener la información necesaria.
 * 
 * Responsabilidades principales:
 * - Obtener información de usuarios por ID
 * - Simular datos de usuarios para testing
 * - Manejo de casos donde el usuario no existe
 * 
 * Nota: Esta es una implementación simulada para el propósito
 * de testing y demostración. En producción, se conectaría
 * al microservicio real de usuarios.
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Service // Marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias y el escaneo automático de componentes
public class UsuarioService {
    
    /**
     * Obtiene un usuario por su ID
     * 
     * Este método simula la búsqueda de un usuario en la base de datos
     * o en el microservicio de usuarios. Para el propósito de testing,
     * retorna datos simulados basados en el ID proporcionado.
     * 
     * @param id ID del usuario a buscar
     * @return Usuario con los datos correspondientes al ID
     * @throws RuntimeException si el usuario no existe
     */
    public Usuario getUserById(Long id) {
        // Simulación de datos de usuarios para testing
        // En una implementación real, esto haría una llamada HTTP
        // al microservicio de usuarios
        
        if (id == null) {
            throw new RuntimeException("ID de usuario no puede ser null");
        }
        
        // Simular diferentes usuarios basados en el ID
        switch (id.intValue()) {
            case 1:
                return Usuario.builder()
                    .id(1L)
                    .nombre("Juan Pérez")
                    .email("juan.perez@email.com")
                    .build();
            case 2:
                return Usuario.builder()
                    .id(2L)
                    .nombre("María García")
                    .email("maria.garcia@email.com")
                    .build();
            case 3:
                return Usuario.builder()
                    .id(3L)
                    .nombre("Carlos López")
                    .email("carlos.lopez@email.com")
                    .build();
            default:
                // Simular que el usuario no existe
                throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
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
    public boolean usuarioExiste(Long id) {
        try {
            getUserById(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    
    /**
     * Obtiene el email de un usuario por su ID
     * 
     * Este método es un helper que obtiene solo el email
     * de un usuario específico.
     * 
     * @param id ID del usuario
     * @return Email del usuario
     * @throws RuntimeException si el usuario no existe
     */
    public String getEmailById(Long id) {
        Usuario usuario = getUserById(id);
        return usuario.getEmail();
    }
    
    /**
     * Obtiene el nombre de un usuario por su ID
     * 
     * Este método es un helper que obtiene solo el nombre
     * de un usuario específico.
     * 
     * @param id ID del usuario
     * @return Nombre del usuario
     * @throws RuntimeException si el usuario no existe
     */
    public String getNombreById(Long id) {
        Usuario usuario = getUserById(id);
        return usuario.getNombre();
    }
}
