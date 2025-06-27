package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer nascimento;

    private Integer morte;

    @OneToMany(mappedBy = "autor")
    private List<Livro> dadosLivros = new ArrayList<>();

    public Autores() {}

    public Autores(String nome, Integer nascimento, Integer morte) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.morte = morte;
    }

    public Autores(String titulo, int numeroDowloads) {}

    public Autores(DadosAutores dados) {
    }

    public List<Livro> getDadosLivros() {
        return dadosLivros;
    }

    public void setDadosLivros(List<Livro> dadosLivros) {
        this.dadosLivros = dadosLivros;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

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

    public void setMorte(Integer morte) {this.morte = morte;}

    @Override
    public String toString() {
        return " nome: " + nome + "\n" +
                " nascimento: " + nascimento + "\n" +
                " morte: " + morte + "\n";
    }
}
