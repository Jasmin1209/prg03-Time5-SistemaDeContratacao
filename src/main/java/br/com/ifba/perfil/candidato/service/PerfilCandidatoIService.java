/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.entity.PerfilCandidato;

/**
 *
 * @author USER
 */
public interface PerfilCandidatoIService {

    /**
     * Atualiza os dados de um perfil de candidato existente.
     *
     * @param perfilCandidato objeto contendo os dados atualizados
     * @return perfil atualizado após persistência
     */
    PerfilCandidato update(PerfilCandidato perfilCandidato);

    /**
     * Remove um perfil de candidato do sistema.
     *
     * @param perfilCandidato perfil que será removido
     */
    void delete(PerfilCandidato perfilCandidato);

    /**
     * Busca um perfil de candidato pelo nome.
     *
     * @param nome nome do candidato
     * @return perfil encontrado ou {@code null} caso não exista
     */
    PerfilCandidato findByUsuarioPerfil_Nome(String nome);
    
    void atualizarSobreMim(Long idPerfil, String novoSobreMim);
}
