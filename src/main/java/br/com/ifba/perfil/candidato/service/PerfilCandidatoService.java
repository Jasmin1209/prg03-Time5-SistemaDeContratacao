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
import java.util.Set;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilCandidatoService implements PerfilCandidatoIService {


    private final PerfilCandidatoRepository perfilCandidatoRepository;

    /**
     * Atualiza um perfil de candidato.
     * 
     * Valida se o perfil existe antes de persistir a atualização.
     * @param perfilCandidato   
     * @return    
     */
    @Override
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
    public void delete(PerfilCandidato perfilCandidato) {
        if (perfilCandidato.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilCandidatoRepository.existsById(perfilCandidato.getId())) {
            throw new NoSuchElementException("Perfil de candidato não encontrado.");
        }

        perfilCandidatoRepository.delete(perfilCandidato);
    }


    /**
     * Busca um perfil pelo nome.
     * @param nome
     * @return 
     */
    @Override
    public PerfilCandidato findByUsuarioPerfilNome(String nome) {
        return perfilCandidatoRepository.findByUsuarioPerfilNome(nome)
                .orElseThrow(() -> new NoSuchElementException(
                        "Perfil de candidato não encontrado com nome: " + nome));
    }
    
    @Override
    public void updateSobreMim(Long idPerfil, String novoSobreMim) {
        
    PerfilCandidato perfil = perfilCandidatoRepository.findById(idPerfil)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    if(novoSobreMim == null || novoSobreMim.trim().isEmpty()){
        throw new IllegalArgumentException("O campo não pode ser vazio");
    }
    
   
    perfil.setSobre(novoSobreMim);
    perfilCandidatoRepository.save(perfil);

    }
    
    @Override
    public Experiencia addExperiencia (Long id, Experiencia experiencia){
        
    if (experiencia.getCargo() == null || experiencia.getCargo().trim().isEmpty()) {
        throw new IllegalArgumentException("Título é obrigatório.");
    }

    if (experiencia.getEmpresa() == null || experiencia.getEmpresa().trim().isEmpty()) {
        throw new IllegalArgumentException("Empresa é obrigatória.");
    }

    if (experiencia.getDataInicial() == null) {
        throw new IllegalArgumentException("Data inicial é obrigatória.");
    }

    PerfilCandidato perfil = perfilCandidatoRepository.findById(id)
        .orElseThrow(() ->
            new NoSuchElementException("Perfil de candidato não encontrado")
        );

    experiencia.setPerfilCandidato(perfil);
    
    perfil.getExperiencias().add(experiencia);

    perfilCandidatoRepository.save(perfil);
    
    return experiencia;
    }
    
    @Override
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
    public PerfilCandidato findById(Long id){
        return perfilCandidatoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado"));
    }
    
    @Override
    public Set<Experiencia> findAllExperiencia(Long id){
        return perfilCandidatoRepository.findAllExperiencia(id);
    }
    
    @Override 
    public void deletedByIdExperiencia(Long idExperiencia){
        perfilCandidatoRepository.deletedByIdExperiencia(idExperiencia);
    }
    
    @Override
    public Set<Formacao> findAllFormacao(Long id){
        return perfilCandidatoRepository.findAllFormacao(id);
    }
    
    @Override
    public void deletedByIdFormacao(Long idFormacao){
        perfilCandidatoRepository.deletedByIdFormacao(idFormacao);
    }
    
    @Override
    public Set<Competencia> findAllCompetencia(Long id){
        return perfilCandidatoRepository.findAllCompetencia(id);
    }
    
    @Override
    public void deleteByIdCompetencia (Long idCompetencia){
        perfilCandidatoRepository.deleteByIdCompetencia(idCompetencia);
    }
    
    @Override
    public Set<Idioma> findAllIdioma (Long id){
        return perfilCandidatoRepository.findAllIdioma(id);
    }
    
    @Override
    public void deleteByIdIdioma (Long idIdioma){
        perfilCandidatoRepository.deleteByIdIdioma(idIdioma);
    }
}



