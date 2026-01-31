/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//Anotações JPA
import br.com.ifba.endereco.Endereco;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Validações
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "perfil_empresa")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerfilEmpresa extends Perfil{
    
    @OneToOne
    @JoinColumn(
            name = "usuarioEmpresa_id",
            nullable = false,
            unique = true
    )
    private Usuario usuarioEmpresa;
    
    @NotBlank
    @Column(name = "setor_da_empresa", nullable = false)
    private String setor;
    
    @Column(name = "site", nullable = true) //o site pode ser nulo
    private String site;
}
