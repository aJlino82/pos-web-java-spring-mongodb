package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.AutorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "autores")
public class Autor {
    @Id
    private String id;
    private String nome;
    private String email;
    
    @JsonIgnore
    @DBRef
    private List<Livro> livros;

    public static Autor fromDTO(AutorDTO autorDTO) {
        return new Autor(null, autorDTO.nome(), autorDTO.email(), autorDTO.livros());
    }
}