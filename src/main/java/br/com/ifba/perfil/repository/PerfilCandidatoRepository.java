/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.repository;

import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USER
 */

@Repository
public interface PerfilCandidatoRepository extends JpaRepository<PerfilCandidato, Long>{
        Optional<PerfilCandidato> findByUsuarioPerfilNome(String nome);
        Set<Experiencia> findAllExperiencia(Long id);
        void deletedByIdExperiencia(Long idExperiencia);
        Set<Formacao> findAllFormacao(Long id);
        void deletedByIdFormacao(Long idFormacao);
        Set<Competencia> findAllCompetencia(Long id);
        void deleteByIdCompetencia (Long idCompetencia);
        Set<Idioma> findAllIdioma (Long id);
        void deleteByIdIdioma (Long idIdioma);
}
