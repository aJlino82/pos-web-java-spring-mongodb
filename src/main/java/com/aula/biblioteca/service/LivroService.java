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

    /**
     * Salva um novo livro na base de dados.
     *
     * @param livroDTO os dados do livro a serem salvos.
     * @return LivroDTO com os dados salvos.
     */
    public LivroDTO save(LivroDTO livroDTO) {
        Livro livro = Livro.fromDTO(livroDTO);
        return new LivroDTO(livroRepository.save(livro));
    }

    /**
     * Retorna todos os livros paginados.
     *
     * @param pageable configuração de paginação.
     * @return Página de livros como LivroDTO.
     */
    public Page<LivroDTO> findAll(Pageable pageable) {
        return livroRepository.findAll(pageable).map(LivroDTO::new);
    }

    /**
     * Atualiza os dados de um livro existente.
     *
     * @param id       o ID do livro a ser atualizado.
     * @param livroDTO os novos dados do livro.
     * @return LivroDTO com os dados atualizados.
     */
    public LivroDTO update(String id, LivroDTO livroDTO) {
        Livro livro = findLivroById(id); // Garante que o livro existe antes de atualizar
        livro.updateFromDTO(livroDTO);
        return new LivroDTO(livroRepository.save(livro));
    }

    /**
     * Busca um livro pelo título.
     *
     * @param titulo   o título do livro a ser buscado.
     * @param pageable configuração de paginação.
     * @return LivroDTO com os dados do livro encontrado.
     * @throws NoSuchElementException se nenhum livro for encontrado.
     */
    public LivroDTO findByTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new NoSuchElementException("Livro com título '" + titulo + "' não encontrado"));
        return new LivroDTO(livro);
    }

    /**
     * Busca livros por nota mínima e quantidade de páginas mínima.
     *
     * @param nota       nota mínima.
     * @param qtdPaginas quantidade mínima de páginas.
     * @param pageable   configuração de paginação.
     * @return Página de livros que atendem aos critérios.
     */
    public Page<LivroDTO> findByNotaAndQtdPaginasGreaterThanEqual(Float nota, Integer qtdPaginas, Pageable pageable) {
        return livroRepository.findByNotaAndQtdPaginasGreaterThanEqual(nota, qtdPaginas, pageable).map(LivroDTO::new);
    }

    /**
     * Remove um livro pelo ID.
     *
     * @param id o ID do livro a ser removido.
     */
    public void delete(String id) {
        findLivroById(id); // Garante que o livro existe antes de deletar
        livroRepository.deleteById(id);
    }

    /**
     * Busca um livro pelo ID.
     *
     * @param id o ID do livro a ser buscado.
     * @return LivroDTO com os dados do livro encontrado.
     * @throws NoSuchElementException se nenhum livro for encontrado.
     */
    public LivroDTO findById(String id) {
        Livro livro = findLivroById(id);
        return new LivroDTO(livro);
    }

    // Métodos utilitários privados para reduzir repetição de código

    /**
     * Busca um livro pelo ID e lança exceção caso não encontre.
     *
     * @param id o ID do livro.
     * @return Instância de Livro encontrada.
     */
    private Livro findLivroById(String id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro com ID '" + id + "' não encontrado"));
    }
}
