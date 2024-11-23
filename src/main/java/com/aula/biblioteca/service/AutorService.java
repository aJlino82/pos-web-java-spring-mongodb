package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.AutorDTO;
import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorDTO create(AutorDTO autorDTO) {
        Autor autor = Autor.fromDTO(autorDTO);
        return new AutorDTO(autorRepository.save(autor));
    }

    public AutorDTO read(String id) {
        Autor autor = findAutorById(id);
        return new AutorDTO(autor);
    }

    public AutorDTO update(String id, AutorDTO autorDTO) {
        Autor autor = findAutorById(id); // Verifica se o autor existe antes de atualizar.
        autor.fromDTO(autorDTO); // Metodo que atualiza os campos do autor.
        return new AutorDTO(autorRepository.save(autor));
    }

    public void delete(String id) {
        findAutorById(id); // Garante que o autor existe antes de deletar.
        autorRepository.deleteById(id);
    }

    public Page<AutorDTO> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable).map(AutorDTO::new);
    }

    // Metodo utilitário privado para reduzir repetição de código.
    private Autor findAutorById(String id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Autor com ID '" + id + "' não encontrado"));
    }
}
