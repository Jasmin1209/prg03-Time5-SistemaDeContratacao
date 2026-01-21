/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.Enum.TipoFormacao;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.time.LocalDate;

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
    
    void updateAboutMe(Long idPerfil, String novoSobreMim);
    
    void adicionarExperiencia(Long idPerfi, String titulo, String empresa, LocalDate dataInicial, LocalDate dataFinal);
    
    void adicionarFormacao(Long idPerfil, String instituicao, TipoFormacao tipo, String nomeCurso, LocalDate dataInicial, LocalDate dataFinal);


}
