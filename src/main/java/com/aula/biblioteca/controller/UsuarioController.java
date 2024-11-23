package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.UsuarioDTO;
import com.aula.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Salvar usuario", description = "Adiciona um novo usuario na base de dados.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> create(UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }

    @Operation(summary = "ler usuario", description = "ler usuario buscando pelo id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> read(String id) {
        return ResponseEntity.ok(usuarioService.read(id));
    }

    @Operation(summary = "atualizar um usuario", description = "atualizar um usuario buscando por id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> update(String id, UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
    }

    @Operation(summary = "apagar usuario", description = "apagar um usuario buscando por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}