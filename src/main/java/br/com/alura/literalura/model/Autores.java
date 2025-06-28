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

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> nomesLivros = new ArrayList<>();

    public Autores() {}

    public Autores(String nome, Integer nascimento, Integer morte) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.morte = morte;
    }

    public List<Livro> getNomesLivros() {
        return nomesLivros;
    }

    public void setNomesLivros(List<Livro> nomesLivros) {
        this.nomesLivros = nomesLivros;
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
        StringBuilder livrosFormatados = new StringBuilder();
        for (Livro livro : nomesLivros) {
            livrosFormatados.append("- ").append(livro.getTitulo()).append("\n");
        }

        return "\nAutor: " + nome + "\n" +
                "Ano de nascimento: " + nascimento + "\n" +
                "Ano de falecimento: " + (morte != null ? morte : "Vivo") + "\n" +
                "Livros:\n" + livrosFormatados;
    }

}
