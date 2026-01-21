/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repository;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso
 * aos dados do usuário candidato.
 * @author luiza
 */
@Repository
public interface UsuarioCandidatoRepository
        extends JpaRepository<UsuarioCandidato, Long> {

    Optional<UsuarioCandidato> findByEmail(String email);
    
    Optional<UsuarioCandidato> findByCPF(String email);
}
