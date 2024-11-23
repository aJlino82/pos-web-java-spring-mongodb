package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.model.Livro;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AutorDTO
        (
                String id,
                @NotBlank(message = " não pode ser vazio") String nome,
                @NotBlank String email,
                List<Livro> livros
        ) {
    public AutorDTO(Autor autor) {
        this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getLivros());
    }
}
