package com.perfulandia.emailservice.model;

import lombok.Data;

@Data
public class CompraRequest {
    private Long userId;
    private Long productoId;
}
