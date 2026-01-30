/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.vaga.view;

import br.com.ifba.endereco.Endereco;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.vaga.controller.VagaController;
import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.PeriodoContratacao;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;


/**
 *
 * @author Taila
 */
public class VagaListar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VagaListar.class.getName());

   // 🎨 Paleta de cores da tela
    private static final Color CARD = Color.WHITE;
    private static final Color TEXTO = new Color(33, 37, 41);

    private static final Color VERDE = new Color(46, 204, 113);
    private static final Color VERMELHO = new Color(231, 76, 60);
    private static final Color ROXO = new Color(110, 86, 207);

    private Usuario usuarioLogado;
    private VagaController vagaController;

    
    public VagaListar(Usuario usuarioLogado, VagaController vagaController) {
        this.usuarioLogado = usuarioLogado;
        this.vagaController = vagaController;

        initComponents();
        setLocationRelativeTo(null);

        btnCadastrar.setVisible(usuarioLogado instanceof UsuarioEmpresa);

        // Se o banco estiver vazio, adiciona cursos de exemplo
        if (vagaController.findAll().isEmpty()) {
                for (Vaga c : gerarVagasTeste()) {
                    vagaController.save(c);
                }
        }
        
        // Customização do painel onde as vagas serão "empilhadas"
        pnListarVagas.setBackground(new Color(245, 246, 250));
        pnListarVagas.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        // Melhora a velocidade do scroll com o mouse
        scroolVagas.getVerticalScrollBar().setUnitIncrement(16);
        setLocationRelativeTo(null);
    
        // Mostra o botão "Cadastrar" apenas se o usuário for empresa
        btnCadastrar.setVisible(usuarioLogado instanceof UsuarioEmpresa);

        carregarFiltros(); // Preenche os ComboBoxes de busca             
        carregarVagas(vagaController.findAll());// Busca e exibe todas as vagas do banco

    }

    
    
    /**
     * Preenche os filtros de busca com os valores dos Enums
     */
    private void carregarFiltros() {
        // Adiciona "Todos" como primeira opção para permitir filtro vazio
        cboxTipo.removeAllItems();
        cboxTipo.addItem("Todos");
        for (TipoContratacao tipo : TipoContratacao.values()) {
            cboxTipo.addItem(tipo.name());
        }

        cBoxModelo.removeAllItems();
        cBoxModelo.addItem("Todos");
        for (ModeloContratacao modelo : ModeloContratacao.values()) {
            cBoxModelo.addItem(modelo.name());
        }
    }

    /**
     * Cria visualmente um "Card" (quadrado branco) para cada vaga.
     * Isso permite que a lista seja infinita e dinâmica.
     */
    private JPanel criarCardVaga(Vaga vaga) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        // Cria uma borda com espaçamento interno (Padding) e uma linha cinza clara
        card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16),
            javax.swing.BorderFactory.createLineBorder(new Color(220, 220, 220))
        ));
        
        card.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, card.getPreferredSize().height));


        // ===== TÍTULO =====
        JLabel lblTitulo = new JLabel(vaga.getTitulo());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JSeparator separator = new JSeparator();

        JPanel pnTopo = new JPanel();
        pnTopo.setLayout(new BoxLayout(pnTopo, BoxLayout.Y_AXIS));
        pnTopo.setBackground(Color.WHITE);
        pnTopo.add(lblTitulo);
        pnTopo.add(Box.createVerticalStrut(6));
        pnTopo.add(separator);

       
        //===========EMPRESA==============
        String nomeEmpresa;
        if (usuarioLogado instanceof UsuarioEmpresa) {
            nomeEmpresa = usuarioLogado.getNome();
        } else {
            nomeEmpresa = "Empresa não informada";
        }
        JLabel lblEmpresa = new JLabel("🏢 " + nomeEmpresa);


        // ===== LOCALIZAÇÃO =====
        Endereco end = vaga.getLocalizacao();
        JLabel lblLocal = new JLabel(
            "📍 " + end.getCidade() + " - " + end.getEstado()
        );

        // ===== INFO =====
        JLabel lblInfo = new JLabel(
            vaga.getModelo() + " | " + vaga.getTipo() + " | " + vaga.getPeriodo()
        );

        // ===== SALÁRIO =====
        JLabel lblSalario = new JLabel(
            vaga.getFaixaSalarial() != null
                ? "💰 R$ " + String.format("%,d", vaga.getFaixaSalarial())
                : "💰 Salário a combinar"
        );
        lblSalario.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // ===== STATUS =====
        JLabel lblStatus = new JLabel(
            vaga.getStatus() ? "  Vaga ativa  " : "  Vaga encerrada  "
        );
        lblStatus.setOpaque(true);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setBackground(
            vaga.getStatus() ? new Color(46, 204, 113) : new Color(231, 76, 60)
        );

        // ===== PAINEL INFO =====
        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));
        pnInfo.setBackground(Color.WHITE);

        pnInfo.add(lblEmpresa);
        pnInfo.add(Box.createVerticalStrut(4));
        pnInfo.add(lblLocal);
        pnInfo.add(Box.createVerticalStrut(6));
        pnInfo.add(lblInfo);
        pnInfo.add(Box.createVerticalStrut(6));
        pnInfo.add(lblSalario);
        pnInfo.add(Box.createVerticalStrut(10));
        pnInfo.add(lblStatus);

        // ===== BOTÃO =====
        JButton btnDetalhes = new JButton("Ver Detalhes");
        btnDetalhes.setBackground(new Color(110, 86, 207));
        btnDetalhes.setForeground(Color.WHITE);
        btnDetalhes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDetalhes.setFocusPainted(false);
        btnDetalhes.setPreferredSize(new Dimension(140, 36));
        btnDetalhes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDetalhes.addActionListener(e -> {
            // Ao clicar, abre a tela de detalhes passando a vaga específica deste card
            VagaDetalhes tela = new VagaDetalhes(usuarioLogado, vagaController, vaga);
            tela.setVisible(true);
            this.dispose();
        });

        JPanel pnBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnBotao.setBackground(Color.WHITE);
        pnBotao.add(btnDetalhes);

        // ===== MONTAGEM =====
        JPanel pnCentro = new JPanel(new BorderLayout());
        pnCentro.setBackground(Color.WHITE);
        pnCentro.add(pnInfo, BorderLayout.CENTER);
        pnCentro.add(pnBotao, BorderLayout.EAST);

        card.add(pnTopo, BorderLayout.NORTH);
        card.add(pnCentro, BorderLayout.CENTER);

        return card;
    }
    
    private void carregarExemplos() {
    List<Vaga> exemplos = gerarVagasTeste();
    carregarVagas(exemplos);
}

    /**
     * Limpa o painel e adiciona os cards das vagas fornecidas
     */
    private void carregarVagas(List<Vaga> vagas) {
        pnListarVagas.removeAll();

        if (vagas == null || vagas.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhuma vaga encontrada.");
            lblVazio.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            lblVazio.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            pnListarVagas.add(lblVazio);
        } else {
            for (Vaga vaga : vagas) {
                JPanel card = criarCardVaga(vaga);
                pnListarVagas.add(card);
                pnListarVagas.add(Box.createVerticalStrut(12));// Espaço entre cards
            }
        }

        pnListarVagas.revalidate();
        pnListarVagas.repaint();
    }

    private List<Vaga> gerarVagasTeste() {
    List<Vaga> vagas = new ArrayList<>();

    Endereco end1 = new Endereco();
    end1.setCidade("Salvador");
    end1.setEstado("BA");

    Endereco end2 = new Endereco();
    end2.setCidade("Feira de Santana");
    end2.setEstado("BA");

    Endereco end3 = new Endereco();
    end3.setCidade("Irecê");
    end3.setEstado("BA");

    Vaga v1 = new Vaga();
    v1.setTitulo("Desenvolvedor Java Júnior");
    v1.setTipo(TipoContratacao.CLT);
    v1.setModelo(ModeloContratacao.PRESENCIAL);
    v1.setPeriodo(PeriodoContratacao.INTEGRAL);
    v1.setFaixaSalarial(3500L);
    v1.setLocalizacao(end1);
    v1.setStatus(true);

    Vaga v2 = new Vaga();
    v2.setTitulo("Estágio em Desenvolvimento");
    v2.setTipo(TipoContratacao.ESTAGIO);
    v2.setModelo(ModeloContratacao.HIBRIDO);
    v2.setPeriodo(PeriodoContratacao.MEIO_PERIODO);
    v2.setFaixaSalarial(1200L);
    v2.setLocalizacao(end2);
    v2.setStatus(true);

    Vaga v3 = new Vaga();
    v3.setTitulo("Analista de Sistemas");
    v3.setTipo(TipoContratacao.CLT);
    v3.setModelo(ModeloContratacao.REMOTO);
    v3.setPeriodo(PeriodoContratacao.INTEGRAL);
    v3.setFaixaSalarial(6500L);
    v3.setLocalizacao(end3);
    v3.setStatus(false); // encerrada

    Vaga v4 = new Vaga();
    v4.setTitulo("Suporte Técnico");
    v4.setTipo(TipoContratacao.CLT);
    v4.setModelo(ModeloContratacao.PRESENCIAL);
    v4.setPeriodo(PeriodoContratacao.INTEGRAL);
    v4.setFaixaSalarial(2200L);
    v4.setLocalizacao(end1);
    v4.setStatus(true);

    vagas.add(v1);
    vagas.add(v2);
    vagas.add(v3);
    vagas.add(v4);

    return vagas;
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnFiltros = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPesquisar = new javax.swing.JTextPane();
        cboxTipo = new javax.swing.JComboBox<>();
        cBoxModelo = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        scroolVagas = new javax.swing.JScrollPane();
        pnListarVagas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(txtPesquisar);

        cboxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTipoActionPerformed(evt);
            }
        });

        cBoxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cBoxModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxModeloActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pesquisar.png"))); // NOI18N
        btnBuscar.setText("Filtrar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cadastrar.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFiltrosLayout = new javax.swing.GroupLayout(pnFiltros);
        pnFiltros.setLayout(pnFiltrosLayout);
        pnFiltrosLayout.setHorizontalGroup(
            pnFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(btnCadastrar)
                .addGap(44, 44, 44))
        );
        pnFiltrosLayout.setVerticalGroup(
            pnFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnCadastrar)
                        .addComponent(cBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnListarVagas.setLayout(new javax.swing.BoxLayout(pnListarVagas, javax.swing.BoxLayout.Y_AXIS));
        scroolVagas.setViewportView(pnListarVagas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroolVagas)
                    .addComponent(pnFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroolVagas, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxTipoActionPerformed
        
    }//GEN-LAST:event_cboxTipoActionPerformed

    private void cBoxModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxModeloActionPerformed
        
    }//GEN-LAST:event_cBoxModeloActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    /**
     * Lógica de Filtro: Varre a lista completa e filtra por texto, tipo e modelo
     */
        String texto = txtPesquisar.getText().trim().toLowerCase();
        String tipoSelecionado = (String) cboxTipo.getSelectedItem();
        String modeloSelecionado = (String) cBoxModelo.getSelectedItem();
        
        List<Vaga> todasVagas = vagaController.findAll();
        List<Vaga> vagasFiltradas = new ArrayList<>();
        
        for(Vaga vaga : todasVagas) {
            //filtro do titulo
            boolean matchTexto = texto.isEmpty()
                || (vaga.getTitulo() != null 
                    && vaga.getTitulo().toLowerCase().contains(texto));
            
            //filtro por tipo
            boolean matchTipo = tipoSelecionado.equals("Todos")
                || vaga.getTipo().name().equals(tipoSelecionado);
            
            //filtro por modelo
            boolean matchModelo = modeloSelecionado.equals("Todos")
                || vaga.getModelo().name().equals(modeloSelecionado);
            
            if (matchTexto && matchTipo && matchModelo) {
            vagasFiltradas.add(vaga);
            }
        }
        
        carregarVagas(vagasFiltradas); // Recarrega a tela apenas com as filtradas
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
      VagaCadastrar telaCadastro = new VagaCadastrar(usuarioLogado, vagaController);
        telaCadastro.setVisible(true);
        this.dispose(); // fecha a tela de listagem
    }//GEN-LAST:event_btnCadastrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> cBoxModelo;
    private javax.swing.JComboBox<String> cboxTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnFiltros;
    private javax.swing.JPanel pnListarVagas;
    private javax.swing.JScrollPane scroolVagas;
    private javax.swing.JTextPane txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
