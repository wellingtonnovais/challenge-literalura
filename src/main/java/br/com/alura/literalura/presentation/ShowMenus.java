package br.com.alura.literalura.presentation;

import br.com.alura.literalura.service.CallApi;
import br.com.alura.literalura.service.Links;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowMenus extends Menus{
    public String exibeMenu(){
        System.out.println(getMenuPrincipal());

        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();

        Links links = new Links();

        try {
            while (escolha != 0) {
                switch (escolha) {
                    case 1:
                        System.out.println("Insira o nome do livro que você deseja procurar");
                        CallApi callApi = new CallApi(links.getGUTENDEX_URL() + scanner.nextLine().toLowerCase().replace(" ", "+"));
                        return "";
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
                        break;
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Por favor, digite apenas números inteiros. ");
            exibeMenu();
        }
        return " ";
    }
}
