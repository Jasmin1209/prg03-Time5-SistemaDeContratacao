/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.service.UsuarioCandidatoService;
import org.springframework.stereotype.Controller;

/**
 * Controller responsável por intermediar
 * as ações da view do candidato com a
 * camada de serviço.
 * Controla operações de cadastro e login.
 * @author luiza
 */
@Controller
public class UsuarioCandidatoController
        implements UsuarioCandidatoControllerInterface {

    private final UsuarioCandidatoService service;

    public UsuarioCandidatoController(UsuarioCandidatoService service) {
        this.service = service;
    }

    @Override
    public boolean login(String email, String senha) {
        service.login(email, senha);
        return true;
    }

    @Override
    public void cadastrar(UsuarioCandidato candidato) {
        service.cadastrar(candidato);
    }

    public void verificarEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
