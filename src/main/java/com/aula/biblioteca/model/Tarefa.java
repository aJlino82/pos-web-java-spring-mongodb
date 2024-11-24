package com.aula.biblioteca.model;

import com.aula.biblioteca.dto.TarefaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "tarefas")
public class Tarefa {

    @Id
    private String id;

    @NotNull(message = "O título da tarefa não pode ser nulo.")
    private String titulo;

    private String descricao;

    @CreatedDate
    private LocalDate dataCriacao;

    private String prioridade;

    private Boolean concluida = false;

    public static Tarefa fromDTO(TarefaDTO dto) {
        return new Tarefa(
                null,
                dto.titulo(),
                dto.descricao(),
                null, // Será inicializado automaticamente
                dto.prioridade(),
                dto.concluida() != null ? dto.concluida() : false
        );
    }

    public void concluirTarefa() {
        this.concluida = true;
    }

    public void reativarTarefa() {
        this.concluida = false;
    }
}
