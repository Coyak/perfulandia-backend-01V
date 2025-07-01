package com.perfulandia.carritoservice.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perfulandia.carritoservice.controller.CarritoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {
    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoController.class).obtenerCarritoActivo(carrito.getUsuarioId())).withSelfRel(),
                linkTo(methodOn(CarritoController.class).agregarItem(carrito.getId(), null, null, null)).withRel("agregarItem"),
                linkTo(methodOn(CarritoController.class).obtenerItemsCarrito(carrito.getId())).withRel("items"));
    }
} 