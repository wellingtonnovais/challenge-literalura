package br.com.alura.literalura.model;

import java.util.List;

public class Livro {

    private int numeroDowloads;

    private String titulo;

    private List<Autores> autores;

    private List<String> linguagens;

    public Livro() {}

    public Livro(String titulo, int numeroDowloads, List<String> linguagens) {
    }


    public List<String> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(List<String> linguagens) {
        this.linguagens = linguagens;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
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