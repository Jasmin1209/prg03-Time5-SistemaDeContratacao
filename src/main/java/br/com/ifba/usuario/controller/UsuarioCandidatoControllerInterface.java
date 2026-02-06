/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.UsuarioCandidato;

/**
 * Interface do controller do usuário candidato.
 * Define as ações intermediárias entre
 * view e camada de serviço.
 * @author luiza
 */
public interface UsuarioCandidatoControllerInterface {

    Usuario login(String email, String senha) ;

    void cadastrar(UsuarioCandidato candidato);
    
    UsuarioCandidato findById(Long idCandidato);
    
    void verificarEmail(String email);
}
