package com.perfulandia.emailservice.service;

import com.perfulandia.emailservice.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UsuarioService {

    private final WebClient webClient;

    public UsuarioService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081").build();
    }

    public Usuario getUserById(Long id) {
        return webClient.get()
                .uri("/api/usuarios/{id}", id)
                .retrieve()
                .bodyToMono(Usuario.class)
                .block();
    }
}
