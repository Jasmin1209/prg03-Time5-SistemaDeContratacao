/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.empresa.controller;

import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.usuario.entity.UsuarioEmpresa;

/**
 *
 * @author USER
 */
public interface PerfilEmpresaIController {
    PerfilEmpresa buscarPerfilCompleto(Long usuarioId);
    
    public PerfilEmpresa update (PerfilEmpresa perfilEmpresa);
    
    public void delete (PerfilEmpresa perfilEmpresa);
    
    public PerfilEmpresa findByUsuarioEmpresaNome (String nome);
    
    void updateSobreMim(Long idPerfil, String novoSobreMim);
    
    PerfilEmpresa addSetor (Long id, String novoSetor);
    
    PerfilEmpresa findById (Long id);
    
    PerfilEmpresa findByUsuarioId(Long usuarioId);
    
    void saveOrUpdate(PerfilEmpresa empresa);
    
    UsuarioEmpresa buscarUsuarioEmpresa(Long idUsuario);
}
