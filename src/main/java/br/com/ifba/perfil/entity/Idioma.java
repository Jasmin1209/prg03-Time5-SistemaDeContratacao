/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//Classe Persistence 
import br.com.ifba.infrastructure.entity.PersistenceEntity;

//Classe ENUM
import br.com.ifba.perfil.enums.Nivel;

//Anotações JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Validações
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

//Lombok
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "idiomas")
@Getter
@Setter
@NoArgsConstructor
public class Idioma extends PersistenceEntity implements Serializable{
    
    @NotBlank
    @Column(name = "idioma", nullable = false, length = 20) //length estabelece o tamanho máximo
    private String idioma;
    
    @Enumerated(EnumType.STRING) //Armazena um ENUM
    @Column(nullable = false)
    private Nivel nivel;
    
    @ManyToOne(fetch = FetchType.LAZY) //MUITOS PARA UM 
    @JoinColumn(name = "perfil_candidato_id", nullable = false)
    private PerfilCandidato perfilCandidato;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Idioma)) return false;
        Idioma other = (Idioma) o;
        return getId() != null && getId().equals(other.getId());
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
