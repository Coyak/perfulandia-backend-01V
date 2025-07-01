package com.perfulandia.productservice.controller;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import java.util.List;

//Nuevas importaciones DTO conexión al MS usuario
import org.springframework.web.client.RestTemplate;
//Para hacer peticiones HTTP a otros microservicios.

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import com.perfulandia.productservice.model.ProductoModelAssembler;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador REST para el manejo de productos
 * 
 * Esta clase expone endpoints HTTP para gestionar las operaciones
 * relacionadas con productos. Proporciona funcionalidades para
 * crear, consultar, modificar y eliminar productos del catálogo.
 * 
 * Endpoints disponibles:
 * - GET /api/productos - Obtener todos los productos
 * - GET /api/productos/{id} - Obtener producto por ID
 * - POST /api/productos - Crear nuevo producto
 * - PUT /api/productos/{id} - Actualizar producto existente
 * - DELETE /api/productos/{id} - Eliminar producto
 * - GET /api/productos/buscar?nombre={nombre} - Buscar productos por nombre
 * 
 * @author Equipo Perfulandia
 * @version 1.0
 * @since 2025-06-30
 */
@RestController // Marca esta clase como un controlador REST que devuelve respuestas en formato JSON automáticamente
@RequestMapping("/api/productos") // Define la ruta base para todos los endpoints
public class ProductoController {

    /**
     * Servicio que contiene la lógica de negocio para productos
     * Se inyecta automáticamente por Spring usando el constructor
     */
    private final ProductoService servicio;
    private final RestTemplate restTemplate;
    private final ProductoModelAssembler assembler;
    public ProductoController(ProductoService servicio,  RestTemplate restTemplate, ProductoModelAssembler assembler){
        this.servicio = servicio;
        this.restTemplate = restTemplate;
        this.assembler = assembler;
    }

    /**
     * Obtiene todos los productos disponibles
     * 
     * Este endpoint retorna una lista con todos los productos
     * que están disponibles en el catálogo.
     * 
     * @return ResponseEntity<List<Producto>> con la lista de productos
     */
    @GetMapping // Mapea este método a peticiones GET en la ruta base
    public CollectionModel<EntityModel<Producto>> listar(){
        List<EntityModel<Producto>> productos = servicio.listar().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }
    /**
     * Crea un nuevo producto en el catálogo
     * 
     * Este endpoint permite agregar un nuevo producto al sistema.
     * 
     * @param producto Objeto Producto con los datos del nuevo producto
     * @return Producto creado con ID generado automáticamente
     */
    @PostMapping // Mapea este método a peticiones POST en la ruta base
    public Producto guardar(@RequestBody Producto producto) { // Extrae el cuerpo de la petición HTTP y lo convierte a Producto
        return servicio.guardar(producto);
    }
    /**
     * Obtiene un producto específico por su ID
     * 
     * Este endpoint permite consultar los detalles de un producto
     * específico usando su identificador único.
     * 
     * @param id ID del producto a buscar
     * @return ResponseEntity<Producto> con el producto encontrado o error 404 si no existe
     */
    @GetMapping("/{id}") // Mapea este método a peticiones GET en la ruta especificada
    public EntityModel<Producto> buscar(@PathVariable long id){
        Producto producto = servicio.bucarPorId(id);
        return assembler.toModel(producto);
    }
    /**
     * Elimina un producto del catálogo
     * 
     * Este endpoint permite eliminar permanentemente un producto
     * del sistema.
     * 
     * @param id ID del producto a eliminar
     */
    @DeleteMapping("/{id}") // Mapea este método a peticiones DELETE en la ruta especificada
    public void eliminar(@PathVariable long id) { // Extrae el valor de la URL y lo convierte a long
        servicio.eliminar(id);
    }

    /**
     * Obtiene información de un usuario desde el microservicio de usuarios
     * 
     * Este endpoint hace una llamada HTTP al microservicio de usuarios
     * para obtener información de un usuario específico.
     * 
     * @param id ID del usuario a consultar
     * @return ResponseEntity<Usuario> con la información del usuario o error 500 si hay problemas de conexión
     */
    @GetMapping("/usuario/{id}") // Mapea este método a peticiones GET en la ruta especificada
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable long id) { // Extrae el valor de la URL y lo convierte a long
        try {
            Usuario usuario = restTemplate.getForObject("http://localhost:8081/api/usuarios/"+id, Usuario.class);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.ok().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Manejador global de excepciones para RestTemplate
     * 
     * Este método captura las excepciones que ocurren durante
     * las llamadas HTTP a otros microservicios y retorna
     * una respuesta de error apropiada.
     * 
     * @param e Excepción capturada
     * @return ResponseEntity<String> con mensaje de error
     */
    @ExceptionHandler({RestClientException.class, RuntimeException.class}) // Define qué tipos de excepciones maneja este método
    public ResponseEntity<String> handleRestTemplateException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error de conexión: " + e.getMessage());
    }
}
