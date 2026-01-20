/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.controller;

import br.com.ifba.perfil.candidato.service.PerfilCandidatoIService;
import br.com.ifba.perfil.entity.PerfilCandidato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author USER
 */

@Controller
@RequiredArgsConstructor
public class PerfilCandidatoController implements PerfilCandidatoIController{
    private final PerfilCandidatoIService perfilcandidatoservice;

    @Override
    public PerfilCandidato update(PerfilCandidato perfilCandidato) {
        return perfilcandidatoservice.update(perfilCandidato);
    }

    @Override
    public void delete(PerfilCandidato perfilCandidato) {
        perfilcandidatoservice.delete(perfilCandidato);
    }

    @Override
    public PerfilCandidato findByUsuarioPerfil_Nome(String nome) {
        return perfilcandidatoservice.findByUsuarioPerfil_Nome(nome);
    }
    
    @Override
    public void atualizarSobreMim(Long idPerfil, String novoSobreMim){
        perfilcandidatoservice.atualizarSobreMim(idPerfil, novoSobreMim);
    }
}
