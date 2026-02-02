/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.empresa.controller;

import br.com.ifba.perfil.empresa.service.PerfilEmpresaIService;
import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author USER
 */
@Controller
@RequiredArgsConstructor
public class PerfilEmpresaController implements PerfilEmpresaIController{
    private final PerfilEmpresaIService perfilempresaservice;

    @Override
    public PerfilEmpresa buscarPerfilCompleto(Long usuarioId) {
        return perfilempresaservice.buscarPerfilCompleto(usuarioId);
    }
    @Override
    public PerfilEmpresa update(PerfilEmpresa perfilCandidato) {
        return perfilempresaservice.update(perfilCandidato);
    }

    @Override
    public void delete(PerfilEmpresa perfilCandidato) {
        perfilempresaservice.delete(perfilCandidato);
    }

    @Override
    public PerfilEmpresa findByUsuarioEmpresaNome(String nome) {
        return perfilempresaservice.findByUsuarioEmpresaNome(nome);
    }
    
    @Override
    public void updateSobreMim(Long idPerfil, String novoSobreMim){
        perfilempresaservice.updateSobreMim(idPerfil, novoSobreMim);
    }
    
    @Override 
    public PerfilEmpresa addSetor (Long id, String novoSetor){
        return perfilempresaservice.addSetor(id, novoSetor);
    }

    @Override
    public PerfilEmpresa findById (Long id){
        return perfilempresaservice.findById(id);
    }
    
    @Override
    public PerfilEmpresa findByUsuarioId(Long usuarioId) {
        return perfilempresaservice.findByUsuarioId(usuarioId);
    }

    @Override
    public void saveOrUpdate(PerfilEmpresa empresa) {
        perfilempresaservice.save(empresa);
    }
    
    @Override
    public UsuarioEmpresa buscarUsuarioEmpresa(Long idUsuario){
        return perfilempresaservice.buscarUsuarioEmpresa(idUsuario);
    }
}
