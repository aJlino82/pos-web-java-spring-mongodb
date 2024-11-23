package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.TarefaDTO;
import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.service.TarefaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "criar uma nova tarefa", description = "criar uma nova tarefa")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDTO> create(@Parameter(required = true) @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.create(tarefaDTO));
    }

    @Operation(summary = "ler tarefa", description = "ler tarefa buscando pelo id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDTO> read(@PathVariable String id) {
        return ResponseEntity.ok(tarefaService.read(id));
    }

    @Operation(summary = "atualizar uma tarefa", description = "atualizar uma tarefa buscando por id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDTO> update(@PathVariable String id, @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.update(id, tarefaDTO));
    }

    @Operation(summary = "apagar tarefa", description = "apagar uma tarefa buscando por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        tarefaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa concluída com sucesso");
    }

    @Operation(summary = "listar tarefas ativas", description = "listar tarefas ativas")
    @GetMapping(value = "/ativas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tarefa>> readAtivas() {
        return ResponseEntity.ok(tarefaService.findAtivas());
    }

    @Operation(summary = "listar tarefas concluídas", description = "listar tarefas concluídas")
    @GetMapping(value = "/concluidas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tarefa>> readConcluidas() {
        return ResponseEntity.ok(tarefaService.findConcluida());
    }

    @Operation(summary = "listar tarefas concluídas por usuario", description = "listar tarefas concluídas por usuario")
    @GetMapping(value = "/{id}/concluidas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tarefa>> readConcludasPorUsuario(String id) {
        List<Tarefa> tarefasConcluidas = tarefaService.findByUsuariosAndConcluidaTrue(id);
        return ResponseEntity.status(HttpStatus.OK).body(tarefasConcluidas);
    }

    @Operation(summary = "listar todas as tarefas", description = "listar tarefas com paginação da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna tarefas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Page<TarefaDTO>> readAll(@Parameter(description = "paginação") @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(tarefaService.findAll(pageable));
    }
}