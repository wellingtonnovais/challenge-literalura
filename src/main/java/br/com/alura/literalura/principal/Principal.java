package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.presentation.Menus;
import br.com.alura.literalura.repository.AutoresRepository;
import br.com.alura.literalura.repository.LivrosRepository;
import br.com.alura.literalura.service.CallApi;
import br.com.alura.literalura.service.ConversorDeDados;
import br.com.alura.literalura.service.Links;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal extends Menus {

    Links links = new Links();
    private int escolha = -1;
    private final AutoresRepository repositorio;
    private final LivrosRepository repositorioLivros;
    private String json;

    public Principal(AutoresRepository repositorio, LivrosRepository repositorioLivros) {
        this.repositorio = repositorio;
        this.repositorioLivros = repositorioLivros;
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
                        listarAutores();
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

        // Verifica se a resposta possui ao menos um livro
        if (!resposta.resultado().isEmpty()) {
            DadosLivros dadosLivro = resposta.resultado().get(0); //salva apenas o primeiro livro

            for (DadosAutores dadosAutor : dadosLivro.autores()) {
                Autores autor;

                // Verifica se o autor já está salvo no banco
                var autorOptional = repositorio.findByNome(dadosAutor.nome());
                if (autorOptional.isPresent()) {
                    autor = autorOptional.get();
                } else {
                    autor = dadosAutor.toEntityAutor();
                    repositorio.save(autor);
                }

                // Cria a entidade Livro e seta o autor
                Livro livro = dadosLivro.toEntityLivro();
                livro.setAutor(autor);

                // Salva o livro somente se ainda não existir
                if (!repositorioLivros.existsByTitulo(livro.getTitulo())) {
                    repositorioLivros.save(livro);
                    System.out.println("Livro salvo com sucesso: " + livro.getTitulo());
                } else {
                    System.out.println("Livro já existe no banco: " + livro.getTitulo());
                }

                exibeResultado(autor, livro);
            }
        } else {
            System.out.println("Nenhum livro encontrado para esse título.");
        }
    }


    private void exibeResultado(Autores autor, Livro livro){
        Menus menus = new Menus();

        System.out.println(menus.getApresentacaoResposta1());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + autor.getNome());
        if (livro.getLinguagens() != null && !livro.getLinguagens().isEmpty()) {
            System.out.println("Idioma: " + livro.getLinguagens().get(0));
        } else {
            System.out.println("Idioma não informado");
        }
        System.out.println("Número de downloads: " + livro.getNumeroDowloads());
        System.out.println(menus.getApresentacaoResposta2());
    }

    private void listarLivrosBuscados(){

    }

    private void listarAutores() throws Exception {
        List<Autores> autoresRegistrados = repositorio.findAll();
        autoresRegistrados.stream()
                .sorted(Comparator.comparing(Autores::getNome))
                .forEach(System.out::println);
        exibeMenu();
    }
}




