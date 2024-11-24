package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.TarefaDTO;
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

    @Operation(summary = "Ler tarefa", description = "Busca uma tarefa pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDTO> read(@PathVariable String id) {
        return ResponseEntity.ok(tarefaService.read(id));
    }

    @Operation(summary = "Atualizar uma tarefa", description = "Atualiza uma tarefa existente pelo ID")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDTO> update(@PathVariable String id, @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.update(id, tarefaDTO));
    }

    @Operation(summary = "Apagar tarefa", description = "Remove uma tarefa pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        tarefaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa removida com sucesso");
    }

    @Operation(summary = "Listar tarefas ativas", description = "Lista todas as tarefas ativas")
    @GetMapping(value = "/ativas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TarefaDTO>> readAtivas() {
        return ResponseEntity.ok(tarefaService.findAtivas());
    }

    @Operation(summary = "Listar tarefas concluídas", description = "Lista todas as tarefas concluídas")
    @GetMapping(value = "/concluidas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TarefaDTO>> readConcluidas() {
        return ResponseEntity.ok(tarefaService.findConcluidas());
    }

    @Operation(summary = "Listar todas as tarefas", description = "Lista todas as tarefas com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna as tarefas paginadas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TarefaDTO>> readAll(
            @Parameter(description = "Paginação") @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(tarefaService.findAll(pageable));
    }
}
