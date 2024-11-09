package com.aula.biblioteca;

import com.aula.biblioteca.repository.LivroRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

    @Autowired
    LivroRepository livroRepository;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @PostConstruct
    public void MongoDBTest() {
        //System.out.println(livroRepository.findByTitulo("Livro A"));
        //System.out.println(livroRepository.findByNotaGreaterThanEqual(7F));
        //System.out.println(livroRepository.findByQtdPaginasGreaterThanEqual(320));
        //System.out.println(livroRepository.findByNotaAndQtdPaginasGreaterThanEqual(7F,320));
    }

}
