package br.com.alura.literalura.service;

import br.com.alura.literalura.model.DadosAutores;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.RespostaApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CallApi implements CommandLineRunner {

    private String url;

    public CallApi(String url){
        this.url = url;
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.recebeDados(getUrl());
        var coversorDeDados = new ConversorDeDados();
        RespostaApi resposta = coversorDeDados.obterDadodos(json, RespostaApi.class);

        DadosLivro livro = resposta.resultado().get(0);
        DadosAutores autores = livro.getAutores().get(0);
        String indioma = livro.getLinguagens().get(0);

        System.out.println("Titulo: " + livro.getTitulo());
        System.out.println("Autor: " + autores.getNome());
        System.out.println("Indioma: " + indioma);
        System.out.println("Numero do download: " + livro.getNumeroDowloads());

    }

    public String getUrl() {
        return url;
    }
}