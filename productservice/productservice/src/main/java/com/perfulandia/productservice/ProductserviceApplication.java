package com.perfulandia.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Clase principal del microservicio de productos
 * 
 * Esta clase es el punto de entrada de la aplicación Spring Boot
 * para el microservicio de productos. Configura automáticamente
 * todos los componentes necesarios para el manejo del catálogo
 * de productos.
 * 
 * Funcionalidades principales:
 * - Configuración automática de Spring Boot
 * - Escaneo de componentes en el paquete com.perfulandia.productservice
 * - Configuración de RestTemplate para comunicación con otros microservicios
 * - Inicio del servidor web embebido
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@SpringBootApplication // Combina @Configuration, @EnableAutoConfiguration y @ComponentScan para configurar automáticamente la aplicación
public class ProductserviceApplication {

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
		SpringApplication.run(ProductserviceApplication.class, args);
	}
	
	/**
	 * Bean de configuración para RestTemplate
	 * 
	 * Este bean permite hacer llamadas HTTP a otros microservicios,
	 * como el microservicio de usuarios para obtener información
	 * de clientes.
	 * 
	 * @return RestTemplate configurado para uso en la aplicación
	 */
	@Bean // Marca este método como un bean de Spring que se puede inyectar en otros componentes
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
