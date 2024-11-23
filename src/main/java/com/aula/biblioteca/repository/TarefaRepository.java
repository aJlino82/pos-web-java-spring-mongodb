package com.aula.biblioteca.repository;

import com.aula.biblioteca.model.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {

    List<Tarefa> findByTituloContaining(String titulo);

    @Query("{'dataCriacao': {'$gte': ?0, '$lte': ?1}}")
    List<Tarefa> findByDataCriacaoBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Tarefa> findByConcluidaFalse();

    List<Tarefa> findByConcluidaTrue();

    List<Tarefa> findByUsuariosAndConcluidaTrue(String id);

}
