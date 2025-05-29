package com.perfulandia.emailservice.model;

import lombok.Data;

@Data
public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
}
