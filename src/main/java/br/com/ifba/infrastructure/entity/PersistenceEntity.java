/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */

@MappedSuperclass
@Getter
@Setter
public abstract class PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY é mais compatível para uso em Postgres
    private Long id;
}

