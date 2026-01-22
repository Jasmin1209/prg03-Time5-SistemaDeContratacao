/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.candidaturas.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.vaga.entity.Vaga;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Taila
 */
@Entity
@Table(name = "candidaturas")
@Getter
@Setter
public class Candidatura extends PersistenceEntity implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_candidato_id", nullable = false)
    private PerfilCandidato perfilCandidato;
}
