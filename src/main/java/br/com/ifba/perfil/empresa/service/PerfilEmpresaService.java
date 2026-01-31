/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.empresa.service;

import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.perfil.repository.PerfilEmpresaRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilEmpresaService implements PerfilEmpresaIService{
    private final PerfilEmpresaRepository perfilempresarepository;
    
    @Override
    public PerfilEmpresa update(PerfilEmpresa perfilEmpresa) {
        if (perfilEmpresa.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilempresarepository.existsById(perfilEmpresa.getId())) {
            throw new NoSuchElementException("Perfil de empresa não encontrado.");
        }

        return perfilempresarepository.save(perfilEmpresa);
    }
    
    @Override
    public void delete(PerfilEmpresa perfilEmpresa) {
        if (perfilEmpresa.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilempresarepository.existsById(perfilEmpresa.getId())) {
            throw new NoSuchElementException("Perfil de empresa não encontrado.");
        }

        perfilempresarepository.delete(perfilEmpresa);
    }
    
    @Override
    public PerfilEmpresa findByUsuarioEmpresaNome(String nome) {
        return perfilempresarepository.findByUsuarioEmpresaNome(nome).orElseThrow(() ->  new IllegalArgumentException("Perfil não encontrado"));
    }
    
    @Override
    public void updateSobreMim(Long idPerfil, String novoSobreMim) {
        
    PerfilEmpresa perfil = perfilempresarepository.findById(idPerfil)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

        if(novoSobreMim == null || novoSobreMim.trim().isEmpty()){
            throw new IllegalArgumentException("O campo não pode ser vazio");
        }
        
    perfil.setSobre(novoSobreMim);
    perfilempresarepository.save(perfil);
    }

    @Override
    public PerfilEmpresa addSetor (Long id, String novoSetor){
        PerfilEmpresa perfil = perfilempresarepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));
        
        if(novoSetor == null || novoSetor.isEmpty()){
            throw new IllegalArgumentException("O campo não pode ser vazio");
        }
        
        perfil.setSetor(novoSetor);
        return perfilempresarepository.save(perfil);
    }
    
    @Override 
    public  PerfilEmpresa findById(Long id){
        return perfilempresarepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("Perfil não encontrado"));
    }
}
