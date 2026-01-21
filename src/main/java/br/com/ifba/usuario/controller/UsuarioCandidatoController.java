/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

/**
 * Controller responsável por intermediar as ações
 * entre as telas de candidato e a camada de serviço.
 * Controla operações de login e cadastro.
 * @author luiza
 */


import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.service.UsuarioCandidatoService;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioCandidatoController {

    private final UsuarioCandidatoService service;

    public UsuarioCandidatoController(UsuarioCandidatoService service) {
        this.service = service;
    }

    public boolean login(String email, String senha) {
        return service.login(email, senha);
    }

    public void cadastrar(UsuarioCandidato candidato) {
        service.cadastrar(candidato);
    }
}
