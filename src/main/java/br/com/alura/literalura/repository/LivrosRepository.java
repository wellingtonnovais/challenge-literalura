package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrosRepository extends JpaRepository<Livro, Long> {

    boolean existsByTitulo(String nome);

    @Query("SELECT DISTINCT l FROM Livro l JOIN FETCH l.autor LEFT JOIN FETCH l.linguagens")
    List<Livro> findAllComAutorELinguagens();

}
