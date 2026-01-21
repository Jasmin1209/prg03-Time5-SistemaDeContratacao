/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

/**
 * Controller responsável por gerenciar as ações
 * relacionadas ao usuário do tipo empresa.
 * Atua como intermediário entre view e service.
 * @author luiza
 */


import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.service.UsuarioEmpresaService;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioEmpresaController {

    private final UsuarioEmpresaService service;

    public UsuarioEmpresaController(UsuarioEmpresaService service) {
        this.service = service;
    }

    public boolean login(String email, String senha) {
        return service.login(email, senha);
    }

    public void cadastrar(UsuarioEmpresa empresa) {
        service.cadastrar(empresa);
    }
}

