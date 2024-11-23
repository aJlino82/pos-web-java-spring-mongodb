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
@RequestMapping(
        path = "/livros",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    @Operation(summary = "Salvar livro", description = "Adiciona um novo livro na base de dados.")
    @ApiResponse(
            responseCode = "201",
            description = "Livro criado com sucesso.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LivroDTO.class))
    )
    public ResponseEntity<LivroDTO> save(
            @Parameter(description = "Dados do livro", required = true)
            @Valid @RequestBody LivroDTO livroDTO
    ) {
        LivroDTO savedLivro = livroService.save(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivro);
    }

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Lista livros com paginação da base de dados.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorna a lista de livros.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            )
    })
    public ResponseEntity<Page<LivroDTO>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<LivroDTO> livros = livroService.findAll(pageable);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Recupera os detalhes de um livro pelo seu ID.")
    @ApiResponse(
            responseCode = "200",
            description = "Retorna os detalhes do livro.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LivroDTO.class))
    )
    public ResponseEntity<LivroDTO> findById(
            @Parameter(description = "ID do livro", required = true)
            @PathVariable String id
    ) {
        LivroDTO livro = livroService.findById(id);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar livro", description = "Remove um livro da base de dados pelo seu ID.")
    @ApiResponse(
            responseCode = "204",
            description = "Livro apagado com sucesso."
    )
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do livro", required = true)
            @PathVariable String id
    ) {
        LivroDTO livroDTO = livroService.findById(id);
        livroService.delete(livroDTO.id());
        return ResponseEntity.noContent().build();
    }
}
