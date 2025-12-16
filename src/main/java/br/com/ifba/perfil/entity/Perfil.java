/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

import br.com.ifba.endereco.Endereco;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

/*
 * A classe abstrata Perfil reúne atributos comuns a diferentes tipos de 
 * perfis promovendo a reutilização do código 
*/

@MappedSuperclass //Indica que a classe não será uma tabela, mas seus atributos serão usados como base para outras entidades
@Getter 
@Setter
@NoArgsConstructor 
public abstract class Perfil extends PersistenceEntity implements Serializable{
    
    //o uso de columnDefinition = "TEXT" permite armazenar textos longos
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String sobre;
    
   
    @OneToOne( //um endereço pertence a um perfil, e um perfil possui um endereço
            cascade = CascadeType.ALL,  //operações CRUD usadas em perfil serão refletidas em endereço
            fetch = FetchType.EAGER //estabelece uma conexão entre perfil e endereço
    )
    @JoinColumn(name = "endereco_id") //define a chave estrangeira que ficará nos perfis
    private Endereco endereco;
}
