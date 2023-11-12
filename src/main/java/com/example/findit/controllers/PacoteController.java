package com.example.findit.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.findit.exceptions.RestNotFoundException;
import com.example.findit.models.Pacotes;
import com.example.findit.repository.PacoteRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;


@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pacote")
@RequestMapping("/api/pacotes")
public class PacoteController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PacoteRepository pacoteRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    // @GetMapping
    // @Operation(
    //     summary = "Detalhar Pacotes.",
    //     description = "" 
    // )
    // @ApiResponses({
    //     @ApiResponse(responseCode = "200", description = ""),
    //     @ApiResponse(responseCode = "404", description = "")
    // })
    // public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
    //     Page<Pacotes> pacote = pacoteRepository.findAll(pageable);

    //     return assembler.toModel(pacote.map(Pacotes::toEntityModel));
    // }

    @GetMapping
    public List<Pacotes> index(){
        return pacoteRepository.findAll();
    }

    @PostMapping
    @Operation(
        summary = "Cadastrar Pacote.",
        description = "Endpoint que recebe os parametros de registro de Pacote e cadastra uma." 
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pacote cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados sao invalidos")
    })
    public ResponseEntity<Pacotes> create(@RequestBody @Valid Pacotes pacotes){
        pacoteRepository.save(pacotes);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacotes);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhar Pacote.",
        description = "" 
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = ""),
        @ApiResponse(responseCode = "404", description = "")
    })
    public EntityModel<Pacotes> show(@PathVariable Long id){
        var pacotes = pacoteRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao achar pacote, não encontrada"));
        return pacotes.toEntityModel();
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Pacote.",
        description = "" 
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = ""),
        @ApiResponse(responseCode = "400", description = "")
    })
    public EntityModel<Pacotes> update(@PathVariable Long id, @RequestBody @Valid Pacotes pacotes){
        pacoteRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, pacote não encontrada"));

        pacotes.setId(id);
        pacoteRepository.save(pacotes);

        return pacotes.toEntityModel();
    }    

    @DeleteMapping("{id}")
    @Operation(
        summary = "Deletar Pacote.",
        description = "" 
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = ""),
        @ApiResponse(responseCode = "401", description = "")
    })
    public ResponseEntity<Pacotes> destroy(@PathVariable Long id){
        var pacotes = pacoteRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, pacote não encontrada"));
        pacoteRepository.delete(pacotes);
        return ResponseEntity.noContent().build();
    }

}
