/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.perfil.empresa.view;

import br.com.ifba.endereco.Endereco;
import br.com.ifba.infrastructure.spring.SpringContext;
import br.com.ifba.perfil.empresa.controller.PerfilEmpresaIController;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.telaPrincipal.view.TelaPrincipal;
import br.com.ifba.vaga.controller.VagaIController;
import br.com.ifba.vaga.entity.Vaga;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */

@Component 
@Scope("prototype")
public class TelaApresentacaoEmpresa extends javax.swing.JFrame {
    
    @Autowired
    private PerfilEmpresaIController perfilEmpresaController;
    
    @Autowired
    private VagaIController vagaController;
    private Long idEmpresa;
    private PerfilEmpresa perfilEmpresa;
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaApresentacaoEmpresa.class.getName());

    /**
     * Creates new form TelaApresentacao
     */
    public TelaApresentacaoEmpresa() {
        initComponents();
        setSize(800, 800);      // largura x altura
        setLocationRelativeTo(null); // centraliza na tela
        setResizable(false);   // impede redimensionamento
    }
    
    /* =========================
       PERFIL
       ========================= */
    public void setPerfil(PerfilEmpresa perfil) {
    if (perfil == null || perfil.getUsuarioEmpresa()== null) {
        JOptionPane.showMessageDialog(this, "Perfil inválido ou incompleto.");
        return;
    }

    this.perfilEmpresa = perfil;
    atualizarTela();
    }

    private void carregarDadosEmpresa() {
        
        if (perfilEmpresa == null) {
            JOptionPane.showMessageDialog(this, "Empresa não encontrada.");
            return;
        }

        lblnomeempresa.setText(perfilEmpresa.getUsuarioEmpresa().getNome());
        lblDescricao.setText(perfilEmpresa.getSobre());
        lblsetorempresa.setText(perfilEmpresa.getSetor());
        
        // ===== CONTATO =====
        lblEmail.setText(perfilEmpresa.getUsuarioEmpresa().getEmail());
        lblTelefone.setText(perfilEmpresa.getUsuarioEmpresa().getTelefone());
        
        if(perfilEmpresa.getSite() != null){
            lblsite.setText(perfilEmpresa.getSite());
        }
        
        // ===== ENDEREÇO =====
        if (perfilEmpresa.getEndereco() != null) {
            Endereco e = perfilEmpresa.getEndereco();
            lblpais.setText(e.getPais());
            lblcidade.setText(e.getCidade());
            lblestado.setText(e.getEstado());
            lblbairro.setText(e.getBairro());
            lblnumero.setText(e.getNumero() != null ? String.valueOf(e.getNumero()) : "");
        } else {
            // Limpa os labels caso não haja endereço
            lblpais.setText("");
            lblcidade.setText("");
            lblestado.setText("");
            lblbairro.setText("");
            lblnumero.setText("");
        }
    }
    
    /* ===============================
       CARREGAR VAGAS
       =============================== */
    private void carregarVagas() {
       // 1. Limpa o PAINEL interno (o que está dentro do scroll)
        pnlConteudoVagas.removeAll(); 

        try {
            List<Vaga> vagas = vagaController.findAll();

            if (vagas != null && !vagas.isEmpty()) {
                // 2. CORREÇÃO: Aplica layout vertical ao painel INTERNO
                pnlConteudoVagas.setLayout(new javax.swing.BoxLayout(pnlConteudoVagas, javax.swing.BoxLayout.Y_AXIS));

                for (Vaga vaga : vagas) {
                    javax.swing.JPanel card = new javax.swing.JPanel();
                    card.setBorder(javax.swing.BorderFactory.createTitledBorder(vaga.getTitulo()));
                    card.add(new javax.swing.JLabel("Modelo: " + vaga.getModelo()));
                    // Impede o card de esticar absurdamente
                    card.setMaximumSize(new java.awt.Dimension(400, 60)); 
                    pnlConteudoVagas.add(card);
                }
            } else {
                pnlConteudoVagas.add(new javax.swing.JLabel("Nenhuma vaga encontrada."));
            }
        } catch (Exception e) {
            pnlConteudoVagas.add(new javax.swing.JLabel("Erro ao carregar vagas."));
        }

        // 3. O SEGREDO DO SCROLL: Revalidar o painel interno
        pnlConteudoVagas.revalidate();
        pnlConteudoVagas.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        lblsobre = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblsetor = new javax.swing.JLabel();
        lblsetorempresa = new javax.swing.JLabel();
        lblvaga = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblinformacoes = new javax.swing.JLabel();
        pnlContato = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblsite = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        pnlEndereco = new javax.swing.JPanel();
        lblpais = new javax.swing.JLabel();
        lblcidade = new javax.swing.JLabel();
        lblestado = new javax.swing.JLabel();
        lblbairro = new javax.swing.JLabel();
        lblnumero = new javax.swing.JLabel();
        lblnomeempresa = new javax.swing.JLabel();
        btnEditarPerfil = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scrollpaneVagas = new javax.swing.JScrollPane();
        pnlConteudoVagas = new javax.swing.JPanel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblsobre.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblsobre.setText("SOBRE");
        getContentPane().add(lblsobre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        lblDescricao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 380, 90));

        lblsetor.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblsetor.setText("SETOR");
        getContentPane().add(lblsetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, -1));

        lblsetorempresa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lblsetorempresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 380, 26));

        lblvaga.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblvaga.setText("VAGAS");
        getContentPane().add(lblvaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblinformacoes.setBackground(new java.awt.Color(204, 0, 0));
        lblinformacoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblinformacoes.setText("INFORMAÇÕES DE CONTATO");
        jPanel2.add(lblinformacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        pnlContato.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTelefone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblsite.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("EMAIL");

        jLabel3.setText("TELEFONE");

        jLabel4.setText("SITE");

        javax.swing.GroupLayout pnlContatoLayout = new javax.swing.GroupLayout(pnlContato);
        pnlContato.setLayout(pnlContatoLayout);
        pnlContatoLayout.setHorizontalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContatoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))
                    .addGroup(pnlContatoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContatoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblsite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlContatoLayout.setVerticalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.add(pnlContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 380, 90));

        lblEndereco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEndereco.setText("ENDEREÇO");
        jPanel2.add(lblEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        pnlEndereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblpais.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblcidade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblestado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlEnderecoLayout = new javax.swing.GroupLayout(pnlEndereco);
        pnlEndereco.setLayout(pnlEnderecoLayout);
        pnlEnderecoLayout.setHorizontalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(lblcidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblestado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(lblbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblnumero, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addComponent(lblpais, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblcidade, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(lblestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnumero, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(lblbairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.add(pnlEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 380, 70));

        lblnomeempresa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblnomeempresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnomeempresa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lblnomeempresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 378, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 390, 240));

        btnEditarPerfil.setText("EDITAR PERFIL");
        btnEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 120, 20));

        jButton3.setText("VOLTAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 90, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("PERFIL EMPRESA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        javax.swing.GroupLayout pnlConteudoVagasLayout = new javax.swing.GroupLayout(pnlConteudoVagas);
        pnlConteudoVagas.setLayout(pnlConteudoVagasLayout);
        pnlConteudoVagasLayout.setHorizontalGroup(
            pnlConteudoVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        pnlConteudoVagasLayout.setVerticalGroup(
            pnlConteudoVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        scrollpaneVagas.setViewportView(pnlConteudoVagas);

        getContentPane().add(scrollpaneVagas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 380, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
        // TODO add your handling code here:
        TelaEditarPerfilEmpresa tela =
            SpringContext.getBean(TelaEditarPerfilEmpresa.class);

        tela.setDados(idEmpresa);                 // id da empresa logada
        tela.setTelaApresentacao(this);           // referência para voltar
        tela.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_btnEditarPerfilActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        TelaPrincipal tela = SpringContext.getBean(TelaPrincipal.class);
        tela.atualizarSessao();
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /* ===============================
       MÉTODO PARA ATUALIZAR A TELA
       =============================== */
    public void atualizarTela() {
        carregarDadosEmpresa();
        carregarVagas();
        setVisible(true);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblbairro;
    private javax.swing.JLabel lblcidade;
    private javax.swing.JLabel lblestado;
    private javax.swing.JLabel lblinformacoes;
    private javax.swing.JLabel lblnomeempresa;
    private javax.swing.JLabel lblnumero;
    private javax.swing.JLabel lblpais;
    private javax.swing.JLabel lblsetor;
    private javax.swing.JLabel lblsetorempresa;
    private javax.swing.JLabel lblsite;
    private javax.swing.JLabel lblsobre;
    private javax.swing.JLabel lblvaga;
    private javax.swing.JPanel pnlContato;
    private javax.swing.JPanel pnlConteudoVagas;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JScrollPane scrollpaneVagas;
    // End of variables declaration//GEN-END:variables
}
