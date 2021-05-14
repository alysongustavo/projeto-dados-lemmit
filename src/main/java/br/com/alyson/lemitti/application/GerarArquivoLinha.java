package br.com.alyson.lemitti.application;

import br.com.alyson.lemitti.util.Constantes;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GerarArquivoLinha {

    public static void main(String[] args) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o tamanho maximo de linhas"));
        int somador = 436;

        while (somador <= numero){
            escreverNoArquivoContatoResultadoVinculo(String.valueOf(somador));
            somador++;
        }

    }

    public static void escreverNoArquivoContatoResultadoVinculo(String linha) {
        try {

            StringBuilder caminhoArquivoCompleto = new StringBuilder(Constantes.pathAplicacao);
            caminhoArquivoCompleto.append("\\");
            caminhoArquivoCompleto.append("alternativo");
            caminhoArquivoCompleto.append("\\");
            caminhoArquivoCompleto.append("linhas_geradas.txt");

            File file = new File(caminhoArquivoCompleto.toString());
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
}
