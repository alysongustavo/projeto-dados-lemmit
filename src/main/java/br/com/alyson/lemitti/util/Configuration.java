package br.com.alyson.lemitti.util;

public enum  Configuration {

    URL_PRINCIPAL("https://lemitti.com/auth/login");

    private String url = null;

    Configuration(String url) {

        this.url = url;
    }

    public String getValor() {

        return this.url;
    }
}
