/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.controller;

import br.com.ifba.perfil.entity.PerfilCandidato;

/**
 *
 * @author USER
 */
public interface PerfilCandidatoIController {
    
    public PerfilCandidato update (PerfilCandidato perfilCandidato);
    
    public void delete (PerfilCandidato perfilCandidato);
    
    public PerfilCandidato findByUsuarioPerfil_Nome (String nome);
    
    void atualizarSobreMim(Long idPerfil, String novoSobreMim);
}
