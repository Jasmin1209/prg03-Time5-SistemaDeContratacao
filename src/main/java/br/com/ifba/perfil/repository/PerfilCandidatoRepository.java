/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.repository;

import br.com.ifba.perfil.entity.PerfilCandidato;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USER
 */

@Repository
public interface PerfilCandidatoRepository extends JpaRepository<PerfilCandidato, Long>{
        Optional<PerfilCandidato> findByUsuarioPerfil_Nome(String nome);
        
}
