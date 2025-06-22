package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDeDados implements IConversorDeDados{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDadodos(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para classe \n" + e);
        }
    }
}