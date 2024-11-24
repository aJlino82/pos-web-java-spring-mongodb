package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Usuario;

import java.util.List;

public record UsuarioDTO(String id, String nome, String email, String telefone, List<TarefaDTO> tarefas) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getTarefas().stream().map(TarefaDTO::new).toList());
    }
}

