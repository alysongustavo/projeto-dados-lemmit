/*
 * Created by JFormDesigner on Mon Jan 11 22:12:12 GFT 2021
 */

package br.com.alyson.lemitti.application;

import javax.swing.event.*;
import br.com.alyson.lemitti.util.Constantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alyson Rodrigo
 */
public class Aplicacao extends JFrame {
    public Aplicacao() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) throws InterruptedException, IOException {

        Date atual = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyyhhmmss");
        String dataAtual = formater.format(atual);

        String tfArqEntrada = this.tfArqEntrada.getText().toString();
        String tfArqValidacao = (this.tfArqValidacao.getText().toString().isEmpty()) ? "dados_validacao_" + dataAtual : this.tfArqValidacao.getText().toString();
        String tfArqSaida = (this.tfArqSaida.getText().toString().isEmpty()) ? "dados_resultado_" + dataAtual : this.tfArqSaida.getText().toString();

        boolean rbTpDocArqCPFSelected = this.rbTpDocCPF.isSelected();
        boolean rbTpDocArqCNPJSelected = this.rbTpDocCNPJ.isSelected();
        boolean obtencaoContatosVinculo = this.rbObterContatoVinc.isSelected();

        if(!rbTpDocArqCPFSelected && !rbTpDocArqCNPJSelected){
            JOptionPane.showMessageDialog(this, "Favor Informar o Tipo do Arquivo do Documento de Origem", "Informação do Processamento" ,JOptionPane.ERROR_MESSAGE);
        }else if(rbTpDocArqCPFSelected){
            


            StringBuilder caminhoArquivoOrigem = new StringBuilder(Constantes.pathAplicacao);
            caminhoArquivoOrigem.append("\\");
            caminhoArquivoOrigem.append("dados");
            caminhoArquivoOrigem.append("\\");
            caminhoArquivoOrigem.append("2019");
            caminhoArquivoOrigem.append("\\");

            StringBuilder caminhoArquivoDestino = new StringBuilder(Constantes.pathAplicacao);
            caminhoArquivoDestino.append("\\");
            caminhoArquivoDestino.append("resultado");
            caminhoArquivoDestino.append("\\");
            caminhoArquivoDestino.append("2019");
            caminhoArquivoDestino.append("\\");

            String arqDocEntrada = obtencaoContatosVinculo ? caminhoArquivoOrigem.toString() + "vinculos" + "\\" + tfArqEntrada + "_vinculo" + ".txt"  : caminhoArquivoOrigem.toString() + tfArqEntrada + ".txt";
            String arqDocValidacao = obtencaoContatosVinculo ? caminhoArquivoOrigem.toString() + "vinculos" + "\\" + tfArqValidacao + "_vinculo"  + ".txt" : caminhoArquivoOrigem.toString() + tfArqValidacao  + ".txt";
            String arqDocSaida = obtencaoContatosVinculo ? caminhoArquivoDestino.toString() + "vinculos" + "\\"  + tfArqSaida + ".csv" : caminhoArquivoDestino.toString()  + tfArqSaida + ".csv";

            String arqDocVinculo = null;

            if(!obtencaoContatosVinculo){
                arqDocVinculo = caminhoArquivoOrigem.toString() + "vinculos" + "\\" +tfArqEntrada + "_vinculo" + ".txt";
            }

            boolean ehArquivoDocOrigem = (new File(arqDocEntrada)).exists();
            boolean ehArquivoDocDestinoValidacao = (new File(arqDocValidacao)).exists();
            boolean ehArquivoDocDestino = (new File(arqDocSaida)).exists();
            boolean ehArquivoVinculo = false;
            boolean ehArqDocVinculoResultado = false;
            if(!obtencaoContatosVinculo){
                ehArquivoVinculo = (new File(arqDocVinculo)).exists();
            }

            if(!ehArquivoDocDestinoValidacao){
                if((new File(arqDocValidacao).createNewFile())){
                    ehArquivoDocDestinoValidacao = true;
                }
            }

            if(!ehArquivoDocDestino){
                if((new File(arqDocSaida).createNewFile())){
                    ehArquivoDocDestino = true;
                }
            }

            if(!ehArquivoVinculo && !obtencaoContatosVinculo){
                if((new File(arqDocVinculo).createNewFile())){
                    ehArquivoVinculo = true;
                }
            }

            if(!tfArqEntrada.isEmpty() && !tfArqValidacao.isEmpty() && !tfArqSaida.isEmpty()
                    && ehArquivoDocOrigem && ehArquivoDocDestino && ehArquivoDocDestinoValidacao){
                new App(arqDocEntrada, arqDocValidacao, arqDocSaida, arqDocVinculo , obtencaoContatosVinculo).iniciarObtencaoContatos();
                this.button1.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Obtenção dos contatos finalizado!", "Informação Processamento" ,JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios e os arquivos devem existir", "Informação Arquivos" ,JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Obtenção de Arquivos de Documentos do Tipo CNPJ ainda em desenvolvimento", "Informação do Processamento" ,JOptionPane.WARNING_MESSAGE);
        }
        
    }

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                new Aplicacao().setVisible(true);
                super.run();
            }
        }.start();
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        dispose();
    }

    private void radioButton1ActionPerformed(ActionEvent e) { }
    
    private void rbTpDocCPFActionPerformed(ActionEvent e) {
        if(rbTpDocCPF.isSelected()){
            rbTpDocCNPJ.setSelected(false);
        }else{
            rbTpDocCNPJ.setSelected(true);
        }
    }

    private void rbTpDocCNPJActionPerformed(ActionEvent e) {
        if(rbTpDocCNPJ.isSelected()){
            rbTpDocCPF.setSelected(false);
        }else{
            rbTpDocCPF.setSelected(true);
        }
    }

    private void radioButton1StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Alyson Rodrigo
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        tfArqSaida = new JTextField();
        tfArqValidacao = new JTextField();
        button1 = new JButton();
        label4 = new JLabel();
        label7 = new JLabel();
        rbTpDocCNPJ = new JRadioButton();
        panel2 = new JPanel();
        panel4 = new JPanel();
        rbTpDocCPF = new JRadioButton();
        tfArqEntrada = new JTextField();
        rbObterContatoVinc = new JRadioButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Obten\u00e7\u00e3o dados Lemitti");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Op\u00e7\u00f5es");

                //---- menuItem1 ----
                menuItem1.setText("Sair");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("Arquivo de Entrada Documentos:");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 50), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Arquivo de Valida\u00e7\u00e3o:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(label2);
        label2.setBounds(30, 85, 150, label2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("Arquivo de Sa\u00edda:");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(label3);
        label3.setBounds(60, 120, 125, label3.getPreferredSize().height);
        contentPane.add(tfArqSaida);
        tfArqSaida.setBounds(180, 115, 395, tfArqSaida.getPreferredSize().height);
        contentPane.add(tfArqValidacao);
        tfArqValidacao.setBounds(180, 80, 395, tfArqValidacao.getPreferredSize().height);

        //---- button1 ----
        button1.setText("Obter Contatos");
        button1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        contentPane.add(button1);
        button1.setBounds(40, 365, 535, 35);

        //---- label4 ----
        label4.setText("Obten\u00e7\u00e3o Contatos do Lemitti");
        label4.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label4);
        label4.setBounds(0, 10, 605, 20);

        //---- label7 ----
        label7.setText("Tipo de Documento do Arquivo");
        label7.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label7);
        label7.setBounds(30, 170, 540, label7.getPreferredSize().height);

        //---- rbTpDocCNPJ ----
        rbTpDocCNPJ.setText("CNPJ");
        rbTpDocCNPJ.setHorizontalAlignment(SwingConstants.CENTER);
        rbTpDocCNPJ.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rbTpDocCNPJ.setBackground(new Color(204, 204, 255));
        rbTpDocCNPJ.setForeground(Color.black);
        rbTpDocCNPJ.addActionListener(e -> {
			rbTpDocCNPJActionPerformed(e);
			rbTpDocCNPJActionPerformed(e);
		});
        contentPane.add(rbTpDocCNPJ);
        rbTpDocCNPJ.setBounds(270, 195, 300, 25);

        //======== panel2 ========
        {
            panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel2. getBorder( )) ); panel2. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panel2.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(new Rectangle(new Point(30, 275), panel2.getPreferredSize()));

        //======== panel4 ========
        {
            panel4.setBackground(new Color(204, 204, 255));
            panel4.setLayout(null);

            //---- rbTpDocCPF ----
            rbTpDocCPF.setText("CPF");
            rbTpDocCPF.setHorizontalAlignment(SwingConstants.CENTER);
            rbTpDocCPF.setFont(new Font("Segoe UI", Font.BOLD, 12));
            rbTpDocCPF.setBackground(new Color(204, 204, 255));
            rbTpDocCPF.setForeground(Color.black);
            rbTpDocCPF.addActionListener(e -> {
			rbTpDocCPFActionPerformed(e);
			rbTpDocCPFActionPerformed(e);
		});
            panel4.add(rbTpDocCPF);
            rbTpDocCPF.setBounds(0, 0, 245, 25);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel4.getComponentCount(); i++) {
                    Rectangle bounds = panel4.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel4.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel4.setMinimumSize(preferredSize);
                panel4.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel4);
        panel4.setBounds(30, 195, 540, 25);
        contentPane.add(tfArqEntrada);
        tfArqEntrada.setBounds(245, 45, 330, tfArqEntrada.getPreferredSize().height);

        //---- rbObterContatoVinc ----
        rbObterContatoVinc.setText("Obten\u00e7\u00e3o contatos dos vinculos?");
        rbObterContatoVinc.setBackground(new Color(255, 51, 51));
        rbObterContatoVinc.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rbObterContatoVinc.setHorizontalAlignment(SwingConstants.CENTER);
        rbObterContatoVinc.setForeground(new Color(0, 51, 51));
        rbObterContatoVinc.addChangeListener(e -> radioButton1StateChanged(e));
        rbObterContatoVinc.addActionListener(e -> radioButton1ActionPerformed(e));
        contentPane.add(rbObterContatoVinc);
        rbObterContatoVinc.setBounds(30, 255, 545, 45);

        contentPane.setPreferredSize(new Dimension(605, 465));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Alyson Rodrigo
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField tfArqSaida;
    private JTextField tfArqValidacao;
    private JButton button1;
    private JLabel label4;
    private JLabel label7;
    private JRadioButton rbTpDocCNPJ;
    private JPanel panel2;
    private JPanel panel4;
    private JRadioButton rbTpDocCPF;
    private JTextField tfArqEntrada;
    private JRadioButton rbObterContatoVinc;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
