/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.endereco;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */

@Entity
@Table(name = "endereco")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Endereco extends PersistenceEntity implements Serializable{
    
    @NotBlank
    @Column(name = "pais", nullable = false)
    private String pais;
    
    @NotBlank
    @Column(name = "estado", nullable = false)
    private String estado;
    
    @NotBlank
    @Column(name = "cidade", nullable = false)
    private String cidade;
    
    @Column(name = "bairro", nullable = true)
    private String bairro;
    
    @Column(name = "número", nullable = true)
    private Integer numero;
    
}
