package com.perfulandia.usuarioservice.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perfulandia.usuarioservice.controller.UsuarioController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).obtenerUsuarioPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).obtenerTodosLosUsuarios()).withRel("usuarios"));
    }
} 