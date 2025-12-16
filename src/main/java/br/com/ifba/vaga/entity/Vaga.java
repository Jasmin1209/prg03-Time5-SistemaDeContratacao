/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.vaga.entity;


import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.enums.PeriodoContratacao;
import br.com.ifba.vaga.enums.ModeloContratacao;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 *
 * @author Taila
 */
@Entity
@Table(name = "Vagas")
@NoArgsConstructor //gera construtor vazio
@Data //gera getters, setters
@AllArgsConstructor //gera construtor com tudo
@EqualsAndHashCode(callSuper = false)
public class Vaga extends PersistenceEntity implements Serializable{
    
    
    //Título da vaga, obrigaatório e com maximo de 120 caracteres
    @Column(nullable = false, length = 120)
    private String titulo;
    
    ///Quantidade de vaagas disponíveis
    @Column(nullable = false)
    private int quantidade;
    
    // Modelo de contratação (Presencial, Híbrido, Remoto)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModeloContratacao modelo;
    
    // Tipo de contratação (CLT, Estágio, Freelancer)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContratacao tipo;
    
    // Período da contratação
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodoContratacao periodo;

    // Faixa salarial (opcional)
    private Long faixaSalarial;

    // Lista de requisitos
    @ElementCollection
    private Set<String> requisitos;

    // Lista de benefícios
    @ElementCollection
    private Set<String> beneficios;

    // Localização da vaga
    @Column(nullable = false)
    private String localizacao;
    
    // Descrição da vaga
    @Column(length = 1000)
    private String descricao;

    // Status da vaga (true = ativa | false = encerrada)
    @Column(nullable = false)
    private Boolean status;
}
