/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.ifba.perfil.repository.PerfilCandidatoRepository;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.service.UsuarioCandidatoService;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilCandidatoService implements PerfilCandidatoIService {


    private final PerfilCandidatoRepository perfilCandidatoRepository;

    private final UsuarioCandidatoService usuarioCandidatoService;

    //BUSCAR PERFIL
    @Override
    @Transactional(readOnly = true)
     public PerfilCandidato buscarPerfilCompleto(Long usuarioId) {

        PerfilCandidato perfil =
                perfilCandidatoRepository.buscarPerfilCompleto(usuarioId);

        if (perfil == null) {
            throw new NoSuchElementException("Perfil não encontrado para o usuário ID: " + usuarioId);
        }

        return perfil;
    }
     
     
     
     
    //CRUD PERFIL
    @Override
    @Transactional
    public PerfilCandidato save(PerfilCandidato perfil) {
        if (perfil.getUsuarioPerfil() == null) {
            throw new RuntimeException("Perfil sem usuário vinculado");
        }
        
        return perfilCandidatoRepository.save(perfil);
    }
    
    @Override
    @Transactional
    public PerfilCandidato criarPerfil(Long usuarioId, PerfilCandidato perfil) {

         UsuarioCandidato usuario = usuarioCandidatoService.findById(usuarioId);
         
        if(usuario == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if (perfilCandidatoRepository
            .findByUsuarioPerfilId(usuario.getId()) != null) {
            throw new IllegalStateException("Usuário já possui perfil");
        }
    
        perfil.setUsuarioPerfil(usuario);

        return perfilCandidatoRepository.save(perfil);
    }

    /**
     * Atualiza um perfil de candidato.
     * 
     * Valida se o perfil existe antes de persistir a atualização.
     * @param perfilCandidato   
     * @return    
     */
    @Override
    @Transactional
    public PerfilCandidato update(PerfilCandidato perfilCandidato) {
        if (perfilCandidato.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilCandidatoRepository.existsById(perfilCandidato.getId())) {
            throw new NoSuchElementException("Perfil de candidato não encontrado.");
        }

        return perfilCandidatoRepository.save(perfilCandidato);
    }

    /**
     * Remove um perfil de candidato.
     * @param perfilCandidato
     */
    @Override
    @Transactional
    public void delete(PerfilCandidato perfilCandidato) {
        if (perfilCandidato.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilCandidatoRepository.existsById(perfilCandidato.getId())) {
            throw new NoSuchElementException("Perfil de candidato não encontrado.");
        }

        perfilCandidatoRepository.delete(perfilCandidato);
    }


    //BUSCAS
    /**
     * Busca um perfil pelo nome.
     * @param nome
     * @return 
     */
    @Override
    @Transactional
    public PerfilCandidato findByUsuarioPerfilNome(String nome) {
        return perfilCandidatoRepository.findByUsuarioPerfilNome(nome)
                .orElseThrow(() -> new NoSuchElementException(
                        "Perfil de candidato não encontrado com nome: " + nome));
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public PerfilCandidato findById(Long id){
        return perfilCandidatoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado"));
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public PerfilCandidato findByUsuarioPerfilId(Long usuarioId){
        PerfilCandidato perfil = perfilCandidatoRepository.buscarPerfilCompleto(usuarioId);
    
    if (perfil == null) {
        throw new NoSuchElementException("Perfil não encontrado para o ID: " + usuarioId);
    }
    return perfil;
    }
    
    //===================
    //EXPERIENCIAS
    //===================
    @Override
    @Transactional
    public void addExperiencia (Long id, Experiencia experiencia){
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        // força inicialização dentro da sessão
        perfil.getExperiencias().size();

        experiencia.setPerfilCandidato(perfil); // lado dono da relação
        perfil.getExperiencias().add(experiencia);

        perfilCandidatoRepository.save(perfil);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Experiencia> findAllExperiencia(Long id){
        return perfilCandidatoRepository.findAllExperiencia(id);
    }
    
    @Override 
    @Transactional
    public void deletedByIdExperiencia(Long idExperiencia){
        perfilCandidatoRepository.deletedByIdExperiencia(idExperiencia);
    }
    
    //=============
    //FORMAÇÃO
    //=============
    @Override
    @Transactional
    public Formacao addFormacao (Long id, Formacao formacao){
         if (formacao.getInstituicao() == null || formacao.getInstituicao().trim().isEmpty()) {
        throw new IllegalArgumentException("Instituição é obrigatória.");
    }

    if (formacao.getTipo() == null) {
        throw new IllegalArgumentException("Tipo de formação é obrigatório.");
    }

    if (formacao.getNomeDocurso() == null || formacao.getNomeDocurso().trim().isEmpty()) {
        throw new IllegalArgumentException("Nome do curso é obrigatório.");
    }

    if (formacao.getDataInicial() == null) {
        throw new IllegalArgumentException("Data inicial é obrigatória.");
    }

    PerfilCandidato perfil = perfilCandidatoRepository.findById(id)
        .orElseThrow(() ->
            new NoSuchElementException("Perfil não encontrado")
        );

    
    formacao.setPerfilCandidato(perfil);
    perfil.getFormacaoAcademica().add(formacao);

    perfilCandidatoRepository.save(perfil);
    
    return formacao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Formacao> findAllFormacao(Long id){
        return perfilCandidatoRepository.findAllFormacao(id);
    }
    
    @Override
    @Transactional
    public void deletedByIdFormacao(Long idFormacao){
        perfilCandidatoRepository.deletedByIdFormacao(idFormacao);
    }

    //=============
    //COMPETÊNCIA
    //============
    @Override 
    @Transactional
    public Competencia addCompetencia (Long id, Competencia competencia){
        if(competencia.getTitulo() == null){
            throw new IllegalArgumentException("Título da competência é obrigatório");
        }
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id).
                orElseThrow(() -> 
                        new NoSuchElementException("Perfil não encontrado")
                );
        
        competencia.setPerfilCandidato(perfil);
        perfil.getCompetencias().add(competencia);
        
        perfilCandidatoRepository.save(perfil);
        
        return competencia;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Competencia> findAllCompetencia(Long id){
        return perfilCandidatoRepository.findAllCompetencia(id);
    }
    
    @Override
    @Transactional
    public void deleteByIdCompetencia (Long idCompetencia){
        perfilCandidatoRepository.deleteByIdCompetencia(idCompetencia);
    }
    
    
    //=============
    //IDIOMA
    //=============
    @Override
    @Transactional
    public Idioma addIdioma (Long id, Idioma idioma){
        if(idioma.getIdioma() == null){
            throw new IllegalArgumentException("O idioma é obrigatório");
        }
        
        if(idioma.getNivel() == null){
            throw new IllegalArgumentException("O nível é obrigatório");
        }
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id).
                orElseThrow(() -> 
                        new NoSuchElementException("Perfil não encontrado")
                );
        
        idioma.setPerfilCandidato(perfil);
        perfil.getIdiomas().add(idioma);
        
        perfilCandidatoRepository.save(perfil);
        
        return idioma;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Idioma> findAllIdioma (Long id){
        return perfilCandidatoRepository.findAllIdioma(id);
    }
    
    @Override
    @Transactional
    public void deleteByIdIdioma (Long idIdioma){
        perfilCandidatoRepository.deleteByIdIdioma(idIdioma);
    }
    
    
}



