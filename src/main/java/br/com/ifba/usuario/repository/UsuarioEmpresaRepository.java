/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.UsuarioEmpresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author USER
 */
public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa, Long>{
 
    Optional<UsuarioEmpresa> findByCnpj(String cnpj);

    Optional<UsuarioEmpresa> findByEmail(String email);
}
