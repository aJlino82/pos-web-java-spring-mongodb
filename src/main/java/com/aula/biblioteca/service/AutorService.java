package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.AutorDTO;
import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorDTO create(AutorDTO autorDTO) {
        return new AutorDTO(autorRepository.save(Autor.fromDTO(autorDTO)));
    }

    public AutorDTO read(String id) {
        Optional<Autor> optionalAutor = autorRepository.findById(id);
        if (optionalAutor.isPresent()) {
            return new AutorDTO(optionalAutor.get());
        }
        throw new NoSuchElementException("Autor com id " + id + " não encontrado");
    }

    public AutorDTO update(String id, AutorDTO autorDto) {
        Autor autor = Autor.fromDTO(autorDto);
        autor.setId(id);
        return new AutorDTO(autorRepository.save(autor));
    }

    public void delete(String id) {
        autorRepository.deleteById(id);
    }

    public Page<AutorDTO> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable).map(AutorDTO::new);
    }

}
