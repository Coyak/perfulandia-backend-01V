package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductoService {

    private final WebClient webClient;

    public ProductoService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8082/api/productos").build(); // Ajusta el puerto/URL
    }

    public Producto obtenerProductoPorId(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class)
                .block();
    }
}
