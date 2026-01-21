/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;
import br.com.ifba.usuario.entity.UsuarioEmpresa;

/**
 * Interface do controller do usuário empresa.
 * Define os contratos das ações
 * entre view e service.
 * @author luiza
 */
public interface UsuarioEmpresaControllerInterface {

    boolean login(String email, String senha);

    void cadastrar(UsuarioEmpresa empresa);
}
