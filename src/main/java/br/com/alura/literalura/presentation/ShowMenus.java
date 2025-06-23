package br.com.alura.literalura.presentation;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.service.CallApi;
import br.com.alura.literalura.service.Links;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ShowMenus extends Menus{
    Links links = new Links();
    private int escolha = -1;

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
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Insira o nome do livro que você deseja procurar");
        try {
            String livroDigitado = scanner1.nextLine().replace(" ", "+").toLowerCase();

            CallApi callApi = new CallApi(links.getGUTENDEX_URL() + livroDigitado);
            callApi.run();
            exibeMenu();
        } catch (RuntimeException e) {
            System.out.println("Problema ao inserir o livro" + e);
        }
        //setEscolha(0);
    }

    private void livrosBuscados() throws Exception {
        //List<String> dadosLivroList =

        System.out.println(getApresentacaoResposta1());

        System.out.println(DadosLivro.class);

        System.out.println(getApresentacaoResposta2());

        exibeMenu();
    }

}
