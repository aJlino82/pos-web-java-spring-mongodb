package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.model.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO
        (
                String id,
                @NotBlank(message = " não pode ser vazio") String nome,
                @NotBlank String email,
                @NotBlank String telefone,
                List<Tarefa> tarefas
        ) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getTarefas());
    }
}

