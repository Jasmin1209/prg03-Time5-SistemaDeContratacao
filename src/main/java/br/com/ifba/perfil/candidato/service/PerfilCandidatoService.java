/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.entity.Perfil;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.repository.PerfilRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilCandidatoService implements PerfilCandidatoIService {

    private final PerfilRepository perfilCandidatoRepository;

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
    public void atualizarSobreMim(Long idPerfil, String novoSobreMim) {

    Perfil perfil = perfilCandidatoRepository.findById(idPerfil)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    perfil.setSobre(novoSobreMim);

    // save não é obrigatório com @Transactional,
    // mas pode deixar se quiser
    perfilCandidatoRepository.save(perfil);
    }
}



