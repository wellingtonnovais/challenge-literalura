package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNome(String nome);
    List<Autores> findByNascimentoLessThanEqualAndMorteGreaterThanEqualOrMorteIsNull(int ano1, int ano2);

}
