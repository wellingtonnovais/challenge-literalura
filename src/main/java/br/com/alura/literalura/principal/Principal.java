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
    private int escolha = -1;
    private final AutoresRepository repositorio;
    private final LivrosRepository repositorioLivros;
    private String json;

    Links links = new Links();
    Menus menus = new Menus();

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
                        listarLivrosBuscados();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivosEmAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
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
        } catch (InputMismatchException e) {
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

    public void salvaNoBanco() throws Exception {
        RespostaApi resposta = new ConversorDeDados().obterDadodos(json, RespostaApi.class);

        if (!resposta.resultado().isEmpty()) {
            DadosLivros dadosLivro = resposta.resultado().get(0);

            for (DadosAutores dadosAutor : dadosLivro.autores()) {
                Autores autor;
                var autorOptional = repositorio.findByNome(dadosAutor.nome());
                if (autorOptional.isPresent()) {
                    autor = autorOptional.get();
                } else {
                    autor = dadosAutor.toEntityAutor();
                    repositorio.save(autor);
                }

                Livro livro = dadosLivro.toEntityLivro();
                livro.setAutor(autor);

                if (!repositorioLivros.existsByTitulo(livro.getTitulo())) {
                    repositorioLivros.save(livro);
                    System.out.println("\nLivro salvo com sucesso: " + livro.getTitulo());
                } else {
                    System.out.println("\nLivro já existe no banco: " + livro.getTitulo());
                }

                exibeResultado(livro);
            }
        } else {
            System.out.println("\nNenhum livro encontrado para esse título.");
        }
    }

    private void exibeResultado(Livro livro) {
        System.out.println(menus.getApresentacaoResposta1());
        exibirInformacoesDoLivro(livro);
    }

    private void listarLivrosBuscados() throws Exception {
        List<Livro> livrosRegistrados = repositorioLivros.findAllComAutorELinguagens();

        livrosRegistrados.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(this::exibirInformacoesDoLivro);

        exibeMenu();
    }

    private void listarAutores() throws Exception {
        List<Autores> autoresRegistrados = repositorio.findAll();
        autoresRegistrados.stream()
                .sorted(Comparator.comparing(Autores::getNome))
                .forEach(System.out::println);
        exibeMenu();
    }

    private void listarLivrosPorIdioma() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getMenuIndioma());
        System.out.print("Digite o código do idioma (ex: en, pt, es, fr): ");
        String idioma = scanner.nextLine().trim().toLowerCase();

        List<Livro> livrosRegistrados = repositorioLivros.findAllComAutorELinguagens();

        List<Livro> livrosFiltrados = livrosRegistrados.stream()
                .filter(livro -> livro.getLinguagens() != null && livro.getLinguagens().contains(idioma))
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();

        if (livrosFiltrados.isEmpty()) {
            System.out.println(menus.linhaPontilhadaMenu(1));
            System.out.println("Nenhum livro encontrado no idioma informado.");
            System.out.println(menus.linhaPontilhadaMenu(2));
        } else {
            livrosFiltrados.forEach(this::exibirInformacoesDoLivro);
        }

        exibeMenu();
    }

    private void listarAutoresVivosEmAno() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int ano = 0;

        try {
            System.out.print("Digite o ano para verificar quais autores estavam vivos: ");
            ano = scanner.nextInt();

            if (ano < 0 || ano > 3000) {
                System.out.println("Por favor, insira um ano válido.");
                exibeMenu();
                return;
            }

            List<Autores> autoresVivos = repositorio.findByNascimentoLessThanEqualAndMorteGreaterThanEqualOrMorteIsNull(ano, ano);

            if (autoresVivos.isEmpty()) {
                System.out.println(menus.linhaPontilhadaMenu(1));
                System.out.println("Nenhum autor encontrado vivo no ano " + ano);
                System.out.println(menus.linhaPontilhadaMenu(2));
            } else {
                autoresVivos.stream()
                        .sorted(Comparator.comparing(Autores::getNome))
                        .forEach(autor -> {
                            System.out.println(menus.linhaPontilhadaMenu(1));
                            System.out.println("Nome: " + autor.getNome());
                            System.out.println("Nascimento: " + autor.getNascimento());
                            System.out.println("Falecimento: " + (autor.getMorte() != null ? autor.getMorte() : "Ainda vivo ou não informado"));
                            System.out.println("Livros: ");
                            autor.getNomesLivros().forEach(livro -> System.out.println("  - " + livro.getTitulo()));
                            System.out.println(menus.linhaPontilhadaMenu(2));
                        });
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
        }

        exibeMenu();
    }

    private void exibirInformacoesDoLivro(Livro livro) {
        System.out.println(menus.linhaPontilhadaMenu(1));
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Autor desconhecido"));

        List<String> idiomas = livro.getLinguagens();
        if (idiomas != null && !idiomas.isEmpty()) {
            System.out.println("Idioma: " + String.join(", ", idiomas));
        } else {
            System.out.println("Idioma: não informado");
        }

        System.out.println("Número de downloads: " + livro.getNumeroDowloads());
        System.out.println(menus.linhaPontilhadaMenu(2));
    }
}