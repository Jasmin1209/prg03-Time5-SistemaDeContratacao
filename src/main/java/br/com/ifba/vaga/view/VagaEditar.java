/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.vaga.view;

import br.com.ifba.candidaturas.view.CandidaturaListar;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.sessao.SessaoUsuario;
import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.PeriodoContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.controller.VagaController;
import java.awt.Color;


import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Taila
 */

@Component
@Scope("prototype")
public class VagaEditar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VagaEditar.class.getName());

    
    // 🎨 Paleta de Cores: Azul Profissional
    private static final Color AZUL_CLARO = new Color(71, 178, 240);  // #47b2f0
    private static final Color AZUL_MEDIO = new Color(54, 150, 209);  // #3696d1
    private static final Color AZUL_BASE  = new Color(36, 121, 178);  // #2479b2
    private static final Color AZUL_FORTE = new Color(18, 92, 146);   // #125c92
    private static final Color AZUL_FUNDO = new Color(0, 63, 115);    // #003f73
    
    private final SessaoUsuario sessaoUsuario;
    private Usuario usuarioLogado;
    private VagaController vagaController;
    private Vaga vaga;
    private ButtonGroup  grupoStatus;
    
    
    /**
     * Creates new form VagaEditar
     */
    @Autowired
    public VagaEditar(SessaoUsuario sessaoUsuario, VagaController vagaController, Vaga vaga) {
        this.usuarioLogado = sessaoUsuario.getUsuario();
        this.vagaController = vagaController;
        this.vaga = vaga;
        this.sessaoUsuario = sessaoUsuario;

        initComponents();
        
        // --- Configuração do Campo de Descrição ---
        txtDescricao.setLineWrap(true);      // Faz o texto pular para a linha seguinte
        txtDescricao.setWrapStyleWord(true); // Evita que palavras sejam cortadas ao meio
        
        estilizarBotoes();
        configurarTela();
    }
    
    private void configurarTela() {
       //setSize(677, 567);
       setLocationRelativeTo(null);  
       
       jLabel1.setForeground(AZUL_FUNDO); // Aplica o azul mais escuro no "Editar Vaga"
       
       grupoStatus = new ButtonGroup();
       grupoStatus.add(jrdAtiva);
       grupoStatus.add(jrdEncerrar);
        
       jboxModelo.setModel(new DefaultComboBoxModel<>(ModeloContratacao.values()));
       jboxTipo.setModel(new DefaultComboBoxModel<>(TipoContratacao.values()));
       jboxPeriodo.setModel(new DefaultComboBoxModel<>(PeriodoContratacao.values()));
        
        if (vaga != null) {
            carregarDadosVagas();
        }

        // Habilita excluir apenas se a vaga já existir no banco (tiver ID)
        btnExcluir.setEnabled(vaga != null && vaga.getId() != null);
    }
    
    private boolean validarCampos() {
    if (txtTitulo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "O título é obrigatório.");
        return false;
    }

    if (txtQtd.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Informe a quantidade de vagas.");
        return false;
    }

    try {
        int qtd = Integer.parseInt(txtQtd.getText());
        if (qtd <= 0) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantidade deve ser um número inteiro.");
        return false;
    }

    if (!txtSalario.getText().trim().isEmpty()) {
        try {
            Long.parseLong(txtSalario.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salário deve conter apenas números.");
            return false;
        }
    }

    return true;
}

    
    private void preencherVaga() {
        vaga.setTitulo(txtTitulo.getText().trim());
        vaga.setDescricao(txtDescricao.getText().trim());
        vaga.setQuantidade(Integer.parseInt(txtQtd.getText().trim()));

        if (!txtSalario.getText().trim().isEmpty()) {
            vaga.setFaixaSalarial(Long.parseLong(txtSalario.getText().trim()));
        } else {
            vaga.setFaixaSalarial(null);
        }

        if (vaga.getLocalizacao() != null) {
                vaga.getLocalizacao().setCidade(txtCidade.getText());
            }
        
        vaga.setTipo((TipoContratacao) jboxTipo.getSelectedItem());
        vaga.setModelo((ModeloContratacao) jboxModelo.getSelectedItem());
        vaga.setPeriodo((PeriodoContratacao) jboxPeriodo.getSelectedItem());
        vaga.setStatus(jrdAtiva.isSelected());
}

    
    private void estilizarBotoes() {
        //Botão Salvar (Ação Principal) - Usando o Azul Base para destaque
        btnSalvar.setBackground(AZUL_BASE); 
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.setFocusPainted(false);

        //Botão Cancelar (Ação Secundária) - Usando o Azul Claro
        btnCancelar.setBackground(AZUL_CLARO);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);

        // Botão Candidatos - Usando o Azul Médio para diferenciar
        btnCandidatos.setBackground(AZUL_MEDIO);
        btnCandidatos.setForeground(Color.WHITE);
        btnCandidatos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Botão Excluir 
        btnExcluir.setBackground(new java.awt.Color(231, 76, 60)); // Vermelho Alerta
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}
    
    private void carregarDadosVagas() {
        if(vaga == null) {
            JOptionPane.showMessageDialog(this, "Erro: vaga inválida.");
            dispose();
            return;
        }
        
        //Preenchimento de campos de texto simples
        txtTitulo.setText(vaga.getTitulo());
        txtQtd.setText(String.valueOf(vaga.getQuantidade()));

        //Configuração da Descrição com reset de scroll
        txtDescricao.setText(vaga.getDescricao());
        txtDescricao.setCaretPosition(0); // Garante que o texto comece no topo

        //Proteção contra Localização nula para evitar NullPointerException
        if (vaga.getLocalizacao() != null) {
            txtCidade.setText(vaga.getLocalizacao().getCidade());
        } else {
            txtCidade.setText("");
        }

        //Verifica se o salário é nulo antes de converter para String
        txtSalario.setText(vaga.getFaixaSalarial() != null ? String.valueOf(vaga.getFaixaSalarial()) : "");

        jboxTipo.setSelectedItem(vaga.getTipo());
        jboxModelo.setSelectedItem(vaga.getModelo());
        jboxPeriodo.setSelectedItem(vaga.getPeriodo());
        
        if (vaga.getStatus() != null && vaga.getStatus()) {
            jrdAtiva.setSelected(true);
        } else {
            jrdEncerrar.setSelected(true);
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

        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblQtd = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblSalario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jboxPeriodo = new javax.swing.JComboBox<>();
        lblPeriodo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        jrdAtiva = new javax.swing.JRadioButton();
        jrdEncerrar = new javax.swing.JRadioButton();
        jboxTipo = new javax.swing.JComboBox<>();
        jboxModelo = new javax.swing.JComboBox<>();
        btnExcluir = new javax.swing.JButton();
        btnCandidatos = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        txtQtd = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtSalario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("Editar Vaga");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitulo.setText("Título da vaga:");

        lblQtd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQtd.setText("Quantidade de vaga:");

        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipo.setText("Tipo de contratação:");

        lblSalario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSalario.setText("faixa salarial:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        btnCancelar.setText("<---");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("");
        btnSalvar.setMaximumSize(new java.awt.Dimension(88, 27));
        btnSalvar.setMinimumSize(new java.awt.Dimension(88, 27));
        btnSalvar.setPreferredSize(new java.awt.Dimension(88, 27));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Cidade da vaga:");

        jboxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(PeriodoContratacao.values()));
        jboxPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jboxPeriodoActionPerformed(evt);
            }
        });

        lblPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPeriodo.setText("Período de contratação:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Status da vaga:");

        lblModelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblModelo.setText("Modelo de contratação:");

        jrdAtiva.setText("Ativa");
        jrdAtiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdAtivaActionPerformed(evt);
            }
        });

        jrdEncerrar.setText("Encerrada");
        jrdEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdEncerrarActionPerformed(evt);
            }
        });

        jboxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(TipoContratacao.values()));

        jboxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(ModeloContratacao.values()));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remover.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCandidatos.setText("Candidatos");
        btnCandidatos.setMaximumSize(new java.awt.Dimension(88, 27));
        btnCandidatos.setMinimumSize(new java.awt.Dimension(88, 27));
        btnCandidatos.setPreferredSize(new java.awt.Dimension(88, 27));
        btnCandidatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCandidatosActionPerformed(evt);
            }
        });

        txtTitulo.setText("jTextField1");

        txtQtd.setText("jTextField1");

        txtCidade.setText("jTextField1");

        txtSalario.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(27, 27, 27)
                        .addComponent(jrdAtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jrdEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir)
                        .addGap(174, 174, 174))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblQtd)
                                    .addComponent(lblModelo)
                                    .addComponent(lblTipo)
                                    .addComponent(lblTitulo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jboxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(lblSalario))
                                    .addComponent(lblPeriodo)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jboxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCandidatos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitulo)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQtd)
                            .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jboxModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblModelo))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTipo)
                                    .addComponent(jboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeriodo)
                            .addComponent(jboxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSalario)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jrdAtiva)
                    .addComponent(jrdEncerrar))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new VagaDetalhes(sessaoUsuario, vagaController, vaga).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (!validarCampos()) {
                return;
            }

        preencherVaga(); // Atualiza o objeto 'vaga' com o que está na tela
        vagaController.update(vaga); // Envia para o banco de dados

        JOptionPane.showMessageDialog(this, "Vaga atualizada com sucesso!");
        
        // Abre a tela de detalhes atualizada e fecha a de edição
        new VagaDetalhes(sessaoUsuario, vagaController, vaga).setVisible(true);
        dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a vaga: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jboxPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jboxPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jboxPeriodoActionPerformed

    private void jrdAtivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrdAtivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrdAtivaActionPerformed

    private void jrdEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrdEncerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrdEncerrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       int opcao = JOptionPane.showConfirmDialog(
        this,
        "Tem certeza que deseja excluir esta vaga?",
        "Confirmar exclusão",
        JOptionPane.YES_NO_OPTION
        );
       
       if(opcao == JOptionPane.YES_OPTION) {
           try {
               // Passando o objeto Vaga inteiro como o seu Service exige
               vagaController.delete(vaga);
               JOptionPane.showMessageDialog(this, "Vaga excluída!");
               new VagaListar(sessaoUsuario, vagaController).setVisible(true);
               this.dispose();
           } catch (Exception e) {
               JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
           }
       }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCandidatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCandidatosActionPerformed
        new CandidaturaListar().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCandidatosActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCandidatos;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<ModeloContratacao> jboxModelo;
    private javax.swing.JComboBox<PeriodoContratacao> jboxPeriodo;
    private javax.swing.JComboBox<TipoContratacao> jboxTipo;
    private javax.swing.JRadioButton jrdAtiva;
    private javax.swing.JRadioButton jrdEncerrar;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblQtd;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtQtd;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
