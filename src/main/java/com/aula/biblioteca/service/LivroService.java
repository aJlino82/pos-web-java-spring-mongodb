package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.LivroDTO;
import com.aula.biblioteca.model.Livro;
import com.aula.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroDTO save(LivroDTO livroDTO) {
        Livro livro = Livro.fromDTO(livroDTO);
        return new LivroDTO(livroRepository.save(livro));
    }

    public Page<LivroDTO> findAll(Pageable pageable) {
        return livroRepository.findAll(pageable).map(LivroDTO::new);
    }

    public LivroDTO update(String id, LivroDTO livroDTO) {
        Livro livro = findLivroById(id); // Garante que o livro existe antes de atualizar
        livro.fromDTO(livroDTO);
        return new LivroDTO(livroRepository.save(livro));
    }

    public LivroDTO findByTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new NoSuchElementException("Livro com título '" + titulo + "' não encontrado"));
        return new LivroDTO(livro);
    }

    public Page<LivroDTO> findByNotaAndQtdPaginasGreaterThanEqual(Float nota, Integer qtdPaginas, Pageable pageable) {
        return livroRepository.findByNotaAndQtdPaginasGreaterThanEqual(nota, qtdPaginas, pageable).map(LivroDTO::new);
    }

    public void delete(String id) {
        findLivroById(id); // Garante que o livro existe antes de deletar
        livroRepository.deleteById(id);
    }

    public LivroDTO findById(String id) {
        Livro livro = findLivroById(id);
        return new LivroDTO(livro);
    }

    // Métodos utilitários privados para reduzir repetição de código
    private Livro findLivroById(String id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro com ID '" + id + "' não encontrado"));
    }
}
