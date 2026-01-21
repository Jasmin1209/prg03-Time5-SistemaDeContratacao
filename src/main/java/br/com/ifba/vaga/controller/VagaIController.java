/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.vaga.controller;

import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import java.util.List;

/**
 *
 * @author Taila
 */
public interface VagaIController {

    Vaga save(Vaga vaga) throws RuntimeException;

    List<Vaga> findAll() throws RuntimeException;

    Vaga update(Vaga vaga) throws RuntimeException;

    void delete(Vaga vaga) throws RuntimeException;

    Vaga findById(Long id) throws RuntimeException;

    // Search by title (case-insensitive)
    List<Vaga> findByTitulo(String titulo) throws RuntimeException;

    // Search by contract type
    List<Vaga> findByTipo(TipoContratacao tipo) throws RuntimeException;

    // Search by work model
    List<Vaga> findByModelo(ModeloContratacao modelo) throws RuntimeException;

    // List only active vacancies
    List<Vaga> findAtivas() throws RuntimeException;

   }
