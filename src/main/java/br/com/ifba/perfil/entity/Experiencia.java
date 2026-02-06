/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//classe Persistence

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
import java.time.LocalDate;
import lombok.Getter;

//Lombok
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "experiencias")
@Setter
@Getter
@NoArgsConstructor
public class Experiencia extends br.com.ifba.infrastructure.entity.PersistenceEntity{
    
    @NotBlank
    @Column(name = "cargo", nullable = false)
    private String cargo;
    
    @NotBlank //Não pode ser nulo para String
    @Column(name = "empresa", nullable = false)
    private String empresa;
    
    @NotNull //Não pode ser nulo para Números
    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;
    
    @Column(name = "data_final", nullable = true) //a data final pode ser nula para indicar que o candidato ainda trabalha 
    private LocalDate dataFinal;
    
    @ManyToOne(fetch = FetchType.LAZY) // Várias experiências podem pertencer a um mesmo perfil
    @JoinColumn(name = "perfil_candidato_id", nullable = false) //define a chave estrangeira
    private PerfilCandidato perfilCandidato;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experiencia)) return false;
        Experiencia other = (Experiencia) o;
        return getId() != null && getId().equals(other.getId());
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
