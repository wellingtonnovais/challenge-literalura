package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {
    @JsonAlias("count")
    private int id;


    @JsonAlias("download_count")
    private int numeroDowloads;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DadosAutores> autores;

    @JsonAlias("languages")
    private List<String> linguagens;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(List<String> linguagens) {
        this.linguagens = linguagens;
    }

    public List<DadosAutores> getAutores() {
        return autores;
    }

    public void setAutores(List<DadosAutores> autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroDowloads() {
        return numeroDowloads;
    }

    public void setNumeroDowloads(int numeroDowloads) {
        this.numeroDowloads = numeroDowloads;
    }
}