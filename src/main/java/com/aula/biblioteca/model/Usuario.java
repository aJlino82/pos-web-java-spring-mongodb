package com.aula.biblioteca.model;


import com.aula.biblioteca.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private String telefone;

    @JsonIgnore
    @DBRef
    private List<Tarefa> tarefas;

    public static Usuario fromDTO(UsuarioDTO dto) {
        return new Usuario(null, dto.nome(), dto.email(), dto.telefone(), dto.tarefas());
    }
}
