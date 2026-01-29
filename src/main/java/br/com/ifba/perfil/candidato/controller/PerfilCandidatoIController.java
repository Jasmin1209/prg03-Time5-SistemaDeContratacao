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

/**
 *
 * @author USER
 */
public interface PerfilCandidatoIController {
    
    public PerfilCandidato update (PerfilCandidato perfilCandidato);
    
    public void delete (PerfilCandidato perfilCandidato);
    
    public PerfilCandidato findByUsuarioPerfilNome (String nome);
    
    void updateSobreMim(Long idPerfil, String novoSobreMim);
    
    Experiencia addExperiencia (Long id, Experiencia experiencia);

    Formacao addFormacao (Long id, Formacao formacao);
    
    Competencia addCompetencia (Long id, Competencia competencia);
    
    Idioma addIdioma (Long id, Idioma idioma);
}
