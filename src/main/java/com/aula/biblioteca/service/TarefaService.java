package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.TarefaDTO;
import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.repository.TarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaDTO create(TarefaDTO tarefaDTO) {
        Tarefa tarefa = Tarefa.fromDTO(tarefaDTO);
        return new TarefaDTO(tarefaRepository.save(tarefa));
    }

    public TarefaDTO read(String id) {
        Tarefa tarefa = findTarefaById(id);
        return new TarefaDTO(tarefa);
    }

    public TarefaDTO update(String id, TarefaDTO tarefaDTO) {
        Tarefa tarefa = findTarefaById(id);
        tarefa.fromDTO(tarefaDTO);
        return new TarefaDTO(tarefaRepository.save(tarefa));
    }

    public void delete(String id) {
        findTarefaById(id);
        tarefaRepository.deleteById(id);
    }

    public Page<TarefaDTO> findAll(Pageable pageable) {
        return tarefaRepository.findAll(pageable).map(TarefaDTO::new);
    }

    // Metodo utilitário privado para reduzir repetição de código.
    private Tarefa findTarefaById(String id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Autor com ID '" + id + "' não encontrado"));
    }
}
