/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.telaPrincipal.view;

import br.com.ifba.infrastructure.spring.SpringContext;
import br.com.ifba.perfil.candidato.controller.PerfilCandidatoController;
import br.com.ifba.perfil.candidato.view.TelaApresentacaoCandidato;
import br.com.ifba.perfil.candidato.view.TelaEditarPerfil;
import br.com.ifba.perfil.empresa.controller.PerfilEmpresaController;
import br.com.ifba.perfil.empresa.view.TelaApresentacaoEmpresa;
import br.com.ifba.perfil.empresa.view.TelaEditarPerfilEmpresa;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.view.LoginCandidatoView;
import br.com.ifba.vaga.controller.VagaController;
import br.com.ifba.vaga.view.VagaListar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import br.com.ifba.usuario.controller.UsuarioCandidatoController;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.sessao.SessaoUsuario;
import br.com.ifba.usuario.view.LoginEmpresaView;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Taila
 */
@Component
@Scope("singleton")
public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    // 🎨 Paleta de Cores: Azul Profissional
    private static final Color AZUL_CLARO = new Color(71, 178, 240);  // #47b2f0
    private static final Color AZUL_MEDIO = new Color(54, 150, 209);  // #3696d1
    private static final Color AZUL_BASE  = new Color(36, 121, 178);  // #2479b2
    private static final Color AZUL_FORTE = new Color(18, 92, 146);   // #125c92
    private static final Color AZUL_FUNDO = new Color(0, 63, 115);    // #003f73
    
 
    private SessaoUsuario sessaoUsuario;
    private PerfilCandidatoController perfilCandidatoController;
    private PerfilEmpresaController perfilEmpresaController;

    /**
     * Creates new form TelaPrincipal
     * @param sessaoUsuario
     * @param vagaController
     * @param controller
     * @param perfilCandidatoController
     */
    @Autowired
    public TelaPrincipal(
            SessaoUsuario sessaoUsuario, 
            VagaController vagaController, 
            UsuarioCandidatoController controller,
            PerfilCandidatoController perfilCandidatoController,
            PerfilEmpresaController perfilEmpresaController
    ) {
        this.sessaoUsuario = sessaoUsuario;
        this.perfilCandidatoController = perfilCandidatoController;
        this.perfilEmpresaController = perfilEmpresaController;
    
        initComponents();
        estilizarTela();
        configurarAcessos();
    }
    
    public void atualizarSessao() {
        configurarAcessos();
    }

    private void estilizarTela() {
        //====== JFRAME =====
        setTitle("Sistema de Vagas - Início");
        setSize(677, 567); // Mantendo o padrão de tamanho das outras telas
        setLocationRelativeTo(null);
        setResizable(false);
        
        //======= FUNDO GERAL (AZUL FUNDO) ========
        getContentPane().setBackground(AZUL_FUNDO);
    
        //========== HEADER (jpnlTitulo) =============
        jpnlTitulo.setBackground(Color.WHITE);
    
        lblTitulo.setText("SISTEMA DE GESTÃO DE VAGAS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(AZUL_FUNDO);
    
        // Botão de Entrada no Header (Azul Claro)
        btnCadastro.setText("Entrar");
        btnCadastro.setBackground(AZUL_CLARO);
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFocusPainted(false);
        btnCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    
        //======= CARD CENTRAL (jpnlVerVagas) ========
        jpnlVerVagas.setBackground(Color.WHITE);
        jpnlVerVagas.setBorder(BorderFactory.createLineBorder(AZUL_CLARO, 2));
    
        // Botão Principal de Ver Vagas (Azul Base)
        btnListarVagas.setText("VER VAGAS DISPONÍVEIS");
        btnListarVagas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnListarVagas.setForeground(Color.WHITE);
        btnListarVagas.setBackground(AZUL_BASE);
        btnListarVagas.setFocusPainted(false);
        btnListarVagas.setBorderPainted(false);
        btnListarVagas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    
        // Texto de apoio
        lblTextinho.setText("Encontre o seu próximo desafio!");
        lblTextinho.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblTextinho.setForeground(new Color(100, 100, 100)); // Cinza suave
    }
    
    private void configurarAcessos() {
        if (sessaoUsuario.getUsuario() != null) {
            btnCadastro.setText("Meu Perfil");
            lblTextinho.setText(
                "Bem-vindo(a), " + sessaoUsuario.getUsuario().getNome() + "!"
            );
        } else {
            btnCadastro.setText("Entrar");
            lblTextinho.setText("Encontre o seu próximo desafio!");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnCadastro = new javax.swing.JButton();
        jpnlVerVagas = new javax.swing.JPanel();
        btnListarVagas = new javax.swing.JButton();
        lblTextinho = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitulo.setText("jLabel1");

        btnCadastro.setText("jButton1");
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlTituloLayout = new javax.swing.GroupLayout(jpnlTitulo);
        jpnlTitulo.setLayout(jpnlTituloLayout);
        jpnlTituloLayout.setHorizontalGroup(
            jpnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(btnCadastro)
                .addGap(16, 16, 16))
        );
        jpnlTituloLayout.setVerticalGroup(
            jpnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlTituloLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(btnCadastro))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnListarVagas.setText("jButton1");
        btnListarVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarVagasActionPerformed(evt);
            }
        });

        lblTextinho.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblTextinho.setText("jLabel1");

        javax.swing.GroupLayout jpnlVerVagasLayout = new javax.swing.GroupLayout(jpnlVerVagas);
        jpnlVerVagas.setLayout(jpnlVerVagasLayout);
        jpnlVerVagasLayout.setHorizontalGroup(
            jpnlVerVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlVerVagasLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(jpnlVerVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlVerVagasLayout.createSequentialGroup()
                        .addComponent(btnListarVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlVerVagasLayout.createSequentialGroup()
                        .addComponent(lblTextinho, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        jpnlVerVagasLayout.setVerticalGroup(
            jpnlVerVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlVerVagasLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btnListarVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTextinho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jpnlVerVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jpnlVerVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarVagasActionPerformed
        // Passamos o usuário que veio do login
        if (sessaoUsuario.getUsuario() == null) {
            JOptionPane.showMessageDialog(this, "Faça login para ver as vagas.");
            return;
        }
       VagaListar telaListagem = SpringContext.getBean(VagaListar.class);
       telaListagem.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnListarVagasActionPerformed

    private void btnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroActionPerformed
        Usuario usuario = sessaoUsuario.getUsuario();

        if(usuario == null){
            String[] opc = {"Candidato", "Empresa", "Cancelar"};
                int escolha = JOptionPane.showOptionDialog(this,
                    "Como você deseja acessar o sistema?",
                    "Seleção de Perfil",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, opc, opc[0]);
        
                if(escolha == 0) { // Candidato
                    LoginCandidatoView login = SpringContext.getBean(LoginCandidatoView.class);
                    login.setVisible(true);
                    this.dispose();
                } else if (escolha == 1) {// Empresa
                    LoginEmpresaView login = SpringContext.getBean(LoginEmpresaView.class);
                    login.setVisible(true);
                    this.dispose();
                }
                
                return;
        }
        
            try {
                if (usuario instanceof UsuarioCandidato) {
                PerfilCandidato perfil = perfilCandidatoController.buscarPerfilCompleto(usuario.getId());


                if (perfil != null) {
                    TelaApresentacaoCandidato tela =
                        SpringContext.getBean(TelaApresentacaoCandidato.class);
                    tela.setPerfil(perfil);
                    tela.setVisible(true);
                } else {
                    TelaEditarPerfil tela =
                        SpringContext.getBean(TelaEditarPerfil.class);

                    tela.setDados(usuario.getId()); // 👈 passe o usuário
                    tela.setVisible(true);
                    setVisible(false);
                }

                this.setVisible(false);
                
                } else if (usuario instanceof UsuarioEmpresa) {
                    
                    PerfilEmpresa perfil = perfilEmpresaController.buscarPerfilCompleto(usuario.getId());
    
                    // Independente de existir ou não, pegamos a instância gerenciada pelo Spring
                    TelaApresentacaoEmpresa telaApres = SpringContext.getBean(TelaApresentacaoEmpresa.class);

                    if(perfil != null){
                        telaApres.setPerfil(perfil);
                        telaApres.setVisible(true);
                    } else {
                        TelaEditarPerfilEmpresa telaEdit = SpringContext.getBean(TelaEditarPerfilEmpresa.class);
                        telaEdit.setDados(usuario.getId());
                        telaEdit.setTelaApresentacao(telaApres); // Passa a referência para o botão Salvar usar depois
                        telaEdit.setVisible(true);
                    }
                
                    setVisible(false);
                }
                
            } catch (Exception e) {
                logger.log(java.util.logging.Level.SEVERE, "Erro ao abrir perfil", e);
            }
            
       
    }//GEN-LAST:event_btnCadastroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnListarVagas;
    private javax.swing.JPanel jpnlTitulo;
    private javax.swing.JPanel jpnlVerVagas;
    private javax.swing.JLabel lblTextinho;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
