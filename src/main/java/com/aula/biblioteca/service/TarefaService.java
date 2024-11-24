package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.TarefaDTO;
import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

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
        Tarefa tarefa = findTarefaById(id);
        tarefa.concluirTarefa();
        tarefaRepository.save(tarefa);
    }

    public Page<TarefaDTO> findAll(Pageable pageable) {
        return tarefaRepository.findAll(pageable).map(TarefaDTO::new);
    }

    public List<TarefaDTO> findAtivas() {
        return tarefaRepository.findByConcluidaFalse()
                .stream()
                .map(TarefaDTO::new)
                .collect(Collectors.toList());
    }

    public List<TarefaDTO> findConcluidas() {
        return tarefaRepository.findByConcluidaTrue()
                .stream()
                .map(TarefaDTO::new)
                .collect(Collectors.toList());
    }

    //Metodo utilitário para buscar uma tarefa pelo ID.
    private Tarefa findTarefaById(String id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa com ID '" + id + "' não encontrada"));
    }
}
