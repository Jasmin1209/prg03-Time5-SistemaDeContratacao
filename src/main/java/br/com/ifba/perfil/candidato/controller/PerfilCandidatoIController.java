/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.perfil.candidato.controller;

import br.com.ifba.perfil.Enum.TipoFormacao;
import br.com.ifba.perfil.entity.PerfilCandidato;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public interface PerfilCandidatoIController {
    
    public PerfilCandidato update (PerfilCandidato perfilCandidato);
    
    public void delete (PerfilCandidato perfilCandidato);
    
    public PerfilCandidato findByUsuarioPerfil_Nome (String nome);
    
    void atualizarSobreMim(Long idPerfil, String novoSobreMim);
    
    void adicionarExperiencia(Long idPerfil, String titulo, String empresa, LocalDate dataInicial, LocalDate dataFinal);

    void adicionarFormacao(Long idPerfil, String instituicao, TipoFormacao tipo, String nomeCurso, LocalDate dataInicial, LocalDate dataFinal);
}
