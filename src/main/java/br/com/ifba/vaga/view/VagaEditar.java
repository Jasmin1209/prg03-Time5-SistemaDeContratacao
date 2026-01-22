/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.vaga.view;

import br.com.ifba.candidaturas.view.CandidaturaListar;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.PeriodoContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.service.VagaService;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Taila
 */
public class VagaEditar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VagaEditar.class.getName());

    private Usuario usuarioLogado;
    private VagaService vagaService;
    private Vaga vaga;
    private ButtonGroup  grupoStatus;
    
    
    /**
     * Creates new form VagaEditar
     */
    public VagaEditar(Usuario usuarioLogado, VagaService vagaService, Vaga vaga) {
        this.usuarioLogado = usuarioLogado;
        this.vagaService = vagaService;
        this.vaga = vaga;

        initComponents();
        estilizarBotoes();
        configurarTela();
    }
    
    private void configurarTela() {
       setLocationRelativeTo(null);       
       
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
        btnSalvar.setBackground(new java.awt.Color(76, 175, 80));
        btnSalvar.setForeground(java.awt.Color.WHITE);
        btnSalvar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        btnCancelar.setBackground(new java.awt.Color(244, 67, 54));
        btnCancelar.setForeground(java.awt.Color.WHITE);
        btnCancelar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    }
    
    private void carregarDadosVagas() {
        if(vaga == null) {
            JOptionPane.showMessageDialog(this, "Erro: vaga inválida.");
            dispose();
            return;
        }
        
        txtTitulo.setText(vaga.getTitulo());
        txtQtd.setText(String.valueOf(vaga.getQuantidade()));
        txtDescricao.setText(vaga.getDescricao());
        
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
        txtSalario = new java.awt.TextField();
        txtCidade = new java.awt.TextField();
        txtTitulo = new java.awt.TextField();
        txtQtd = new java.awt.TextField();
        btnCandidatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
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

        txtSalario.setText("textField1");

        txtCidade.setText("textField1");

        txtTitulo.setText("textField1");

        txtQtd.setText("textField2");

        btnCandidatos.setText("Candidatos");
        btnCandidatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCandidatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jrdAtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrdEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar)
                                .addGap(99, 99, 99)
                                .addComponent(btnCandidatos))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcluir))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(lblSalario))
                                    .addComponent(lblPeriodo)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jboxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel9))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluir))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTitulo)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQtd))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSalario)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jrdAtiva)
                    .addComponent(jrdEncerrar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCandidatos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new VagaDetalhes(usuarioLogado, vagaService, vaga).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
         try {
        if (!validarCampos()) {
            return;
        }

        preencherVaga();
        vagaService.update(vaga);

        JOptionPane.showMessageDialog(this, "Vaga atualizada com sucesso!");
        new VagaDetalhes(usuarioLogado, vagaService, vaga).setVisible(true);
        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar a vaga.");
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
               vagaService.delete(vaga);
               JOptionPane.showMessageDialog(this, "Vaga excluída!");
               new VagaListar(usuarioLogado, vagaService).setVisible(true);
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
    private java.awt.TextField txtCidade;
    private javax.swing.JTextArea txtDescricao;
    private java.awt.TextField txtQtd;
    private java.awt.TextField txtSalario;
    private java.awt.TextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
