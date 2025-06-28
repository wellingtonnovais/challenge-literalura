package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int numeroDowloads;

    private String titulo;

    @ElementCollection
    private List<String> linguagens;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;

    public Livro() {}

    public Livro(String titulo, int numeroDowloads, List<String> linguagens) {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(List<String> linguagens) {
        this.linguagens = linguagens;
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

    @Override
    public String toString() {
        return "Livro{" +
                "Id=" + Id +
                ", numeroDowloads=" + numeroDowloads +
                ", titulo='" + titulo + '\'' +
                ", linguagens=" + linguagens +
                ", autor=" + autor +
                '}';
    }
}