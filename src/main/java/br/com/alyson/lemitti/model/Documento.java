package br.com.alyson.lemitti.model;

public class Documento {

    private String numero;

    private String docVinculo;

    public Documento() {
    }

    public Documento(String numero) {
        this.numero = numero;
    }

    public Documento(String numero, String docVinculo) {
        this.numero = numero;
        this.docVinculo = docVinculo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDocVinculo() {
        return docVinculo;
    }

    public void setDocVinculo(String docVinculo) {
        this.docVinculo = docVinculo;
    }
}
