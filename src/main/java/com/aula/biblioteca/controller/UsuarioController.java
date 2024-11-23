package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.UsuarioDTO;
import com.aula.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "salvar usuario", description = "Adiciona um novo usuario na base de dados.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> create(@Parameter(required = true) @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }

    @Operation(summary = "ler usuario", description = "ler usuario buscando pelo id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> read(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.read(id));
    }

    @Operation(summary = "atualizar um usuario", description = "atualizar um usuario buscando por id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> update(@Parameter(required = true) UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(usuarioDTO.id(), usuarioDTO));
    }

    @Operation(summary = "apagar usuario", description = "apagar um usuario buscando por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "listar todos os usuarios", description = "listar usuarios com paginação da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna usuarios",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioDTO>> readAll(@Parameter(description = "paginação") @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }
}