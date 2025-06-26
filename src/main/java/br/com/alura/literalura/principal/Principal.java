package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.presentation.Menus;
import br.com.alura.literalura.repository.AutoresRepository;
import br.com.alura.literalura.service.CallApi;
import br.com.alura.literalura.service.ConversorDeDados;
import br.com.alura.literalura.service.Links;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal extends Menus {

    Links links = new Links();
    private int escolha = -1;
    private final AutoresRepository repositorio;
    private String json;

    public Principal(AutoresRepository repositorio) {
        this.repositorio = repositorio;
    }

    public int getEscolha() {
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }

    public void exibeMenu() throws Exception {
        System.out.println(getMenuPrincipal());

        try {
            Scanner scanner = new Scanner(System.in);
            setEscolha(scanner.nextInt());

            while (getEscolha() != 0) {
                switch (getEscolha()) {
                    case 1:
                        buscaLivro();
                        break;
                    case 2:
                        livrosBuscados();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        getMenuIndioma();
                        scanner.nextLine();
                        break;
                    case 0:
                        setEscolha(0);
                        break;
                    default:
                        System.out.println("Por favor, digite apenas um dos números do menu. ");
                        setEscolha(0);
                        exibeMenu();
                        break;
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Por favor, digite apenas números inteiros. ");
            exibeMenu();
        }
    }

    private void buscaLivro() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome de um livro para buscar e salvar os autores:");
        try {
            String livroDigitado = scanner.nextLine().replace(" ", "+").toLowerCase();

            CallApi callApi = new CallApi(links.getGUTENDEX_URL() + livroDigitado);
            json = callApi.resultadoApi();
            salvaNoBanco();
            //exibeResultado();
            exibeMenu();
        } catch (RuntimeException e) {
            System.out.println("Problema ao inserir o livro: " + e);
            exibeMenu();
        }
    }

    private void livrosBuscados() throws Exception {

        System.out.println(getApresentacaoResposta1());

        System.out.println(Livro.class);

        System.out.println(getApresentacaoResposta2());

        exibeMenu();
    }

    public void salvaNoBanco() throws Exception {
        RespostaApi resposta = new ConversorDeDados().obterDadodos(json, RespostaApi.class);

        for (DadosLivros livro : resposta.resultado()) {
            for (DadosAutores dadosAutor : livro.autores()) {
                Autores autor = dadosAutor.toEntityAutor();
                Livro livro1 = livro.toEntityLivro();
                exibeResultado(autor, livro1);

                if (!repositorio.existsByNome(autor.getNome())) {
                    repositorio.save(autor);
                    System.out.println("Autor salvo: " + autor.getNome());
                } else {
                    System.out.println("Autor já existe: " + autor.getNome());
                }
            }
        }

    }

    private void exibeResultado(Autores autor, Livro livro){
        Menus menus = new Menus();

        System.out.println(menus.getApresentacaoResposta1());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + autor.getNome());
        System.out.println("Indioma: " + livro.getLinguagens().getFirst());
        System.out.println("Número de downloads: " + livro.getNumeroDowloads());
        System.out.println(menus.getApresentacaoResposta2());

    }

}




