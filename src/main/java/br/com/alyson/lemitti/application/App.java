package br.com.alyson.lemitti.application;

import br.com.alyson.lemitti.controller.Robo;
import br.com.alyson.lemitti.model.Documento;
import br.com.alyson.lemitti.model.Pessoa;
import br.com.alyson.lemitti.model.Vinculo;
import br.com.alyson.lemitti.util.Configuration;
import br.com.alyson.lemitti.util.LeituraArquivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class App {

    private String arquivoDocOrigem;
    private String arquivoDocDestino;
    private String arquivoDocDestinoValidacao;
    private String arquivoDocVinculo;
    private String arqDocVinculoResultado;
    private boolean ehObtencaoVinculo;

    public App(String arqDocOrigem, String arqDocDestinoValidacao, String arqDocDestino, String arquivoDocVinculo ,boolean ehVinculo){
        this.arquivoDocOrigem = arqDocOrigem;
        this.arquivoDocDestinoValidacao = arqDocDestinoValidacao;
        this.arquivoDocDestino = arqDocDestino;
        this.arquivoDocVinculo = arquivoDocVinculo;
        this.ehObtencaoVinculo = ehVinculo;
    }

    public String getArquivoDocOrigem() {
        return arquivoDocOrigem;
    }

    public String getArquivoDocDestino() {
        return arquivoDocDestino;
    }

    public String getArquivoDocDestinoValidacao() {
        return arquivoDocDestinoValidacao;
    }

    public String getArquivoDocVinculo() {
        return arquivoDocVinculo;
    }

    public String getArqDocVinculoResultado() {
        return arqDocVinculoResultado;
    }

    public boolean isEhObtencaoVinculo() {
        return ehObtencaoVinculo;
    }

    public void iniciarObtencaoContatos() throws InterruptedException{

        // Carrega o arquivo de documentos
        LeituraArquivo leituraArquivo = new LeituraArquivo(getArquivoDocOrigem(),getArquivoDocDestinoValidacao(), this.ehObtencaoVinculo);
        List<Documento> documentos = leituraArquivo.getDocumentoList();

        if(this.ehObtencaoVinculo){
            documentos.removeIf(documento -> {
                return leituraArquivo.verificaDocJaConsultado(documento.getDocVinculo());
            });
        }else{
            documentos.removeIf(documento -> {
                return leituraArquivo.verificaDocJaConsultado(documento.getNumero());
            });
        }


        Robo neri = new Robo(Configuration.URL_PRINCIPAL.getValor());

        neri.logarAdvogado();

        if(this.ehObtencaoVinculo){
            documentos.forEach(documento -> {
                try {

                    Pessoa pessoa1 = neri.pesquisarContato(documento.getDocVinculo(), documento.getNumero() ,ehObtencaoVinculo);

                    if(pessoa1 != null){
                        escreverNoArquivo(pessoa1, documento.getDocVinculo(), ehObtencaoVinculo);
                    }else{
                        System.out.println("Pessoa não encontrada com o documento: " + documento.getNumero());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }else{
            documentos.forEach(documento -> {
                try {

                    Pessoa pessoa1 = neri.pesquisarContato(documento.getNumero(), "", ehObtencaoVinculo);
                    // Pessoa pessoa1 = PessoaMock.buscarPessoa(documento.getNumero());

                    if(pessoa1 != null){
                        escreverNoArquivo(pessoa1, documento.getNumero(), ehObtencaoVinculo);
                    }else{
                        System.out.println("Pessoa não encontrada com o documento: " + documento.getNumero());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public void escreverNoArquivo(Pessoa pessoa, String cpf, boolean ehObtencaoVinculo) {

       escreverNoArquivoDocumentoValidacao(cpf);
       if(!ehObtencaoVinculo){
           escreverNoArquivoContato(pessoa.dadosFormatado());
           escreverNoArquivoVinculo(pessoa);
       }else{
           escreverNoArquivoContato(pessoa.dadosFormatado2());
       }

    }

    public void escreverNoArquivoContato(String linha) {
        try {
            File file = new File(this.getArquivoDocDestino());
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(linha);
            conexao.newLine();
            conexao.close();
            fw.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escreverNoArquivoContatoResultadoVinculo(String linha) {
        try {
            File file = new File(this.arqDocVinculoResultado);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(linha);
            conexao.newLine();
            conexao.close();
            fw.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escreverNoArquivoDocumentoValidacao(String cpf) {
        try {
            File file = new File(this.getArquivoDocDestinoValidacao());
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(cpf);
            conexao.newLine();
            conexao.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escreverNoArquivoVinculo(Pessoa pessoa) {
        try {

            File fileVinculo = new File(this.getArquivoDocVinculo());
            FileWriter fw = new FileWriter(fileVinculo, true);
            BufferedWriter conexao = new BufferedWriter(fw);

            for(Vinculo vinculo: pessoa.getVinculos()){

               StringBuilder stringBuilder = new StringBuilder(pessoa.getCpf());
               stringBuilder.append("|");
               stringBuilder.append(vinculo.getDocumento());

               conexao.write(stringBuilder.toString());
               conexao.newLine();
            }
            conexao.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
