/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.controller;

import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.util.Set;

/**
 *
 * @author USER
 */
public interface PerfilCandidatoIController {
    
    PerfilCandidato update (PerfilCandidato perfilCandidato);
    
    void delete (PerfilCandidato perfilCandidato);
    
    PerfilCandidato findByUsuarioPerfilNome (String nome);
    
    void updateSobreMim(Long idPerfil, String novoSobreMim);
    
    Experiencia addExperiencia (Long id, Experiencia experiencia);

    Formacao addFormacao (Long id, Formacao formacao);
    
    Competencia addCompetencia (Long id, Competencia competencia);
    
    Idioma addIdioma (Long id, Idioma idioma);
    
    PerfilCandidato findById(Long id);
    
    Set<Experiencia> findAllExperiencia(Long id);
    
    void deletedByIdExperiencia(Long idExperiencia);
    
    Set<Formacao> findAllFormacao(Long id);
    
    void deletedByIdFormacao(Long idFormacao);
    
    Set<Competencia> findAllCompetencia(Long id);
     
    void deleteByIdCompetencia (Long idCompetencia);
     
    Set<Idioma> findAllIdioma (Long id);
     
    void deleteByIdIdioma (Long idIdioma);
}
