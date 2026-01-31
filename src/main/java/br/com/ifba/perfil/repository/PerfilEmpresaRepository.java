/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.repository;

import br.com.ifba.perfil.entity.PerfilEmpresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USER
 */

@Repository
@Transactional
public interface PerfilEmpresaRepository extends JpaRepository<PerfilEmpresa, Long>{
    Optional<PerfilEmpresa> findByUsuarioEmpresaNome(String nome); 
}
