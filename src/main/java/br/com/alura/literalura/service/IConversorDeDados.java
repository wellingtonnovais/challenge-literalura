package br.com.alura.literalura.service;

public interface IConversorDeDados {
    <T> T obterDadodos(String json, Class<T> classe);
}
