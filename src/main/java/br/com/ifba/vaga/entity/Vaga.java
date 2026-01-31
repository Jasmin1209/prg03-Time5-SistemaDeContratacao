/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.vaga.entity;



import br.com.ifba.candidaturas.entity.Candidatura;
import br.com.ifba.endereco.Endereco;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.enums.PeriodoContratacao;
import br.com.ifba.vaga.enums.ModeloContratacao;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

//Lombok
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author Taila
 */
@Entity
@Table(name = "Vagas")
@NoArgsConstructor //gera construtor vazio
@Getter
@Setter
@AllArgsConstructor //gera construtor com tudo
@EqualsAndHashCode(callSuper = false)
public class Vaga extends PersistenceEntity implements Serializable{
    
    
    //Título da vaga, obrigaatório e com maximo de 120 caracteres
    @Column(name = "titulo", nullable = false, length = 120)
    private String titulo;
    
    ///Quantidade de vaagas disponíveis
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
   
    // Modelo de contratação (Presencial, Híbrido, Remoto)
    @Enumerated(EnumType.STRING)
    @Column(name = "modelo_contratacao",nullable = false)
    private ModeloContratacao modelo;
    
    // Tipo de contratação (CLT, Estágio, Freelancer)
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contratacao",nullable = false)
    private TipoContratacao tipo;
    
    // Período da contratação
    @Enumerated(EnumType.STRING)
    @Column(name = "periodo_contratacao",nullable = false)
    private PeriodoContratacao periodo;

    // Faixa salarial (opcional)
    @Column(name = "faixa_salarial")
    private Long faixaSalarial;

    // Lista de requisitos
    @ElementCollection
    @Column(name = "requisito")
    private Set<String> requisitos;

    // Lista de benefícios
    @ElementCollection
    @Column(name = "beneficio")
    private Set<String> beneficios;

    // Localização da vaga
    @ManyToOne(optional = false)
    @JoinColumn(name = "endereco_id")
    private Endereco localizacao;
    
    // Descrição da vaga
    @Column(name = "descricao", length = 1000)
    private String descricao;
    
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Candidatura> candidaturas;


    // Status da vaga (true = ativa | false = encerrada)
    @Column(name = "status",nullable = false)
    private Boolean status;
}
