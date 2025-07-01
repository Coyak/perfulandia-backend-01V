package com.perfulandia.usuarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del microservicio de usuarios
 * 
 * Esta clase es el punto de entrada de la aplicación Spring Boot
 * para el microservicio de usuarios. Configura automáticamente
 * todos los componentes necesarios para el manejo de usuarios
 * del sistema.
 * 
 * Funcionalidades principales:
 * - Configuración automática de Spring Boot
 * - Escaneo de componentes en el paquete com.perfulandia.usuarioservice
 * - Configuración automática de JPA y la base de datos
 * - Inicio del servidor web embebido
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@SpringBootApplication // Combina @Configuration, @EnableAutoConfiguration y @ComponentScan para configurar automáticamente la aplicación
public class UsuarioserviceApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot
	 * 
	 * Este método:
	 * 1. Crea el contexto de aplicación de Spring
	 * 2. Registra todos los beans configurados
	 * 3. Configura automáticamente JPA y la base de datos
	 * 4. Inicia el servidor web embebido (Tomcat por defecto)
	 * 5. Hace que la aplicación esté lista para recibir peticiones HTTP
	 * 
	 * @param args Argumentos de línea de comandos (no utilizados en este caso)
	 */
	public static void main(String[] args) {
		// Inicia la aplicación Spring Boot
		// SpringApplication.run() configura y ejecuta la aplicación
		SpringApplication.run(UsuarioserviceApplication.class, args);
	}

}
