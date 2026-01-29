/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.enums.TipoFormacao;
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
    PerfilCandidato findByUsuarioPerfilNome(String nome);
    
    void updateSobreMim(Long idPerfil, String novoSobreMim);
    
    Experiencia addExperiencia (Long id, Experiencia experiencia);
    
    Formacao addFormacao (Long id, Formacao formacao);

    Competencia addCompetencia (Long id, Competencia competencia);
    
    Idioma addIdioma (Long id, Idioma idioma);
}
