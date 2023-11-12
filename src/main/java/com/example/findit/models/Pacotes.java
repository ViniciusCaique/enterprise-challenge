package com.example.findit.models;


import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.findit.controllers.PacoteController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Pacotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String tipo;

    @NotBlank @Size(min = 5, max = 255)
    private String descricao;

    @ManyToOne
    private Agencia agencia;

    @ManyToOne
    private Destino destino;

    public EntityModel<Pacotes> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(PacoteController.class).show(id)).withSelfRel(),
//            linkTo(methodOn(PacoteController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(PacoteController.class).show(id)).withRel("destroy")
        );
    }

}
