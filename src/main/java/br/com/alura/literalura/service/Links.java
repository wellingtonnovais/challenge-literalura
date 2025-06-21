package br.com.alura.literalura.service;

public class Links {
    private final String GUTENDEX_URL = System.getenv("LINK_API_GUTENDEX");

    public String getGUTENDEX_URL() {
        return GUTENDEX_URL;
    }


}
