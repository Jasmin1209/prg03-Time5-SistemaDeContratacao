/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.vaga.repository;

import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taila
 */
@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>{
    
     // Buscar por título (contendo o texto, ignorando maiúsculas/minúsculas)
    List<Vaga> findByTituloContainingIgnoreCase(String titulo);

    // Buscar por tipo de contratação (CLT, Estágio, Freelancer)
    List<Vaga> findByTipo(TipoContratacao tipo);

    // Buscar por modelo de contratação (Presencial, Híbrido, Remoto)
    List<Vaga> findByModelo(ModeloContratacao modelo);
    
    // Buscar apenas vagas ativas
    List<Vaga> findByStatusTrue();
    
     
}
