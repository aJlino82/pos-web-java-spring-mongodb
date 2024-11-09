package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.LivroDTO;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "livros")
public class Livro {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private Integer qtdPaginas;
    private Float nota;

    @JsonIgnore
    @DBRef
    private List<Autor> autores;

    public static Livro fromDTO(LivroDTO dto) {
        return new Livro(null, dto.titulo(), dto.descricao(), dto.qtdPaginas(), dto.nota(), dto.autores());
    }

}
