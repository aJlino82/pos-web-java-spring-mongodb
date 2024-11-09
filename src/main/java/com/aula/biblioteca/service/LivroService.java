package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.LivroDTO;
import com.aula.biblioteca.model.Livro;
import com.aula.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        Livro livro = Livro.fromDTO(livroDTO);
        livro.setId(id);
        return new LivroDTO(livroRepository.save(livro));
    }

    public LivroDTO findByTitulo(String titulo, Pageable pageable) {
        Optional<Livro> optional = livroRepository.findByTitulo(titulo);
        if (optional.isPresent()) {
            Livro livro = optional.get();
        }
        throw new NoSuchElementException("Livro com titulo " + titulo + "não encontrado");
    }

    public Page<LivroDTO> findByNotaAndQtdPaginasGreaterThanEqual(Float nota, Integer qtdPaginas, Pageable pageable) {
        return livroRepository.findByNotaAndQtdPaginasGreaterThanEqual(nota, qtdPaginas, pageable).map(LivroDTO::new);
    }

    public void delete(String id) {
        livroRepository.deleteById(id);
    }

    public LivroDTO findById(String id) {
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if (optionalLivro.isPresent()) {
            return new LivroDTO(optionalLivro.get());
        }
        throw new NoSuchElementException("Livro com id " + id + " não encontrado");
    }

}
