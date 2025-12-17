/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;
import jakarta.persistence.Entity;

/**
 * Entidade que representa um usuário do tipo empresa.
 * Especializa a classe Usuario, adicionando informações
 * específicas para empresas.
 * @author luiza
 */

@Entity
public class UsuarioEmpresa extends Usuario {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

