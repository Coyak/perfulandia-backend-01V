package com.perfulandia.carritoservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuración JPA para el microservicio de carrito de compras
 * 
 * Esta clase contiene la configuración específica para JPA (Java Persistence API)
 * y Spring Data JPA. Se separó de la clase principal para permitir que los tests
 * no carguen la configuración JPA, evitando problemas de contexto.
 * 
 * Esta clase se encarga de:
 * 1. Habilitar Spring Data JPA para el paquete de repositorios
 * 2. Configurar automáticamente los repositorios JPA
 * 3. Permitir que los tests funcionen sin cargar JPA innecesariamente
 * 
 * Beneficios de esta separación:
 * - Los tests de controlador pueden usar @WebMvcTest sin problemas
 * - Mejor rendimiento en tests al no cargar componentes innecesarios
 * - Configuración más limpia y mantenible
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@Configuration // Marca esta clase como una clase de configuración de Spring
@Profile("!test") // Solo se activa cuando NO está el perfil "test"
@EnableJpaRepositories(basePackages = "com.perfulandia.carritoservice.repository") // Habilita Spring Data JPA y especifica dónde buscar los repositorios JPA
public class CarritoJpaConfig {
    
} 