package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    boolean existsByNome(String nome);
}
