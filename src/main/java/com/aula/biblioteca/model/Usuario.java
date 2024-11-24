package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @NotNull(message = "Nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "Email não pode ser nulo.")
    @Email(message = "Email inválido.")
    private String email;

    private String telefone;

    @JsonIgnoreProperties("usuarios")
    @DBRef(lazy = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    public static Usuario fromDTO(UsuarioDTO dto) {
        return new Usuario(dto.id(), dto.nome(), dto.email(), dto.telefone(), new ArrayList<>());
    }
}
