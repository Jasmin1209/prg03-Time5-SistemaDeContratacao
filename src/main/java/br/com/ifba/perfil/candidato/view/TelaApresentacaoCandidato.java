/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.perfil.candidato.view;

import br.com.ifba.infrastructure.spring.SpringContext;
import br.com.ifba.perfil.candidato.controller.PerfilCandidatoIController;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.telaPrincipal.view.TelaPrincipal;
import java.awt.FlowLayout;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */

@Component
@Scope("prototype")
public class TelaApresentacaoCandidato extends javax.swing.JFrame {
    

    @Autowired
    private PerfilCandidatoIController perfilcandidatocontroller;
    
    private PerfilCandidato perfilcandidato;
    

    private JPanel painelExperiencias;
    private JPanel painelFormacoes;
    private JPanel painelIdiomas;
    private JPanel painelCompetencias;

    private void configurarPainelDinamico() {

    // Experiências
    painelExperiencias = new JPanel();
    painelExperiencias.setLayout(new BoxLayout(painelExperiencias, BoxLayout.Y_AXIS));
    jScrollPane4.setViewportView(painelExperiencias);

    // Formações
    painelFormacoes = new JPanel();
    painelFormacoes.setLayout(new BoxLayout(painelFormacoes, BoxLayout.Y_AXIS));
    jScrollPane3.setViewportView(painelFormacoes);

    // Idiomas
    painelIdiomas = new JPanel();
    painelIdiomas.setLayout(new BoxLayout(painelIdiomas, BoxLayout.Y_AXIS));
    jScrollPane6.setViewportView(painelIdiomas);

    // Competências
    painelCompetencias = new JPanel();
    painelCompetencias.setLayout(new FlowLayout(FlowLayout.LEFT));
    jScrollPane5.setViewportView(painelCompetencias);
    

}

    
    
    /* =========================
       PERFIL
       ========================= */
    public void setPerfil(PerfilCandidato perfil) {
    if (perfil == null || perfil.getUsuarioPerfil() == null) {
        JOptionPane.showMessageDialog(this, "Perfil inválido ou incompleto.");
        return;
    }

    this.perfilcandidato = perfil;
    atualizarTela();
}


    
    private void atualizarTela() {

         if (perfilcandidato == null) return;

    // ===== USUÁRIO =====
    if (perfilcandidato.getUsuarioPerfil() != null) {
        lblcompletedname.setText(perfilcandidato.getUsuarioPerfil().getNome());
        lblEmail.setText(perfilcandidato.getUsuarioPerfil().getEmail());
        lblTelefone.setText(perfilcandidato.getUsuarioPerfil().getTelefone());
    }

    // ===== PERFIL =====
    lblSobreMimCandidato.setText(perfilcandidato.getSobre());

    // ===== ENDEREÇO =====
    if (perfilcandidato.getEndereco() != null) {
        lblPais.setText(perfilcandidato.getEndereco().getPais());
        lblEstado.setText(perfilcandidato.getEndereco().getEstado());
        lblCidade.setText(perfilcandidato.getEndereco().getCidade());
    } else {
        lblPais.setText("");
        lblEstado.setText("");
        lblCidade.setText("");
    }
        
        carregarExperiencias();
        carregarFormacoes();
        carregarCompetencias();
        carregarIdiomas();
        
        jPanel1.revalidate();
        jPanel1.repaint();
        jPanel1.setPreferredSize(jPanel1.getLayout().preferredLayoutSize(jPanel1));
    }
    
    /* =========================
       EXPERIÊNCIAS
       ========================= */
    private void carregarExperiencias() {
    painelExperiencias.removeAll();

    Set<Experiencia> experiencias = perfilcandidato.getExperiencias();

    if (experiencias != null) {
        experiencias.forEach(exp -> {
            painelExperiencias.add(criarPainelExperiencia(exp));
        });
    }

    painelExperiencias.revalidate();
    painelExperiencias.repaint();
}


    private JPanel criarPainelExperiencia(Experiencia exp) {
    JPanel p = new JPanel();
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    p.setBorder(BorderFactory.createTitledBorder("Experiência"));

    p.add(new JLabel("Cargo: " + exp.getCargo()));
    p.add(new JLabel("Empresa: " + exp.getEmpresa()));
    p.add(new JLabel("Início: " + exp.getDataInicial()));
    p.add(new JLabel(exp.getDataFinal() != null ? "Fim: " + exp.getDataFinal() : "Atual"));

    return p;
}
    
    /* =========================
       FORMAÇÕES
       ========================= */
    private void carregarFormacoes() {
    painelFormacoes.removeAll();

    Set<Formacao> formacoes = perfilcandidato.getFormacaoAcademica();

    if (formacoes != null) {
        formacoes.forEach(f -> {
            painelFormacoes.add(criarPainelFormacao(f));
        });
    }

    painelFormacoes.revalidate();
    painelFormacoes.repaint();
}

private JPanel criarPainelFormacao(Formacao f) {
    JPanel p = new JPanel();
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    p.setBorder(BorderFactory.createTitledBorder("Formação"));

    p.add(new JLabel("Instituição: " + f.getInstituicao()));
    p.add(new JLabel("Curso: " + f.getNomeDocurso()));
    p.add(new JLabel("Tipo: " + f.getTipo()));
    p.add(new JLabel("Início: " + f.getDataInicial()));
    p.add(new JLabel(f.getDataFinal() != null ? "Fim: " + f.getDataFinal() : "Atual"));

    return p;
}

    private void carregarCompetencias() {
    painelCompetencias.removeAll();

    if (perfilcandidato.getCompetencias() != null) {
        perfilcandidato.getCompetencias().forEach(c -> {
            painelCompetencias.add(criarPainelCompetencia(c.getTitulo()));
        });
    }

    painelCompetencias.revalidate();
    painelCompetencias.repaint();
}



private JPanel criarPainelCompetencia(String nome) {
    JPanel p = new JPanel();
    p.setBorder(BorderFactory.createEtchedBorder());
    p.add(new JLabel(nome));
    return p;
}

private void carregarIdiomas() {
    painelIdiomas.removeAll();

    if (perfilcandidato.getIdiomas() != null) {
        perfilcandidato.getIdiomas().forEach(i -> {
            painelIdiomas.add(
                criarPainelIdioma(i.getIdioma(), i.getNivel().name())
            );
        });
    }

    painelIdiomas.revalidate();
    painelIdiomas.repaint();
}


private JPanel criarPainelIdioma(String nome, String nivel) {
    JPanel p = new JPanel();
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    p.setBorder(BorderFactory.createTitledBorder("Idioma"));

    p.add(new JLabel("Idioma: " + nome));
    p.add(new JLabel("Nível: " + nivel));

    return p;
}

    /* =========================
       RECARREGAR PERFIL
       ========================= */
    public void recarregarPerfil() {
        if (perfilcandidato == null || perfilcandidato.getUsuarioPerfil() == null) return;

        PerfilCandidato atualizado =
            perfilcandidatocontroller.findByUsuarioPerfilId(
                perfilcandidato.getUsuarioPerfil().getId()
            );

        setPerfil(atualizado);
    }
    
    public TelaApresentacaoCandidato() {
        initComponents();
        configurarPainelDinamico();
        
        setSize(600, 3000);      // largura x altura
        setLocationRelativeTo(null); // centraliza na tela
        setResizable(false);   // impede redimensionamento
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnpainelcentral = new javax.swing.JPanel();
        lblcompetence = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        btnInserirExperiencias = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        btnInserirFormacao = new javax.swing.JButton();
        btnInserirCompetencia = new javax.swing.JButton();
        btnInserirIdiomas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblCidade = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblPais = new javax.swing.JLabel();
        lblpais = new javax.swing.JLabel();
        lblPaisCandidato = new javax.swing.JLabel();
        pnlContato = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblSite = new javax.swing.JLabel();
        btncontact = new javax.swing.JButton();
        lblcompletedname = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSobreMimCandidato = new javax.swing.JLabel();
        btnSalvarPerfil = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jpnpainelcentral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblcompetence.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jpnpainelcentral.add(lblcompetence, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(627, 6000));
        jScrollPane2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseMoved(evt);
            }
        });

        jLabel2.setText("EXPERIÊNCIAS");

        btnInserirExperiencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cadastrar.png"))); // NOI18N
        btnInserirExperiencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirExperienciasActionPerformed(evt);
            }
        });

        jLabel6.setText("FORMAÇÃO");

        jLabel3.setText("COMPETÊNCIA");

        jLabel7.setText("IDIOMAS");

        btnInserirFormacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cadastrar.png"))); // NOI18N
        btnInserirFormacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirFormacaoActionPerformed(evt);
            }
        });

        btnInserirCompetencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cadastrar.png"))); // NOI18N
        btnInserirCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirCompetenciaActionPerformed(evt);
            }
        });

        btnInserirIdiomas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cadastrar.png"))); // NOI18N
        btnInserirIdiomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirIdiomasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ENDEREÇO");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCidade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(lblCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 20));

        lblEstado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 40, 20));

        lblPais.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(lblPais);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 50, 200));
        jPanel3.add(lblpais, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblPaisCandidato.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(lblPaisCandidato, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 20));

        pnlContato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlContato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlContato.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 20));

        lblTelefone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlContato.add(lblTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 250, 20));

        lblSite.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlContato.add(lblSite, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 400, 20));

        btncontact.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncontact.setText("INFORMAÇÕES DE CONTATO");
        btncontact.setBorder(null);
        btncontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncontactActionPerformed(evt);
            }
        });

        lblcompletedname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("SOBRE MIM");

        lblSobreMimCandidato.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSalvarPerfil.setText("EDITAR PERFIL");
        btnSalvarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPerfilActionPerformed(evt);
            }
        });

        jButton1.setText("VOLTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(btnSalvarPerfil)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInserirFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInserirIdiomas))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInserirCompetencia))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInserirExperiencias, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSobreMimCandidato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(lblcompletedname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btncontact)
                            .addComponent(pnlContato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcompletedname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncontact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSobreMimCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvarPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInserirExperiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(btnInserirFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(btnInserirCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnInserirIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jpnpainelcentral.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -10, 540, 940));

        getContentPane().add(jpnpainelcentral);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPerfilActionPerformed
        // TODO add your handling code here:
        TelaEditarPerfil tela =
        SpringContext.getBean(TelaEditarPerfil.class);

        tela.setDados(perfilcandidato.getUsuarioPerfil().getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnSalvarPerfilActionPerformed

    private void btncontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncontactActionPerformed

    private void btnInserirIdiomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirIdiomasActionPerformed
        // TODO add your handling code here:
        TelaEditarIdioma tela =
        SpringContext.getBean(TelaEditarIdioma.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirIdiomasActionPerformed

    private void btnInserirCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirCompetenciaActionPerformed
        // TODO add your handling code here:
        TelaEditarCompetencia tela =
        SpringContext.getBean(TelaEditarCompetencia.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirCompetenciaActionPerformed

    private void btnInserirFormacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirFormacaoActionPerformed
        // TODO add your handling code here:
        TelaEditarFormacao tela =
        SpringContext.getBean(TelaEditarFormacao.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirFormacaoActionPerformed

    private void btnInserirExperienciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirExperienciasActionPerformed
        // TODO add your handling code here:
        TelaEditarExperiencia tela =
        SpringContext.getBean(TelaEditarExperiencia.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirExperienciasActionPerformed

    private void jScrollPane2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseMoved



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInserirCompetencia;
    private javax.swing.JButton btnInserirExperiencias;
    private javax.swing.JButton btnInserirFormacao;
    private javax.swing.JButton btnInserirIdiomas;
    private javax.swing.JButton btnSalvarPerfil;
    private javax.swing.JButton btncontact;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpnpainelcentral;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPaisCandidato;
    private javax.swing.JLabel lblSite;
    private javax.swing.JLabel lblSobreMimCandidato;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblcompetence;
    private javax.swing.JLabel lblcompletedname;
    private javax.swing.JLabel lblpais;
    private javax.swing.JPanel pnlContato;
    // End of variables declaration//GEN-END:variables

    

}