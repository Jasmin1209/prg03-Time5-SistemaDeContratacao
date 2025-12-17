/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;
import jakarta.persistence.Entity;

/**
 * Entidade que representa um usuário do tipo candidato.
 * Especializa a classe Usuario, adicionando informações
 * específicas para candidatos.
 * @author luiza
 */


@Entity
public class UsuarioCandidato extends Usuario {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

