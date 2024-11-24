package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Tarefa;

public record TarefaDTO(String id, String titulo, String descricao, String prioridade, Boolean concluida) {

    public TarefaDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getConcluida());
    }
}
