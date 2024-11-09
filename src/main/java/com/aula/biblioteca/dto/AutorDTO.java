package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.model.Livro;

import java.util.List;

public record AutorDTO
        (
                String id,
                String nome,
                String email,
                List<Livro> livros
        ) {
    public AutorDTO(Autor autor) {
        this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getLivros());
    }
}
