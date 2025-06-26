package br.com.alura.literalura.service;

public class CallApi {

    private String url;

    public CallApi(String url){
        this.url = url;
    }

    public String resultadoApi() {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.recebeDados(getUrl());
        return json;
    }

    public String getUrl() {
        return url;
    }
}