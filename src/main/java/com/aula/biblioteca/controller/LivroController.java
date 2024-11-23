package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.LivroDTO;
import com.aula.biblioteca.service.LivroService;
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
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Operation(summary = "salvar livro", description = "Adiciona um novo livro na base de dados.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> create(@Parameter(required = true) @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(livroService.save(livroDTO));
    }

    @Operation(summary = "ler livro", description = "ler livro buscando pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> read(@PathVariable String id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @Operation(summary = "atualizar um livro", description = "atualizar um livro buscando por id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> update(@Parameter(required = true) @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(livroService.update(livroDTO.id(), livroDTO));
    }

    @Operation(summary = "apagar livro", description = "apagar um livro buscando por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "listar todos os livros", description = "listar livros com paginação da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna livros",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<LivroDTO>> readAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(livroService.findAll(pageable));
    }
}
