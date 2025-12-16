/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;

//ENUM
import br.com.ifba.perfil.Enum.TipoFormacao;

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
@Table(name = "formacoes")
@Getter
@Setter
@NoArgsConstructor
public class Formacao extends PersistenceEntity implements Serializable{
    
    @NotBlank
    @Column(name = "instituicao", nullable = false)
    private String instituicao;
    
    @Enumerated(EnumType.STRING) //usado para ENUM
    @Column(name = "tipo", nullable = false)
    private TipoFormacao tipo;
    
    @NotBlank
    @Column(name = "nome_do_curso", nullable = false)
    private String nomeDocurso;
    
    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;
    
    @Column(name = "data_final", nullable = true) //nullable pode ser true indicando que a formação está em andamento
    private LocalDate dataFinal;
    
    @ManyToOne(fetch = FetchType.LAZY) //Muitas formações pertencem a um candidato
    @JoinColumn(name = "perfil_candidato_id", nullable = false)
    private PerfilCandidato perfilCandidato;
}
