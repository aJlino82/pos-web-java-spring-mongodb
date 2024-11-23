package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.model.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record TarefaDTO
        (
                String id,
                @NotBlank(message = " não pode ser vazio") String titulo,
                @NotBlank String descricao,
                @NotBlank LocalDate dataCriacao,
                @NotBlank String prioridade,
                @NotBlank Boolean concluida,
                @NotBlank LocalDate dataConclusao,
                List<Usuario> usuarios
        ) {
    public TarefaDTO(Tarefa tarefa) {
        this
                (
                        tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getDataCriacao(),
                        tarefa.getPrioridade(),
                        tarefa.getConcluida(),
                        tarefa.getDataConclusao(),
                        tarefa.getUsuarios()
                );
    }
}
