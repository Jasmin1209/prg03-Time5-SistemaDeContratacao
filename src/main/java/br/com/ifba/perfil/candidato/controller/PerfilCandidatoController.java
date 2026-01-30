/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.controller;

import br.com.ifba.perfil.candidato.service.PerfilCandidatoIService;
import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.entity.PerfilCandidato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author USER
 */

@Controller
@RequiredArgsConstructor
public class PerfilCandidatoController implements PerfilCandidatoIController{
    private final PerfilCandidatoIService perfilcandidatoservice;

    @Override
    public PerfilCandidato update(PerfilCandidato perfilCandidato) {
        return perfilcandidatoservice.update(perfilCandidato);
    }

    @Override
    public void delete(PerfilCandidato perfilCandidato) {
        perfilcandidatoservice.delete(perfilCandidato);
    }

    @Override
    public PerfilCandidato findByUsuarioPerfilNome(String nome) {
        return perfilcandidatoservice.findByUsuarioPerfilNome(nome);
    }
    
    @Override
    public void updateSobreMim(Long idPerfil, String novoSobreMim){
        perfilcandidatoservice.updateSobreMim(idPerfil, novoSobreMim);
    }
    
    @Override
    public Experiencia addExperiencia (Long id, Experiencia experiencia) {
       return perfilcandidatoservice.addExperiencia(id, experiencia);
    }

    @Override
    public Formacao addFormacao (Long id, Formacao formacao){ 
       return perfilcandidatoservice.addFormacao(id, formacao);
    }
    
    @Override
    public Competencia addCompetencia (Long id, Competencia competencia){
       return perfilcandidatoservice.addCompetencia(id, competencia);
    }
    
    @Override
    public Idioma addIdioma (Long id, Idioma idioma){
       return perfilcandidatoservice.addIdioma(id, idioma);
    }
}
