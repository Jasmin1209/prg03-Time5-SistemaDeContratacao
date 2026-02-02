/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.empresa.service;

import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.usuario.entity.UsuarioEmpresa;

/**
 *
 * @author USER
 */
public interface PerfilEmpresaIService {
       
       UsuarioEmpresa buscarUsuarioEmpresa(Long idUsuario);
       
       PerfilEmpresa buscarPerfilCompleto(Long usuarioId);
       
       PerfilEmpresa update(PerfilEmpresa perfilEmpresa);

       void delete(PerfilEmpresa perfilEmpresa);
       
       PerfilEmpresa findByUsuarioEmpresaNome(String nome);
       
       void updateSobreMim(Long id, String novoTexto);
       
       PerfilEmpresa addSetor (Long id, String novoSetor);
      
       PerfilEmpresa findById(Long id);
       
       PerfilEmpresa findByUsuarioId(Long usuarioId);
    
        void save(PerfilEmpresa empresa);
}
