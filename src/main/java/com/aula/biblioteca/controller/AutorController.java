package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.AutorDTO;
import com.aula.biblioteca.service.AutorService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    @Operation(summary = "listar todos os autores", description = "listar autores com paginação da base de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "retorna autores", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AutorDTO>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(autorService.findAll(pageable));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDTO> update(@Parameter(required = true) @RequestBody AutorDTO autorDTO) {
        return ResponseEntity.ok(autorService.update(autorDTO.id(), autorDTO));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDTO> save(@Parameter(required = true) @RequestBody AutorDTO autorDTO) {
        return ResponseEntity.ok(autorService.save(autorDTO));
    }
}
