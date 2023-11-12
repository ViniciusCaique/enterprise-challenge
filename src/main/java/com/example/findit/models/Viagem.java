package com.example.findit.models;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.findit.controllers.ViagemController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDate dataPartida;

    private LocalDate dataFim;

    @ManyToOne
    private Destino destino;

    @ManyToOne
    private Usuario usuario;

    public EntityModel<Viagem> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(ViagemController.class).show(id)).withSelfRel(),
//            linkTo(methodOn(ViagemController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ViagemController.class).show(id)).withRel("destroy")
        );
    }


}
