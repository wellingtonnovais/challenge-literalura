package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosLivro(@JsonAlias("name") String autor,
                         @JsonAlias("title") String nomeLivro,
                         @JsonAlias("languages") String indioma,
                         @JsonAlias("download_count") Double numeroDownload,
                         @JsonAlias("birth_year") Integer anoNascimento,
                         @JsonAlias("death_year") Integer anoFalecimento) {
}
