/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Setter
@Getter
public class UsuarioCandidato extends Usuario{
 
    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    private String cpf;
 
}
