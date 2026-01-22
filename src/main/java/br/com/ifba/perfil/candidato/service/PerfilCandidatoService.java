/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.enums.TipoFormacao;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.ifba.perfil.repository.PerfilCandidatoRepository;
import java.time.LocalDate;

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
     */
    @Override
    public PerfilCandidato findByUsuarioPerfil_Nome(String nome) {
        return perfilCandidatoRepository.findByUsuarioPerfil_Nome(nome)
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
    public void addExperiencia (Long id, Experiencia experiencia){
        
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
    }
    
    @Override
    public void addFormacao (Long id, Formacao formacao){
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
    }

}



