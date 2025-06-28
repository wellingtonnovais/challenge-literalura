package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutores(@JsonAlias("name") String nome,
                           @JsonAlias("birth_year") Integer nascimento,
                           @JsonAlias("death_year") Integer morte){

    public Autores toEntityAutor() {
        return new Autores(nome, nascimento, morte);
    }
}
