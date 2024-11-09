package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.AutorDTO;
import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public Page<AutorDTO> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable).map(AutorDTO::new);
    }

    public AutorDTO save(AutorDTO autorDTO) {
        return new AutorDTO(autorRepository.save(Autor.fromDTO(autorDTO)));
    }

    public AutorDTO update(String id, AutorDTO autorDto) {
        Autor autor = Autor.fromDTO(autorDto);
        autor.setId(id);
        return new AutorDTO(autorRepository.save(autor));
    }

}
