package br.com.alyson.lemitti.controller;

import br.com.alyson.lemitti.model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.ArrayList;
import java.util.List;

public class Robo {

    private WebDriver driver = null;

    private boolean concluirColeta = false;

    public Robo(String urlPrincipal) {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--verbose");
        chromeOptions.addArguments("--allowed-ips");
        driver = new ChromeDriver(chromeOptions);
        driver.get(urlPrincipal);
    }

    private void dormir(int tempoSono) throws NumberFormatException, InterruptedException {

        Thread.sleep(Long.parseLong(String.valueOf(tempoSono * 1000)));
    }

    private void focoAba(int aba) {

        List<String> abas = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(abas.get(aba - 1));
    }

    private void fecharAba(int aba) {

        List<String> abas = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(abas.get(aba - 1));
        driver.close();
    }

    public void logarAdvogado() throws NumberFormatException, InterruptedException {

        focoAba(1);

        Usuario advogado = new Usuario();

        WebElement element = driver.findElement(By.id("email"));
        element.sendKeys(advogado.getEmail());

        element = driver.findElement(By.id("password"));
        element.sendKeys(advogado.getPassword());

        element = driver.findElement(By.className("btn-lemit"));
        element.submit();

        dormir(2);
    }

    public Pessoa pesquisarContato(String cpf, String cpfVinculo, boolean ehObtencaoContatosVinculo) throws NumberFormatException, InterruptedException {

        focoAba(1);

        boolean temEmail = false;
        boolean temTelefone = false;
        boolean temVinculo = false;
        boolean temEndereco = false;

        dormir(4);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(cpf);

        if(ehObtencaoContatosVinculo){
            pessoa.setCpfVinculo(cpfVinculo);
        }

        WebElement element = driver.findElement(By.id("document"));
        element.sendKeys("");
        element.sendKeys(cpf);

        element = driver.findElement(By.className("btn-primary"));
        element.submit();

        dormir(5);

        WebElement telefonesContatos = null;
        boolean achouDocumento = false;

        if(ehObtencaoContatosVinculo){
            WebElement nomeFind = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[3]/div[2]/div[1]/table/tbody/tr[1]/td"));
            pessoa.setNome(nomeFind.getAttribute("innerHTML"));
        }

        try {
            telefonesContatos = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[6]/div[2]/div/table/tbody"));
            achouDocumento = true;
        }catch (Exception e){
            achouDocumento = false;
        }

        if(achouDocumento){
            List<WebElement> linhasTelefone = telefonesContatos.findElements(By.cssSelector("tr"));

            for (WebElement linhaDetalhe : linhasTelefone) {

                WebElement colunaContato = linhaDetalhe.findElements(By.cssSelector("td")).get(0);
                WebElement em = null;

                try{
                    em = colunaContato.findElement(By.cssSelector("em"));
                    temTelefone = true;
                }catch (Exception e){
                    temTelefone = false;
                }

                if(temTelefone){
                    String mensagemNaoEncontrado = em.getAttribute("innerHTML");
                    Telefone telefone = new Telefone();
                    telefone.setContato(mensagemNaoEncontrado);
                    pessoa.addTelefone(telefone);
                }else{
                    WebElement linkA = colunaContato.findElements(By.cssSelector("a")).get(0);
                    String numero = linkA.getAttribute("innerHTML");
                    Telefone telefone = new Telefone();
                    telefone.setContato(numero);
                    pessoa.addTelefone(telefone);
                }
            }

            WebElement emails = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[8]/div[2]/div/table/tbody"));

            List<WebElement> linhasEmail = emails.findElements(By.cssSelector("tr"));

            for (WebElement linhaDetalhe : linhasEmail) {

                WebElement colunaEmail = linhaDetalhe.findElements(By.cssSelector("td")).get(0);
                WebElement em2 = null;
                try{
                    em2 = colunaEmail.findElement(By.cssSelector("em"));
                    temEmail = true;
                }catch (Exception e){
                    temEmail = false;
                }

                if(temEmail){
                    String mensagemNaoEncontrado = em2.getAttribute("innerHTML");
                    Email email = new Email();
                    email.setEmail(mensagemNaoEncontrado);
                    pessoa.addEmail(email);
                }else{
                    WebElement linkA = colunaEmail.findElements(By.cssSelector("a")).get(0);
                    String email = linkA.getAttribute("innerHTML");
                    Email emailContato = new Email();
                    emailContato.setEmail(email);
                    pessoa.addEmail(emailContato);
                }

            }

            WebElement enderecos = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[9]/div[2]/div/table/tbody"));

            List<WebElement> linhasEndereco = enderecos.findElements(By.cssSelector("tr"));

            for (WebElement linhaDetalheEndereco : linhasEndereco) {

                WebElement colunaEndereco = linhaDetalheEndereco.findElements(By.cssSelector("td")).get(0);
                WebElement colunaBairro = null;
                WebElement colunaCidade = null;
                WebElement colunaCEP = null;
                WebElement colunaTipo = null;

                WebElement em2 = null;
                try{
                    em2 = colunaEndereco.findElement(By.cssSelector("em"));
                    temEndereco = true;
                }catch (Exception e){
                    temEndereco = false;
                }

                if(temEndereco){
                    String mensagemNaoEncontrado = em2.getAttribute("innerHTML");
                    Endereco endereco = new Endereco();
                    pessoa.addEndereco(endereco, false);
                }else{

                    colunaBairro = linhaDetalheEndereco.findElements(By.cssSelector("td")).get(1);
                    colunaCidade = linhaDetalheEndereco.findElements(By.cssSelector("td")).get(2);
                    colunaCEP = linhaDetalheEndereco.findElements(By.cssSelector("td")).get(3);
                    colunaTipo = linhaDetalheEndereco.findElements(By.cssSelector("td")).get(4);

                    WebElement linkEndereco = colunaEndereco;
                    WebElement linkBairro = colunaBairro;
                    WebElement linkCidade = colunaCidade;
                    WebElement linkCEP = colunaCEP;
                    WebElement linkTipo = colunaTipo.findElement(By.cssSelector("abbr"));

                    String enderecoRua = linkEndereco.getAttribute("innerHTML");
                    String bairro = linkBairro.getAttribute("innerHTML");
                    String cidade = linkCidade.getAttribute("innerHTML");
                    String cep = linkCEP.getAttribute("innerHTML");
                    String tipo = linkTipo.getAttribute("innerHTML");

                    Endereco endereco = new Endereco();
                    endereco.setEndereco(enderecoRua);
                    endereco.setBairro(bairro);
                    endereco.setCidade(cidade);
                    endereco.setCep(cep);
                    endereco.setTipo(tipo);

                    pessoa.addEndereco(endereco, true);
                }

            }

            if(!ehObtencaoContatosVinculo){

                WebElement vinculos = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[4]/div[2]/div/table/tbody"));

                List<WebElement> linhasVinculos = vinculos.findElements(By.cssSelector("tr"));

                for (WebElement linhaDetalhe : linhasVinculos) {

                    WebElement colunaNome = linhaDetalhe.findElements(By.cssSelector("td")).get(0);
                    WebElement colunaCPF = null;
                    WebElement colunaTpVinculo = null;

                    WebElement em2 = null;
                    try{
                        em2 = colunaNome.findElement(By.cssSelector("em"));
                        temVinculo = true;
                    }catch (Exception e){
                        temVinculo = false;
                    }

                    if(temVinculo){
                        String mensagemNaoEncontrado = em2.getAttribute("innerHTML");
                        Vinculo vinculo = new Vinculo();
                        pessoa.addVinculo(vinculo, false);
                    }else{

                        colunaCPF = linhaDetalhe.findElements(By.cssSelector("td")).get(1);
                        colunaTpVinculo = linhaDetalhe.findElements(By.cssSelector("td")).get(2);

                        WebElement linkNome = colunaNome;
                        WebElement linkCPF = colunaCPF.findElements(By.cssSelector("a")).get(0);
                        WebElement linkTpVinculo = colunaTpVinculo;
                        String nome = linkNome.getAttribute("innerHTML");
                        String documento = linkCPF.getAttribute("innerHTML");
                        String tpVinculo = linkTpVinculo.getAttribute("innerHTML");
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNome(nome);
                        vinculo.setDocumento(documento);
                        vinculo.setTipoVinculo(tpVinculo);
                        pessoa.addVinculo(vinculo, true);
                    }

                }

            }

            dormir(3);

            element = driver.findElement(By.className("btn-default"));
            element.click();

            return pessoa;
        }else{
            return null;
        }
    }

    private String tratarCampoTelefone(String html) {

        String retorno = new String();

        if (html.contains("CPF:")) {

            retorno = html.substring(html.indexOf(":") + 2, html.indexOf(":") + 16);
        }

        return retorno;
    }


}
