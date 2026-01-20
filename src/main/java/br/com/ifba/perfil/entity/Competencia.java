/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//classe de persistência
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.io.Serializable;

//Anotações JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//validação
import jakarta.validation.constraints.NotBlank;

//Lombok
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "competencias")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Competencia extends PersistenceEntity implements Serializable{
    
    @NotBlank //Anotação para que o campo não seja nulo nem vazio (para o JAVA)
    @Column(
            name = "titulo", 
            nullable = false, //o campo não pode ser nulo nem vazio (para o BANCO DE DADOS)
            length = 50 //tamanho máximo 
    )
    private String titulo;
    
    @ManyToOne(fetch = FetchType.LAZY) //MUITOS PARA UM: Muitas competências pode pertencer a um mesmo perfil
    @JoinColumn(name = "perfil_candidato_id", nullable = false) //define a chave estrangeira no banco de dados
    private PerfilCandidato perfilCandidato;

}
