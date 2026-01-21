/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;
import br.com.ifba.usuario.entity.UsuarioEmpresa;

/**
 * Interface da camada de serviço do usuário empresa.
 * Centraliza os contratos de regras de negócio
 * para cadastro e autenticação.
 * @author luiza
 */
public interface UsuarioEmpresaServiceInterface {

    void cadastrar(UsuarioEmpresa empresa);

    boolean login(String email, String senha);
}
