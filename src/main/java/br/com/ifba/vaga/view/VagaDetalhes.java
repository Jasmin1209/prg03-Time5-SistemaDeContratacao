/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.vaga.view;


import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.sessao.SessaoUsuario;
import br.com.ifba.vaga.controller.VagaController;
import br.com.ifba.vaga.entity.Vaga;
import java.awt.Color;
import javax.swing.JOptionPane;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Taila
 */
@Component
@Scope("prototype")
public class VagaDetalhes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VagaDetalhes.class.getName());

    // 🎨 Paleta de Cores: Azul Profissional
    private static final Color AZUL_CLARO = new Color(71, 178, 240);  // #47b2f0
    private static final Color AZUL_MEDIO = new Color(54, 150, 209);  // #3696d1
    private static final Color AZUL_BASE  = new Color(36, 121, 178);  // #2479b2
    private static final Color AZUL_FORTE = new Color(18, 92, 146);   // #125c92
    private static final Color AZUL_FUNDO = new Color(0, 63, 115);    // #003f73
    
    // Atributos que armazenam os dados necessários para a tela funcionar
    
    private final SessaoUsuario sessaoUsuario;
    private VagaController vagaController;
    private Usuario usuarioLogado;
    private Vaga  vaga; // Objeto da vaga específica que será exibida
    /**
     * Creates new form VagaDetalhes
     */
    public VagaDetalhes(SessaoUsuario sessaoUsuario, VagaController vagaController, Vaga vaga) {
        this.sessaoUsuario = sessaoUsuario;
        this.usuarioLogado = sessaoUsuario.getUsuario();
        this.vagaController = vagaController;
        this.vaga = vaga;

        
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela
        //this.setSize(677, 567);
        
        // Configura o JTextArea para quebrar o texto automaticamente quando chegar ao fim da largura
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        
        carregarDados(); //preenche os campos com a s informações da vaga
        configurarBotao(); //define se o botão dirá "editar" ou "candidatar-se"
        estilizarTela();
    }
    
    private void carregarDados() {
        if (vaga == null) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados da vaga.");
            return;
        }

        lblTituloVaga.setText(vaga.getTitulo());

       StringBuilder sb = new StringBuilder();

        // 🔹 dados da vaga
        sb.append("-----------------------------------------------------------\n");
        String statusTexto = (vaga.getStatus() != null && vaga.getStatus())
                ? "✅ ATIVA"
                : "🚫 ENCERRADA";

        sb.append("📊 Status: ").append(statusTexto).append("\n");
        sb.append("📌 Quantidade de vagas: ").append(vaga.getQuantidade()).append("\n");
        sb.append("💼 Modelo de contratação: ").append(vaga.getModelo()).append("\n");
        sb.append("🏢 Tipo de contratação: ").append(vaga.getTipo()).append("\n");
        sb.append("⏰ Período de contratação: ").append(vaga.getPeriodo()).append("\n");

        String cidade = (vaga.getLocalizacao() != null)
                ? vaga.getLocalizacao().getCidade()
                : "Não informada";

        sb.append("📍 Localização: ").append(cidade).append("\n");

        if (vaga.getFaixaSalarial() != null) {
            sb.append("💰 Faixa salarial: R$ ")
              .append(String.format("%,d", vaga.getFaixaSalarial()))
              .append("\n");
        } else {
            sb.append("💰 Faixa salarial: A combinar\n");
        }
        
         
        sb.append("-----------------------------------------------------------\n");
        sb.append("\n📝 DETALHES E REQUISITOS\n");
        sb.append("\n");
        sb.append(vaga.getDescricao() != null ? vaga.getDescricao() : "Nenhum detalhe adicional fornecido.");

        // Aplicar ao componente
        txtDescricao.setText(sb.toString());

        // Garante que o usuário veja o topo (os dados técnicos) assim que abrir
        txtDescricao.setCaretPosition(0);
    }


    
    /**
     * Define o comportamento visual do botão principal baseado no tipo de usuário
     */
    private void configurarBotao() {
        if(usuarioLogado instanceof UsuarioEmpresa){
            btnEditar.setText("Editar");
        }else {
            btnEditar.setText("Candidatar-se");
        }
    }
    
    private void estilizarTela() {
        // Título Principal
        lblTitulo.setForeground(AZUL_FUNDO);
    
        // Título da Vaga (Destaque)
        lblTituloVaga.setForeground(AZUL_BASE);
    
        // Área de Descrição
        txtDescricao.setBackground(new Color(250, 250, 250)); // Branco levemente acinzentado
        txtDescricao.setForeground(new Color(50, 50, 50));   // Texto quase preto para leitura
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(AZUL_CLARO));
    
        // Botão Voltar
        btnVoltar.setBackground(AZUL_CLARO);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    
        // Botão de Ação (Editar ou Candidatar)
        btnEditar.setBackground(AZUL_BASE);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setFocusPainted(false);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnVoltar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lblTituloVaga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 24)); // NOI18N
        lblTitulo.setText("Detalhes da Vaga");

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblDescricao.setText("Descrição:");

        txtDescricao.setEditable(false);
        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        btnVoltar.setText("<---");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        lblTituloVaga.setFont(new java.awt.Font("Segoe UI Variable", 3, 18)); // NOI18N
        lblTituloVaga.setText("Titulo da vaga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lblTituloVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(18, 18, 18)))
                .addComponent(lblTituloVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblDescricao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        new VagaListar(sessaoUsuario, vagaController).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (usuarioLogado instanceof UsuarioEmpresa) {
            new VagaEditar(sessaoUsuario, vagaController, vaga).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnEditarActionPerformed
                                       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloVaga;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
