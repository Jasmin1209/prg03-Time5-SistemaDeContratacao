/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.navegador;

import br.com.ifba.usuario.view.CadastroEmpresaView;
import br.com.ifba.usuario.view.EsqueciSenhaView;
import br.com.ifba.usuario.view.LoginEmpresaView;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class NavegadorTelas {

    private final ApplicationContext context;

    public NavegadorTelas(ApplicationContext context) {
        this.context = context;
    }

    public void abrirLoginEmpresa() {
        context.getBean(LoginEmpresaView.class).setVisible(true);
    }

    public void abrirCadastroEmpresa() {
        context.getBean(CadastroEmpresaView.class).setVisible(true);
    }

    public void abrirEsqueciSenha() {
        context.getBean(EsqueciSenhaView.class).setVisible(true);
    }
}

