/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Classe abstrata que representa um usuário do sistema.
 * Centraliza atributos e comportamentos comuns aos tipos de usuário,
 * servindo como superclasse para as entidades especializadas.
 * @author luiza
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario extends PersistenceEntity {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Email(message = "E-mail inválido")
    @NotBlank
    private String email;
    
    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
    private String telefone;
    
    @NotBlank
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    @Pattern(
        regexp = "(?=.*[A-Za-z])(?=.*\\d).*",
        message = "Senha deve conter letras e números")
    private String senha;

    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void esqueciMinhaSenha() {
        System.out.println("Solicitação de redefinição de senha");
    }

    // Getters e Setters (exceto id, que já vem do Lombok)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

