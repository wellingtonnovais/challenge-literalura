package br.com.alura.literalura;

import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutoresRepository repositorio;

	public static void main(String[] args) throws Exception {
		org.springframework.boot.SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
