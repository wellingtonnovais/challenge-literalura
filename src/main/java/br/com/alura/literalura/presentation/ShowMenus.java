package br.com.alura.literalura.presentation;

import br.com.alura.literalura.service.CallApi;
import br.com.alura.literalura.service.Links;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class ShowMenus extends Menus{
    public void exibeMenu() throws Exception {
        System.out.println(getMenuPrincipal());

        Links links = new Links();

        try {
            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();

            while (escolha != 0) {
                switch (escolha) {
                    case 1:
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
                        escolha = 0;
                        break;
                    case 2:
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
                        escolha = 0;
                        break;
                    default:
                        System.out.println("Por favor, digite apenas um dos números do menu. ");
                        escolha = 0;
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
}
