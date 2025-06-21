package br.com.alura.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaApi(@JsonAlias("count")Integer numero,
                          @JsonAlias("results")List<DadosLivro> resultado){

}