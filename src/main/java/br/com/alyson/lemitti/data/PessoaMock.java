package br.com.alyson.lemitti.data;

import br.com.alyson.lemitti.model.Email;
import br.com.alyson.lemitti.model.Pessoa;
import br.com.alyson.lemitti.model.Telefone;
import br.com.alyson.lemitti.model.Vinculo;

import java.util.HashMap;
import java.util.Map;

public class PessoaMock {

    private static final Map<String, Pessoa> pessoas = new HashMap<>();

    static {

       /* Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alyson Rodrigo Gustavo da Silva");
        pessoa.setCpf("086.703.134-48");

        Email email = new Email();
        email.setEmail("aci.alyson@gmail.com");
        pessoa.addEmail(email);

        Telefone telefone = new Telefone();
        telefone.setContato("83996100456");
        pessoa.addTelefone(telefone);

        Vinculo vinculo  = new Vinculo();
        vinculo.setNome("Maria Lucia Gustavo dos Santos");
        vinculo.setTipoVinculo("Mae");
        vinculo.setDocumento("929.300.774-68");
        pessoa.addVinculo(vinculo, true);

        Vinculo vinculo2  = new Vinculo();
        vinculo2.setNome("Ewerthon Pedro Gustavo da Silva");
        vinculo2.setTipoVinculo("Irmão");
        vinculo2.setDocumento("087.707.300-68");
        pessoa.addVinculo(vinculo2, true);

        pessoas.put("086.703.134-48", pessoa);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Renata Irineu de Freitas");
        pessoa2.setCpf("700.579.274-21");

        Email email2 = new Email();
        email2.setEmail("renatafreitas232@gmail.com");
        pessoa2.addEmail(email2);

        Telefone telefone2 = new Telefone();
        telefone2.setContato("83981751375");
        pessoa2.addTelefone(telefone2);

        Vinculo vinculo3  = new Vinculo();
        vinculo3.setNome("Edilene Irineu Marcolino");
        vinculo3.setTipoVinculo("Mae");
        vinculo3.setDocumento("579.200.300-68");
        pessoa2.addVinculo(vinculo3, true);

        Vinculo vinculo4  = new Vinculo();
        vinculo4.setNome("Ewerthon Pedro Gustavo da Silva");
        vinculo4.setTipoVinculo("Irmão");
        vinculo4.setDocumento("087.707.300-68");
        pessoa2.addVinculo(vinculo4, true);

        pessoas.put("700.579.274-21", pessoa2);*/

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Maria Lucia Gustavo dos Santos");
        pessoa.setCpf("929.300.774-68");
        pessoa.setCpfVinculo("086.703.137-48");

        Email email = new Email();
        email.setEmail("lucia@gmail.com");
        pessoa.addEmail(email);

        Telefone telefone = new Telefone();
        telefone.setContato("8399885588");
        pessoa.addTelefone(telefone);

        pessoas.put("929.300.774-68", pessoa);

        pessoa = new Pessoa();

        pessoa.setNome("Everthon Pedro Gustavo da Silva");
        pessoa.setCpf("087.707.300-68");
        pessoa.setCpfVinculo("086.703.137-48");

        Email emailX = new Email();
        emailX.setEmail("everthon@gmail.com");
        pessoa.addEmail(emailX);

        Telefone telefoneX = new Telefone();
        telefoneX.setContato("8399774488");
        pessoa.addTelefone(telefoneX);

        pessoas.put("087.707.300-68", pessoa);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Edilene Irineu Marcolino");
        pessoa2.setCpf("579.200.300-68");
        pessoa2.setCpfVinculo("700.579.274-21");

        Email email2 = new Email();
        email2.setEmail("edilene@gmail.com");
        pessoa2.addEmail(email2);

        Telefone telefone2 = new Telefone();
        telefone2.setContato("83981885588");
        pessoa2.addTelefone(telefone2);

        pessoas.put("579.200.300-68", pessoa2);

        pessoa2 = new Pessoa();
        pessoa2.setNome("Ricardo Irineu de Freitas");
        pessoa2.setCpf("444.555.666-77");
        pessoa2.setCpfVinculo("700.579.274-21");

        Email email3 = new Email();
        email3.setEmail("ricardo@gmail.com");
        pessoa2.addEmail(email3);

        Telefone telefone3 = new Telefone();
        telefone3.setContato("83987885577");
        pessoa2.addTelefone(telefone3);

        pessoas.put("444.555.666-77", pessoa2);

    }

    public static Pessoa buscarPessoa(String documento){
        return pessoas.get(documento);
    }

}
