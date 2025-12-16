/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//classe Persistence
import br.com.ifba.infrastructure.entity.PersistenceEntity;

//Anotações JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Validações
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Tipos Java
import java.io.Serializable;
import java.time.LocalDate;

//Lombok
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "experiencias")
@Getter
@Setter
@NoArgsConstructor
public class Experiencia extends PersistenceEntity implements Serializable{
    
    
    @NotBlank //Não pode ser nulo para String
    @Column(name = "empresa", nullable = false)
    private String empresa;
    
    @NotBlank
    @Column(name = "cargo", nullable = false)
    private String cargo;
    
    @NotNull //Não pode ser nulo para Números
    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;
    
    @Column(name = "data_final", nullable = true) //a data final pode ser nula para indicar que o candidato ainda trabalha 
    private LocalDate dataFinal;
    
    @ManyToOne(fetch = FetchType.LAZY) // Várias experiências podem pertencer a um mesmo perfil
    @JoinColumn(name = "perfil_candidato_id", nullable = false) //define a chave estrangeira
    private PerfilCandidato perfilCandidato;
    
}
