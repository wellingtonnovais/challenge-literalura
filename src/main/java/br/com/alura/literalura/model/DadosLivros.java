package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("download_count") int numeroDowloads,
                          @JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DadosAutores> autores,
                          @JsonAlias("languages") List<String> linguagens){

    public Livro toEntityLivro() {
        Livro livro = new Livro();
        livro.setTitulo(this.titulo());
        livro.setNumeroDowloads(this.numeroDowloads());
        livro.setLinguagens(this.linguagens());
        return livro;
    }
};
