/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.vaga.controller;

import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.service.VagaIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Taila
 */
@Controller
public class VagaController implements VagaIController{
    
    @Autowired
    private VagaIService vagaIService;
   
    @Override
    public Vaga save(Vaga vaga) throws RuntimeException {
        return vagaIService.save(vaga);
    }

    
    @Override
    public List<Vaga> findAll() throws RuntimeException {
        return vagaIService.findAll();
    }

    @Override
    public Vaga update(Vaga vaga) throws RuntimeException {
        return vagaIService.update(vaga);
    }

    @Override
    public void delete(Vaga vaga) throws RuntimeException {
        vagaIService.delete(vaga);
    }

    @Override
    public Vaga findById(Long id) throws RuntimeException {
        return vagaIService.findById(id);
    }

    @Override
    public List<Vaga> findByTipo(TipoContratacao tipo) throws RuntimeException {
        return vagaIService.findByTipo(tipo);
    }

    @Override
    public List<Vaga> findByModelo(ModeloContratacao modelo) throws RuntimeException {
        return vagaIService.findByModelo(modelo);
    }

    @Override
    public List<Vaga> findAtivas() throws RuntimeException {
        return vagaIService.findByStatusTrue();
    }

    @Override
    public List<Vaga> findByTitulo(String titulo) throws RuntimeException {
        return vagaIService.findByTituloContainingIgnoreCase(titulo);
    }

}
