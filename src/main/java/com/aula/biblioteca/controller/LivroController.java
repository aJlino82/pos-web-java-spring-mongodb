package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.LivroDTO;
import com.aula.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/livros",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {

    private final LivroService livroService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> save(@Parameter(required = true) @Valid @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livroDTO));
    }


    @Operation(summary = "listar todos os livros", description = "listar livros com paginação da base de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "retorna autores", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),})
    @GetMapping
    public ResponseEntity<Page<LivroDTO>> gatAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(livroService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        LivroDTO livroDTO = livroService.findById(id);
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
