package br.com.alura.literalura;

import br.com.alura.literalura.presentation.ShowMenus;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication {
	public static void main(String[] args) throws Exception {
		ShowMenus showMenus = new ShowMenus();
		showMenus.exibeMenu();
	}
}
