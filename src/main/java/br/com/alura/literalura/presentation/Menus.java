package br.com.alura.literalura.presentation;

public class Menus {
    private String menuPrincipal = """
            
            °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
            ESCOLHA O NÚMERO DA OPÇÃO DESEJADA
            
            1- Buscar livro pelo título
            2- Listar livros registrados
            3- Listar autores registrados
            4- Listar autores vivos em um determinado período
            5- Listar livros em um determinado indioma
            0- Sair
            °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
            
            """;

    private String menuIndioma = """
            
            ESCOLHA O INDIOMA PARA REALIZAR A BUSCA
            
            es- Espanhol
            en- Inglês
            fr- Francês
            pt- Português
            
            """;

    private String apresentacaoResposta1 = "\n°°°°°°°°°° LIVRO °°°°°°°°°°";

    public String linhaPontilhadaMenu (int numeroDaLinha) {
        if (numeroDaLinha == 1) {
            return "\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        }else {
            return "°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        }
    }

    public String getMenuPrincipal() {
        return menuPrincipal;
    }

    public String getMenuIndioma() {
        return menuIndioma;
    }

    public String getApresentacaoResposta1() {
        return apresentacaoResposta1;
    }

}