package br.com.alyson.lemitti.model;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    private String nome;

    private String cpfVinculo;

    private String cpf;

    private List<Email> emails = new ArrayList<Email>();

    private List<Telefone> telefones = new ArrayList<Telefone>();

    private List<Vinculo> vinculos = new ArrayList<>();

    private List<Endereco> enderecos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfVinculo() {
        return cpfVinculo;
    }

    public void setCpfVinculo(String cpfVinculo) {
        this.cpfVinculo = cpfVinculo;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco, boolean temEndereco){
        if(temEndereco){
            this.getEnderecos().add(endereco);
        }
    }

    public void addVinculo(Vinculo vinculo, boolean temVinculo){
        if(temVinculo) {
            this.getVinculos().add(vinculo);
        }
    }

    public void addEmail(Email email){
        this.getEmails().add(email);
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void addTelefone(Telefone telefone){
        this.getTelefones().add(telefone);
    }

    public String dadosFormatado(){
        StringBuilder dadosApend = new StringBuilder();

        dadosApend.append(this.cpf);
        dadosApend.append("|");

        if(this.vinculos.size() > 0){
            dadosApend.append("|");
        }

        this.emails.forEach(email -> {
            dadosApend.append(email.getEmail());
            dadosApend.append(",");
        });

        dadosApend.append("|");

        this.telefones.forEach(telefone -> {
            dadosApend.append(telefone.getContato());
            dadosApend.append(",");
        });

        dadosApend.append("|");

        this.enderecos.forEach(endereco -> {
            dadosApend.append(endereco.getEndereco());
            dadosApend.append(","+endereco.getBairro());
            dadosApend.append(","+endereco.getCidade());
            dadosApend.append(","+endereco.getCep());
            dadosApend.append(","+endereco.getTipo());
            dadosApend.append("/");
        });

        dadosApend.append("|");

        this.vinculos.forEach(vinculo -> {
            dadosApend.append(vinculo.getNome());
            dadosApend.append(","+vinculo.getDocumento());
            dadosApend.append(","+vinculo.getTipoVinculo());
            dadosApend.append("/");
        });

        return dadosApend.toString();
    }

    public String dadosFormatado2(){
        StringBuilder dadosApend = new StringBuilder();

        dadosApend.append(this.cpfVinculo);
        dadosApend.append("|");

        dadosApend.append(this.nome);
        dadosApend.append("|");

        dadosApend.append(this.cpf);
        dadosApend.append("|");

        this.emails.forEach(email -> {
            dadosApend.append(email.getEmail());
            dadosApend.append(",");
        });

        dadosApend.append("|");

        this.telefones.forEach(telefone -> {
            dadosApend.append(telefone.getContato());
            dadosApend.append(",");
        });

        dadosApend.append("|");

        this.enderecos.forEach(endereco -> {
            dadosApend.append(endereco.getEndereco());
            dadosApend.append(","+endereco.getBairro());
            dadosApend.append(","+endereco.getCidade());
            dadosApend.append(","+endereco.getCep());
            dadosApend.append(","+endereco.getTipo());
            dadosApend.append("/");
        });

        return dadosApend.toString();
    }
}
