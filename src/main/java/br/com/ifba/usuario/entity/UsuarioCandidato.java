/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entidade que representa um usuário do tipo candidato.
 * Especializa a classe Usuario, adicionando informações
 * específicas para candidatos.
 * @author luiza
 */


@Entity
public class UsuarioCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
    private String telefone;

    @Email(message = "E-mail inválido")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    @Pattern(
        regexp = "(?=.*[A-Za-z])(?=.*\\d).*",
        message = "Senha deve conter letras e números"
    )
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
