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


    
    public void atualizarTela() {

         if (perfilcandidato == null) return;

    // ===== USUÁRIO =====
    if (perfilcandidato.getUsuarioPerfil() != null) {
        lblcompletedname.setText(perfilcandidato.getUsuarioPerfil().getNome());
        lblEmail.setText(perfilcandidato.getUsuarioPerfil().getEmail());
        lblTelefone.setText(perfilcandidato.getUsuarioPerfil().getTelefone());
    }

    // ===== PERFIL =====
    lblSobreMimCandidato.setText(perfilcandidato.getSobre());
    lblSite.setText(perfilcandidato.getSite());

    // ===== ENDEREÇO =====
    if (perfilcandidato.getEndereco() != null) {
        String pais = perfilcandidato.getEndereco().getPais();
        // Se o país for nulo ou vazio, coloca um aviso para você saber que o erro é no banco
        lblPais.setText((pais == null || pais.isEmpty()) ? "País não informado" : pais);
        
        lblEstado.setText(perfilcandidato.getEndereco().getEstado());
        lblCidade.setText(perfilcandidato.getEndereco().getCidade());
        
        // Se houver um label específico de país fora do scroll, use-o também
        lblPaisCandidato.setText(lblPais.getText()); 
    }

    // ===== SITE =====
    String site = perfilcandidato.getSite();
    lblSite.setText((site == null || site.isEmpty()) ? "Site não informado" : site);
        
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
        
        setSize(800, 700);      // largura x altura
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
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnExcluiExperiencias = new javax.swing.JButton();
        btnExcluirFormacao = new javax.swing.JButton();
        btnExcluirCompetencia = new javax.swing.JButton();
        btnExcluirIdioma = new javax.swing.JButton();
        btnEditarExperiencias = new javax.swing.JButton();
        btnEditarFormacao = new javax.swing.JButton();
        btnEditarCompetencia = new javax.swing.JButton();
        btnEditarIdioma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jpnpainelcentral.setLayout(new javax.swing.BoxLayout(jpnpainelcentral, javax.swing.BoxLayout.LINE_AXIS));

        lblcompetence.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jpnpainelcentral.add(lblcompetence);

        getContentPane().add(jpnpainelcentral);

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(627, 800));
        jScrollPane2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseMoved(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(940, 1000));

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

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 255));
        jLabel5.setText("PERFIL CANDIDATO");

        jButton1.setText("VOLTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnExcluiExperiencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remover.png"))); // NOI18N
        btnExcluiExperiencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiExperienciasActionPerformed(evt);
            }
        });

        btnExcluirFormacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remover.png"))); // NOI18N
        btnExcluirFormacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFormacaoActionPerformed(evt);
            }
        });

        btnExcluirCompetencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remover.png"))); // NOI18N
        btnExcluirCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCompetenciaActionPerformed(evt);
            }
        });

        btnExcluirIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remover.png"))); // NOI18N
        btnExcluirIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirIdiomaActionPerformed(evt);
            }
        });

        btnEditarExperiencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editar.png"))); // NOI18N
        btnEditarExperiencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarExperienciasActionPerformed(evt);
            }
        });

        btnEditarFormacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editar.png"))); // NOI18N
        btnEditarFormacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFormacaoActionPerformed(evt);
            }
        });

        btnEditarCompetencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editar.png"))); // NOI18N
        btnEditarCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCompetenciaActionPerformed(evt);
            }
        });

        btnEditarIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editar.png"))); // NOI18N
        btnEditarIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarIdiomaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvarPerfil)
                .addGap(448, 448, 448))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jButton1)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcompletedname, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btncontact)
                            .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSobreMimCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEditarFormacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExcluirFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnInserirFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEditarExperiencias)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExcluiExperiencias)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnInserirExperiencias))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditarCompetencia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnExcluirCompetencia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnInserirCompetencia))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditarIdioma)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnExcluirIdioma)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnInserirIdiomas))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblcompletedname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncontact, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSobreMimCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(btnSalvarPerfil)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserirExperiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btnExcluiExperiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnInserirFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEditarFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnExcluirFormacao, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(btnInserirCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(btnExcluirIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInserirIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(btnEditarExperiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TelaPrincipal tela = SpringContext.getBean(TelaPrincipal.class);
        tela.atualizarSessao();
        tela.setVisible(true);
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
        TelaAdicionarIdioma tela =
        SpringContext.getBean(TelaAdicionarIdioma.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirIdiomasActionPerformed

    private void btnInserirCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirCompetenciaActionPerformed
        // TODO add your handling code here:
        TelaAdicionarCompetencia tela =
        SpringContext.getBean(TelaAdicionarCompetencia.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirCompetenciaActionPerformed

    private void btnInserirFormacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirFormacaoActionPerformed
        // TODO add your handling code here:
        TelaAdicionarFormacao tela =
        SpringContext.getBean(TelaAdicionarFormacao.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirFormacaoActionPerformed

    private void btnInserirExperienciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirExperienciasActionPerformed
        // TODO add your handling code here:
        TelaAdicionarExperiencia tela =
        SpringContext.getBean(TelaAdicionarExperiencia.class);

        tela.setDados(perfilcandidato.getId());
        tela.setTelaApresentacaoCandidato(this);
        tela.setVisible(true);
    }//GEN-LAST:event_btnInserirExperienciasActionPerformed

    private void jScrollPane2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseMoved

    private void btnExcluiExperienciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiExperienciasActionPerformed
        // TODO add your handling code here:
        TelaExcluirExperiencia tela = SpringContext.getBean(TelaExcluirExperiencia.class);
        tela.setDados(perfilcandidato.getId());
        tela.setVisible(true);
    }//GEN-LAST:event_btnExcluiExperienciasActionPerformed

    private void btnExcluirFormacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFormacaoActionPerformed
        // TODO add your handling code here:
        TelaExcluirFormacao tela = SpringContext.getBean(TelaExcluirFormacao.class);
        tela.setDados(perfilcandidato.getId());
        tela.setVisible(true);
    }//GEN-LAST:event_btnExcluirFormacaoActionPerformed

    private void btnExcluirCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCompetenciaActionPerformed
        // TODO add your handling code here:
        TelaExcluirCompetencia tela = SpringContext.getBean(TelaExcluirCompetencia.class);
        tela.setDados(perfilcandidato.getId());
        tela.setVisible(true);
    }//GEN-LAST:event_btnExcluirCompetenciaActionPerformed

    private void btnExcluirIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirIdiomaActionPerformed
        // TODO add your handling code here:
        TelaExcluirIdioma tela = SpringContext.getBean(TelaExcluirIdioma.class);
        tela.setDados(perfilcandidato.getId());
        tela.setVisible(true);
    }//GEN-LAST:event_btnExcluirIdiomaActionPerformed

    private void btnEditarExperienciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarExperienciasActionPerformed
        // TODO add your handling code here:
        // 1. Pega a tela de listagem de edição do contexto do Spring
        TelaEditarExperiencia telaLista = SpringContext.getBean(TelaEditarExperiencia.class);
    
        // 2. Passa o ID do perfil para carregar a lista correta
        telaLista.setDados(perfilcandidato.getId());
    
        // 3. Passa a referência desta tela (Apresentação) para que o botão voltar funcione
        telaLista.setTelaApresentacaoCandidato(this);
    
        // 4. Exibe a tela de seleção
        telaLista.setVisible(true);
    }//GEN-LAST:event_btnEditarExperienciasActionPerformed

    private void btnEditarFormacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFormacaoActionPerformed
        // TODO add your handling code here:
        // 1. Pega a tela de listagem de edição do contexto do Spring
        TelaEditarFormacao telaLista = SpringContext.getBean(TelaEditarFormacao.class);
    
        // 2. Passa o ID do perfil para carregar a lista correta
        telaLista.setDados(perfilcandidato.getId());
    
        // 3. Passa a referência desta tela (Apresentação) para que o botão voltar funcione
        telaLista.setTelaApresentacaoCandidato(this);
    
        // 4. Exibe a tela de seleção
        telaLista.setVisible(true);
    }//GEN-LAST:event_btnEditarFormacaoActionPerformed

    private void btnEditarCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCompetenciaActionPerformed
        // TODO add your handling code here:
        // 1. Pega a tela de listagem de edição do contexto do Spring
        TelaEditarCompetencia telaLista = SpringContext.getBean(TelaEditarCompetencia.class);
    
        // 2. Passa o ID do perfil para carregar a lista correta
        telaLista.setDados(perfilcandidato.getId());
    
        // 3. Passa a referência desta tela (Apresentação) para que o botão voltar funcione
        telaLista.setTelaApresentacaoCandidato(this);
    
        // 4. Exibe a tela de seleção
        telaLista.setVisible(true);
    }//GEN-LAST:event_btnEditarCompetenciaActionPerformed

    private void btnEditarIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarIdiomaActionPerformed
        // TODO add your handling code here:
        // 1. Pega a tela de listagem de edição do contexto do Spring
        TelaEditarIdoma telaLista = SpringContext.getBean(TelaEditarIdoma.class);
    
        // 2. Passa o ID do perfil para carregar a lista correta
        telaLista.setDados(perfilcandidato.getId());
    
        // 3. Passa a referência desta tela (Apresentação) para que o botão voltar funcione
        telaLista.setTelaApresentacaoCandidato(this);
    
        // 4. Exibe a tela de seleção
        telaLista.setVisible(true);
    }//GEN-LAST:event_btnEditarIdiomaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarCompetencia;
    private javax.swing.JButton btnEditarExperiencias;
    private javax.swing.JButton btnEditarFormacao;
    private javax.swing.JButton btnEditarIdioma;
    private javax.swing.JButton btnExcluiExperiencias;
    private javax.swing.JButton btnExcluirCompetencia;
    private javax.swing.JButton btnExcluirFormacao;
    private javax.swing.JButton btnExcluirIdioma;
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
    private javax.swing.JLabel jLabel5;
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