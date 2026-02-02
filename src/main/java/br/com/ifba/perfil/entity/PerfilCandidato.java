/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

import br.com.ifba.usuario.entity.UsuarioCandidato;
//Anotações JPA
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

//Anotações Lombok
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCandidato extends Perfil{
    
    /*
        * cascade = ALL: operações CRUD serão refletidas diretamente
        * maapedBy: indica o "dono" do relacionamento através do atributo, onde fica a chave estrangeira
        * orphanRemoval = true: se o atributo for removida da coleção, será removida do banco 
        * OneToMany: Relação UM para MUITOS
        * OneToOne: Relação UM para UM
        * nullable = true: Indica que o campo não é obrigatório
        * BigDecimal: usado para valores monetários
        * LinkedHashSet: usada para evitar 
        * JoinColumn: Estabele a chave estrangeira
    */
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            unique = true
    )
    private UsuarioCandidato usuarioPerfil;
     
    @OneToMany( 
            mappedBy = "perfilCandidato",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Formacao> formacaoAcademica = new LinkedHashSet<>();
    
    @OneToMany(
            mappedBy = "perfilCandidato",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Experiencia> experiencias = new LinkedHashSet<>();
    
    @OneToMany(
        mappedBy = "perfilCandidato",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Competencia> competencias = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "perfilCandidato",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Idioma> idiomas = new LinkedHashSet<>();
    
    
    
}
