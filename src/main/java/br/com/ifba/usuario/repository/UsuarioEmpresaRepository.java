/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repository;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso
 * aos dados do usuário empresa.
 * @author luiza
 */
@Repository
public interface UsuarioEmpresaRepository
        extends JpaRepository<UsuarioEmpresa, Long> {

    Optional<UsuarioEmpresa> findByCnpj(String cnpj);

    Optional<UsuarioEmpresa> findByEmail(String email);
}
