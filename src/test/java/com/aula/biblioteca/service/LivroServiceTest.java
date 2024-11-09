package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.LivroDTO;
import com.aula.biblioteca.model.Livro;
import com.aula.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Livro livro = new Livro();

        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        LivroDTO result = livroService.save(new LivroDTO(livro));
        assertNotNull(result);
        assertEquals(LivroDTO.class, result.getClass());
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    void findAll() {
        Pageable pageable = mock(Pageable.class);
        Livro livro = new Livro();
        Page<Livro> pageLivros = new PageImpl<>(List.of(livro));

        when(livroRepository.findAll(pageable)).thenReturn(pageLivros);

        Page<LivroDTO> result = livroService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(livroRepository, times(1)).findAll(pageable);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
        Livro livro = new Livro();
        livro.setId("123");

        when(livroRepository.findById(anyString())).thenReturn(Optional.of(livro));

        LivroDTO result = livroService.findById("123");
        assertNotNull(result);
        assertEquals(LivroDTO.class, result.getClass());
        assertEquals("123", result.id());
        verify(livroRepository, times(1)).findById("123");
    }
}