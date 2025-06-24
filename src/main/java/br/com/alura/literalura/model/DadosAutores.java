package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAutores {
    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer nascimento;

    @JsonAlias("death_year")
    private Integer morte;


//    public Autores(String nome, Integer nascimento, Integer morte) {
//        this.nome = nome;
//        this.nascimento = nascimento;
//        this.morte = morte;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNascimento() {
        return nascimento;
    }

    public void setNascimento(Integer nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getMorte() {
        return morte;
    }

    public void setMorte(Integer morte) {
        this.morte = morte;
    }
}
