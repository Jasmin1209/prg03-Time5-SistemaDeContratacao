/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.UsuarioCandidato;

/**
 * Interface da camada de serviço do usuário candidato.
 * Define as operações de negócio relacionadas
 * a cadastro e login.
 * @author luiza
 */
public interface UsuarioCandidatoServiceInterface {

     void cadastrar(UsuarioCandidato candidato);

     UsuarioCandidato login(String email, String senha);
     
     UsuarioCandidato findById(Long idCandidato);
}

