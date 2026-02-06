/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.endereco;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "endereco")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Endereco extends PersistenceEntity implements Serializable{
    
    
    @Column(name = "pais", nullable = true)
    private String pais;
    
    
    @Column(name = "estado", nullable = true)
    private String estado;
    
    
    @Column(name = "cidade", nullable = true)
    private String cidade;
    
    @Column(name = "bairro", nullable = true)
    private String bairro;
    
    @Column(name = "número", nullable = true)
    private Integer numero;
    
}
