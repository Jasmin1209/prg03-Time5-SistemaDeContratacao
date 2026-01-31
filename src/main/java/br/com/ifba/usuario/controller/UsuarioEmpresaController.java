/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.service.UsuarioEmpresaService;
import org.springframework.stereotype.Controller;

/**
 * Controller responsável por intermediar
 * as ações da view da empresa com a
 * camada de serviço.
 * @author luiza
 */

@Controller
public class UsuarioEmpresaController
        implements UsuarioEmpresaControllerInterface {

    private final UsuarioEmpresaService service;

    public UsuarioEmpresaController(UsuarioEmpresaService service) {
        this.service = service;
    }

    @Override
    public boolean login(String email, String senha) {
        service.login(email, senha);
        return true;
    }

    @Override
    public void cadastrar(UsuarioEmpresa empresa) {
        service.cadastrar(empresa);
    }
}
