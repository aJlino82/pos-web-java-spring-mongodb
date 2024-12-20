package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.TarefaDTO;
import com.aula.biblioteca.dto.UsuarioDTO;
import com.aula.biblioteca.model.Tarefa;
import com.aula.biblioteca.model.Usuario;
import com.aula.biblioteca.repository.TarefaRepository;
import com.aula.biblioteca.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.fromDTO(usuarioDTO);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO read(String id) {
        Usuario usuario = findUsuarioById(id);
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO update(String id, UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.fromDTO(usuarioDTO);
        usuario.setId(id);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public void delete(String id) {
        findUsuarioById(id);
        usuarioRepository.deleteById(id);
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
    }

    public UsuarioDTO adicionaTarefa(String id, TarefaDTO tarefaDTO) {
        Tarefa tarefa = Tarefa.fromDTO(tarefaDTO);
        tarefa.reativarTarefa();
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        Usuario usuario = findUsuarioById(id);
        usuario.getTarefas().add(tarefaSalva);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public List<TarefaDTO> listaTarefas(String id) {
        Usuario usuario = findUsuarioById(id);
        return usuario.getTarefas().stream().map(TarefaDTO::new).toList();
    }


    // Metodo utilitário privado para reduzir repetição de código.
    private Usuario findUsuarioById(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário com ID '" + id + "' não encontrado"));
    }
}
