/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.vaga.service;

import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import java.util.List;

/**
 *
 * @author Taila
 */
public interface VagaIService {
    
    List<Vaga> findAll() throws RuntimeException;

    Vaga save(Vaga vaga) throws RuntimeException;

    Vaga update(Vaga vaga) throws RuntimeException;

    void delete(Vaga vaga) throws RuntimeException;

    Vaga findById(Long id) throws RuntimeException;
    
    // Buscar por título (contendo o texto, ignorando maiúsculas/minúsculas)
    List<Vaga> findByTituloContainingIgnoreCase(String titulo);

    // Buscar por tipo de contratação (CLT, Estágio, Freelancer)
    List<Vaga> findByTipo(TipoContratacao tipo);

    // Buscar por modelo de contratação (Presencial, Híbrido, Remoto)
    List<Vaga> findByModelo(ModeloContratacao modelo);

    // Buscar apenas vagas ativas
    List<Vaga> findByStatusTrue();

}
