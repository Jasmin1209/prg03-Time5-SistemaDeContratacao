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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author USER
 */

@Repository
@Transactional
public interface PerfilCandidatoRepository extends JpaRepository<PerfilCandidato, Long>{
        Optional<PerfilCandidato> findByUsuarioPerfilNome(String nome);

    @Query("SELECT p.experiencias FROM PerfilCandidato p WHERE p.id = :id")
    Set<Experiencia> findAllExperiencia(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Experiencia e WHERE e.id = :id")
    void deletedByIdExperiencia(@Param("id") Long id);

    @Query("SELECT p.formacaoAcademica FROM PerfilCandidato p WHERE p.id = :id")
    Set<Formacao> findAllFormacao(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Formacao f WHERE f.id = :id")
    void deletedByIdFormacao(@Param("id") Long id);

    @Query("SELECT p.competencias FROM PerfilCandidato p WHERE p.id = :id")
    Set<Competencia> findAllCompetencia(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Competencia c WHERE c.id = :id")
    void deleteByIdCompetencia(@Param("id") Long id);

    @Query("SELECT p.idiomas FROM PerfilCandidato p WHERE p.id = :id")
    Set<Idioma> findAllIdioma(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Idioma i WHERE i.id = :id")
    void deleteByIdIdioma(@Param("id") Long id);
}
