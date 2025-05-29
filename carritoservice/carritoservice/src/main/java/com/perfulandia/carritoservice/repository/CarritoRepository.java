package com.perfulandia.carritoservice.repository;

import com.perfulandia.carritoservice.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUsuarioIdAndEstado(Long usuarioId, String estado);
} 