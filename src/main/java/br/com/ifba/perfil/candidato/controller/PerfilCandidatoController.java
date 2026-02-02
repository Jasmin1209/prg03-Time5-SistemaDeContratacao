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
import br.com.ifba.usuario.entity.Usuario;
import java.util.Set;
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
    public PerfilCandidato buscarPerfilCompleto(Long usuarioId) {
        return perfilcandidatoservice.buscarPerfilCompleto(usuarioId);
    }
    
    @Override
    public PerfilCandidato criarPerfil(Long usuarioId, PerfilCandidato perfil) {
        return perfilcandidatoservice.criarPerfil(usuarioId, perfil);
    }

    @Override
    public PerfilCandidato save(PerfilCandidato perfil){
        return perfilcandidatoservice.save(perfil);
    }
    
    @Override
    public PerfilCandidato update(PerfilCandidato perfilCandidato) {
        return perfilcandidatoservice.update(perfilCandidato);
    }

    @Override
    public void delete(PerfilCandidato perfilCandidato) {
        perfilcandidatoservice.delete(perfilCandidato);
    }

    @Override
    public PerfilCandidato findByUsuarioPerfilId(Long usuarioId){
        return perfilcandidatoservice.findByUsuarioPerfilId(usuarioId);
    }
    @Override
    public PerfilCandidato findByUsuarioPerfilNome(String nome) {
        return perfilcandidatoservice.findByUsuarioPerfilNome(nome);
    }
    
    @Override
    public void addExperiencia (Long id, Experiencia experiencia) {
         perfilcandidatoservice.addExperiencia(id, experiencia);
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
    
    @Override
    public PerfilCandidato findById(Long id){
        return perfilcandidatoservice.findById(id);
    }
    
    @Override
    public Set<Experiencia> findAllExperiencia(Long id){
        return perfilcandidatoservice.findAllExperiencia(id);
    }
    
    @Override
    public void deletedByIdExperiencia(Long idExperiencia){
        perfilcandidatoservice.deletedByIdExperiencia(idExperiencia);
    }
    
    @Override
    public Set<Formacao> findAllFormacao(Long id){
        return perfilcandidatoservice.findAllFormacao(id);
    }
    
    @Override
    public void deletedByIdFormacao(Long idFormacao){
        perfilcandidatoservice.deletedByIdFormacao(idFormacao);
    }
    
    @Override
    public Set<Competencia> findAllCompetencia(Long id){
        return perfilcandidatoservice.findAllCompetencia(id);
    }
    
    @Override
    public void deleteByIdCompetencia (Long idCompetencia){
        perfilcandidatoservice.deleteByIdCompetencia(idCompetencia);
    }
    
    @Override
    public Set<Idioma> findAllIdioma (Long id){
        return perfilcandidatoservice.findAllIdioma(id);
    }
    
    @Override
    public void deleteByIdIdioma (Long idIdioma){
        perfilcandidatoservice.deleteByIdIdioma(idIdioma);
    }
}
