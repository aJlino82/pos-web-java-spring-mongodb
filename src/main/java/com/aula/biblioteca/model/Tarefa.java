package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.TarefaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Document(collection = "tarefas")
public class Tarefa {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    @CreatedDate()
    private LocalDate dataCriacao;
    private String prioridade;
    private Boolean concluida;
    private LocalDate dataConclusao;

    @JsonIgnore
    @DBRef
    List<Usuario> usuarios;

    public static Tarefa fromDTO(TarefaDTO dto) {
        return new Tarefa
                (
                        null,
                        dto.titulo(),
                        dto.descricao(),
                        dto.dataCriacao(),
                        dto.prioridade(),
                        dto.concluida(),
                        dto.dataConclusao(),
                        dto.usuarios()
                );
    }

    public void setConcluida() {
        this.concluida = true;
        this.dataConclusao = LocalDate.now();
    }
}
