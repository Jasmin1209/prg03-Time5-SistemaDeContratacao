/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.empresa.service;

import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.entity.PerfilEmpresa;
import br.com.ifba.perfil.repository.PerfilEmpresaRepository;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.repository.UsuarioEmpresaRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilEmpresaService implements PerfilEmpresaIService{
    
   
    private final PerfilEmpresaRepository perfilempresarepository;
    private final UsuarioEmpresaRepository usuarioempresarepository;
    
    @Override
    @Transactional
    public UsuarioEmpresa buscarUsuarioEmpresa(Long idUsuario) {
        return usuarioempresarepository.findById(idUsuario)
            .orElseThrow(() ->
                new IllegalStateException("Usuário empresa não encontrado"));
    }
    
    //BUSCAR PERFIL
    @Override
    @Transactional(readOnly = true)
    public PerfilEmpresa buscarPerfilCompleto(Long usuarioId) {
        return perfilempresarepository.findById(usuarioId).orElse(null);
    }
    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public PerfilEmpresa findByUsuarioEmpresaNome(String nome) {
        return perfilempresarepository.findByUsuarioEmpresaNome(nome).orElseThrow(() ->  new IllegalArgumentException("Perfil não encontrado"));
    }
    
    @Override
    @Transactional
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
    @Transactional
    public PerfilEmpresa addSetor (Long id, String novoSetor){
        PerfilEmpresa perfilEmpresa = perfilempresarepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));
        
        if(novoSetor == null || novoSetor.isEmpty()){
            throw new IllegalArgumentException("O campo não pode ser vazio");
        }
        
        perfilEmpresa.setSetor(novoSetor);
        return perfilempresarepository.save(perfilEmpresa);
    }
    
    @Override 
    @Transactional
    public  PerfilEmpresa findById(Long id){
        return perfilempresarepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("Perfil não encontrado"));
    }
    
    @Override
    @Transactional
    public PerfilEmpresa findByUsuarioId(Long usuarioId){
        return perfilempresarepository.findByUsuarioEmpresaId(usuarioId).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(PerfilEmpresa empresa){
        perfilempresarepository.save(empresa);
    }
    
}
