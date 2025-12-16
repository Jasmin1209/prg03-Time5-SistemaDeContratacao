/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

//Anotações JPA
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Tipos Java
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

//Anotações Lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "perfil_candidato")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCandidato extends Perfil{
    
    /*
        * cascade = ALL: operações CRUD serão refletidas diretamente
        * maapedBy: indica o "dono" do relacionamento através do atributo, onde fica a chave estrangeira
        * orphanRemoval = true: se o atributo for removida da coleção, será removida do banco 
        * OneToMany: Relação UM para MUITOS
        * nullable = true: Indica que o campo não é obrigatório
        * BigDecimal: usado para valores monetários
        * LinkedHashSet: usada para evitar 
    */
    
    @OneToMany( 
            mappedBy = "perfil_candidato_formacao",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Formacao> formacaoAcademica = new LinkedHashSet<>();
    
    @OneToMany(
            mappedBy = "perfil_candidato_experiencia",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Experiencia> experiencias = new LinkedHashSet<>();
    
    @OneToMany(
        mappedBy = "perfil_candidato_competencia",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Competencia> competencias = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "perfil_candidato_idioma",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Idioma> idiomas = new LinkedHashSet<>();
    
    @Column(name = "pretencao_salarial", nullable = true)
    private BigDecimal pretencaoSalarial;
}
