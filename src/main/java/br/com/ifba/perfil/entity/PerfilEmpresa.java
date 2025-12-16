/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//Anotações JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Validações
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "perfil_empresa")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerfilEmpresa extends Perfil{
    
    @NotBlank
    @Column(name = "nome_da_empresa", nullable = false)
    private String nomeEmpresa;
    
    @NotBlank
    @Column(name = "setor_da_empresa", nullable = false)
    private String setor;
    
    @Column(name = "site", nullable = true) //o site pode ser nulo
    private String site;
}
