/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.perfil.candidato.service;

import br.com.ifba.perfil.entity.Competencia;
import br.com.ifba.perfil.entity.Experiencia;
import br.com.ifba.perfil.entity.Formacao;
import br.com.ifba.perfil.entity.Idioma;
import br.com.ifba.perfil.entity.PerfilCandidato;
import br.com.ifba.perfil.repository.CompetenciaRepository;
import br.com.ifba.perfil.repository.ExperienciaRepository;
import br.com.ifba.perfil.repository.FormacaoRepository;
import br.com.ifba.perfil.repository.IdiomaRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.ifba.perfil.repository.PerfilCandidatoRepository;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.service.UsuarioCandidatoService;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USER
 */

@Service
@RequiredArgsConstructor
public class PerfilCandidatoService implements PerfilCandidatoIService {


    private final PerfilCandidatoRepository perfilCandidatoRepository;

    private final UsuarioCandidatoService usuarioCandidatoService;
    
    private final CompetenciaRepository competenciaRepository;
    
    private final ExperienciaRepository experienciaRepository;
    
    private final FormacaoRepository formacaoRepository;
    
    private final IdiomaRepository idiomaRepository;

    //BUSCAR PERFIL
    @Override
    @Transactional(readOnly = true)
     public PerfilCandidato buscarPerfilCompleto(Long usuarioId) {

        PerfilCandidato perfil =
                perfilCandidatoRepository.buscarPerfilCompleto(usuarioId);

        if (perfil == null) {
            throw new NoSuchElementException("Perfil não encontrado para o usuário ID: " + usuarioId);
        }

        return perfil;
    }
     
     
     
     
    //CRUD PERFIL
    @Override
    @Transactional
    public PerfilCandidato save(PerfilCandidato perfil) {
        if (perfil.getUsuarioPerfil() == null) {
            throw new RuntimeException("Perfil sem usuário vinculado");
        }
        
        return perfilCandidatoRepository.save(perfil);
    }
    
    @Override
    @Transactional
    public PerfilCandidato criarPerfil(Long usuarioId, PerfilCandidato perfil) {

         UsuarioCandidato usuario = usuarioCandidatoService.findById(usuarioId);
         
        if(usuario == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if (perfilCandidatoRepository
            .findByUsuarioPerfilId(usuario.getId()) != null) {
            throw new IllegalStateException("Usuário já possui perfil");
        }
    
        perfil.setUsuarioPerfil(usuario);

        return perfilCandidatoRepository.save(perfil);
    }

    /**
     * Atualiza um perfil de candidato.
     * 
     * Valida se o perfil existe antes de persistir a atualização.
     * @param perfilCandidato   
     * @return    
     */
    @Override
    @Transactional
    public PerfilCandidato update(PerfilCandidato perfilCandidato) {
        if (perfilCandidato.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilCandidatoRepository.existsById(perfilCandidato.getId())) {
            throw new NoSuchElementException("Perfil de candidato não encontrado.");
        }

        return perfilCandidatoRepository.save(perfilCandidato);
    }

    /**
     * Remove um perfil de candidato.
     * @param perfilCandidato
     */
    @Override
    @Transactional
    public void delete(PerfilCandidato perfilCandidato) {
        if (perfilCandidato.getId() == null) {
            throw new IllegalArgumentException("O ID do perfil não pode ser nulo.");
        }

        if (!perfilCandidatoRepository.existsById(perfilCandidato.getId())) {
            throw new NoSuchElementException("Perfil de candidato não encontrado.");
        }

        perfilCandidatoRepository.delete(perfilCandidato);
    }


    //BUSCAS
    /**
     * Busca um perfil pelo nome.
     * @param nome
     * @return 
     */
    @Override
    @Transactional
    public PerfilCandidato findByUsuarioPerfilNome(String nome) {
        return perfilCandidatoRepository.findByUsuarioPerfilNome(nome)
                .orElseThrow(() -> new NoSuchElementException(
                        "Perfil de candidato não encontrado com nome: " + nome));
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public PerfilCandidato findById(Long id){
        return perfilCandidatoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado"));
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public PerfilCandidato findByUsuarioPerfilId(Long usuarioId){
        PerfilCandidato perfil = perfilCandidatoRepository.buscarPerfilCompleto(usuarioId);
    
    if (perfil == null) {
        throw new NoSuchElementException("Perfil não encontrado para o ID: " + usuarioId);
    }
    return perfil;
    }
    
    //===================
    //EXPERIENCIAS
    //===================
    @Override
    @Transactional
    public void addExperiencia (Long id, Experiencia experiencia){
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

    // garante que a coleção está inicializada
    perfil.getExperiencias().size();

    if (experiencia.getId() == null) {
        // 🆕 NOVA EXPERIÊNCIA
        experiencia.setPerfilCandidato(perfil);
        perfil.getExperiencias().add(experiencia);
    }

        perfilCandidatoRepository.save(perfil);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Experiencia> findAllExperiencia(Long id){
        return perfilCandidatoRepository.findAllExperiencia(id);
    }
    
    @Override
@Transactional
public Experiencia updateExperiencia(Long perfilId, Experiencia experienciaAtualizada) {
    PerfilCandidato perfil = perfilCandidatoRepository.findById(perfilId)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    perfil.getExperiencias().size(); // 👈 MUITO IMPORTANTE

    Experiencia experienciaExistente = perfil.getExperiencias().stream()
        .filter(e -> e.getId().equals(experienciaAtualizada.getId()))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Experiência não encontrada"));

    experienciaExistente.setCargo(experienciaAtualizada.getCargo());
    experienciaExistente.setEmpresa(experienciaAtualizada.getEmpresa());
    experienciaExistente.setDataInicial(experienciaAtualizada.getDataInicial());
    experienciaExistente.setDataFinal(experienciaAtualizada.getDataFinal());

    return experienciaExistente;
}


    
    @Override 
    @Transactional
    public void deletedByIdExperiencia(Long idExperiencia){
        Experiencia experiencia = experienciaRepository.findById(idExperiencia)
        .orElseThrow(() -> new NoSuchElementException("Experiência não encontrada"));
    
    PerfilCandidato perfil = experiencia.getPerfilCandidato();
    if (perfil != null) {
        perfil.getExperiencias().remove(experiencia); // Remove da lista em memória [cite: 66]
        perfilCandidatoRepository.save(perfil); // Sincroniza o Perfil
    }
    perfilCandidatoRepository.deletedByIdExperiencia(idExperiencia);}
    
    //=============
    //FORMAÇÃO
    //=============
    @Override
    @Transactional
    public Formacao addFormacao (Long id, Formacao formacao){
         if (formacao.getInstituicao() == null || formacao.getInstituicao().trim().isEmpty()) {
        throw new IllegalArgumentException("Instituição é obrigatória.");
    }

    if (formacao.getTipo() == null) {
        throw new IllegalArgumentException("Tipo de formação é obrigatório.");
    }

    if (formacao.getNomeDocurso() == null || formacao.getNomeDocurso().trim().isEmpty()) {
        throw new IllegalArgumentException("Nome do curso é obrigatório.");
    }

    if (formacao.getDataInicial() == null) {
        throw new IllegalArgumentException("Data inicial é obrigatória.");
    }

    PerfilCandidato perfil = perfilCandidatoRepository.findById(id)
        .orElseThrow(() ->
            new NoSuchElementException("Perfil não encontrado")
        );

    
    formacao.setPerfilCandidato(perfil);
    perfil.getFormacaoAcademica().add(formacao);

    perfilCandidatoRepository.save(perfil);
    
    return formacao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Formacao> findAllFormacao(Long id){
        return perfilCandidatoRepository.findAllFormacao(id);
    }
    
    @Override
@Transactional
public Formacao updateFormacao(Long perfilId, Formacao formacaoAtualizada) {
    // 1. Busca o perfil garantindo que ele venha com as formações (Fetch)
    PerfilCandidato perfil = perfilCandidatoRepository.findById(perfilId)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    // 2. Localiza a formação existente dentro da coleção do perfil
    Formacao formacaoExistente = perfil.getFormacaoAcademica().stream()
        .filter(f -> f.getId().equals(formacaoAtualizada.getId()))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Formação não encontrada no perfil"));

    // 3. Atualiza APENAS os campos necessários da instância que já está anexada ao Hibernate
    formacaoExistente.setNomeDocurso(formacaoAtualizada.getNomeDocurso());
    formacaoExistente.setInstituicao(formacaoAtualizada.getInstituicao());
    formacaoExistente.setDataInicial(formacaoAtualizada.getDataInicial());
    formacaoExistente.setDataFinal(formacaoAtualizada.getDataFinal());
    formacaoExistente.setTipo(formacaoAtualizada.getTipo());

    // 4. Salva o perfil (o Cascade cuidará da formação)
    perfilCandidatoRepository.save(perfil);

    return formacaoExistente;
}
    
    
    @Override
    @Transactional
    public void deletedByIdFormacao(Long idFormacao){
        Formacao formacao = formacaoRepository.findById(idFormacao)
        .orElseThrow(() -> new NoSuchElementException("Formação não encontrada"));
    
    PerfilCandidato perfil = formacao.getPerfilCandidato();
    if (perfil != null) {
        perfil.getFormacaoAcademica().remove(formacao); // Remove da lista em memória [cite: 75]
        perfilCandidatoRepository.save(perfil);
    }
    perfilCandidatoRepository.deletedByIdFormacao(idFormacao);
    }

    //=============
    //COMPETÊNCIA
    //============
    @Override 
    @Transactional
    public Competencia addCompetencia (Long id, Competencia competencia){
        if(competencia.getTitulo() == null){
            throw new IllegalArgumentException("Título da competência é obrigatório");
        }
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id).
                orElseThrow(() -> 
                        new NoSuchElementException("Perfil não encontrado")
                );
        
        competencia.setPerfilCandidato(perfil);
        perfil.getCompetencias().add(competencia);
        
        perfilCandidatoRepository.save(perfil);
        
        return competencia;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Competencia> findAllCompetencia(Long id){
        return perfilCandidatoRepository.findAllCompetencia(id);
    }
    
    @Override
    @Transactional
public Competencia updateCompetencia(Long perfilId, Competencia competenciaAtualizada) {
    // Busca o perfil com a coleção de competências
    PerfilCandidato perfil = perfilCandidatoRepository.findById(perfilId)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    // Localiza a competência que já existe dentro da lista do perfil
    Competencia competenciaExistente = perfil.getCompetencias().stream()
        .filter(c -> c.getId().equals(competenciaAtualizada.getId()))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Competência não encontrada no perfil"));

    // Atualiza apenas os atributos necessários na instância "viva" do Hibernate
    competenciaExistente.setTitulo(competenciaAtualizada.getTitulo());

    perfilCandidatoRepository.save(perfil);
    
    return competenciaExistente;
}
    @Override
    @Transactional
    public void deleteByIdCompetencia (Long idCompetencia){
        Competencia competencia = competenciaRepository.findById(idCompetencia)
        .orElseThrow(() -> new NoSuchElementException("Competência não encontrada"));
    
    PerfilCandidato perfil = competencia.getPerfilCandidato();
    if (perfil != null) {
        perfil.getCompetencias().remove(competencia); // Remove da lista em memória [cite: 82]
        perfilCandidatoRepository.save(perfil);
    }
    perfilCandidatoRepository.deleteByIdCompetencia(idCompetencia);
    }
    
    
    //=============
    //IDIOMA
    //=============
    @Override
    @Transactional
    public Idioma addIdioma (Long id, Idioma idioma){
        if(idioma.getIdioma() == null){
            throw new IllegalArgumentException("O idioma é obrigatório");
        }
        
        if(idioma.getNivel() == null){
            throw new IllegalArgumentException("O nível é obrigatório");
        }
        
        PerfilCandidato perfil = perfilCandidatoRepository.findById(id).
                orElseThrow(() -> 
                        new NoSuchElementException("Perfil não encontrado")
                );
        
        idioma.setPerfilCandidato(perfil);
        perfil.getIdiomas().add(idioma);
        
        perfilCandidatoRepository.save(perfil);
        
        return idioma;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Idioma> findAllIdioma (Long id){
        return perfilCandidatoRepository.findAllIdioma(id);
    }
    
    @Override 
    @Transactional
public Idioma updateIdioma(Long perfilId, Idioma idiomaAtualizada) {
    // 1. Busca o perfil e garante que a coleção seja carregada [cite: 88]
    PerfilCandidato perfil = perfilCandidatoRepository.findById(perfilId)
        .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));

    // Força a inicialização da coleção Lazy para evitar LazyInitializationException [cite: 88]
    perfil.getIdiomas().size(); 

    // 2. Localiza o idioma que já pertence ao perfil
    Idioma idiomaExistente = perfil.getIdiomas().stream()
        .filter(e -> e.getId().equals(idiomaAtualizada.getId()))
        .findFirst()
        // Corrigido: a mensagem de erro deve ser "Idioma não encontrado" [cite: 89]
        .orElseThrow(() -> new NoSuchElementException("Idioma não encontrado"));

    // 3. Atualiza os campos na instância gerenciada pelo Hibernate [cite: 89]
    idiomaExistente.setIdioma(idiomaAtualizada.getIdioma());
    idiomaExistente.setNivel(idiomaAtualizada.getNivel());
    
    // 4. ESSENCIAL: Salva o perfil para persistir a alteração no banco de dados [cite: 62]
    // Isso garante que o Dirty Checking do Hibernate dispare o SQL de UPDATE
    perfilCandidatoRepository.save(perfil);
    
    return idiomaExistente;
}
    
    @Override
    @Transactional
    public void deleteByIdIdioma (Long idIdioma){
        Idioma idioma = idiomaRepository.findById(idIdioma)
        .orElseThrow(() -> new NoSuchElementException("Idioma não encontrado"));
    
    PerfilCandidato perfil = idioma.getPerfilCandidato();
    if (perfil != null) {
        perfil.getIdiomas().remove(idioma); // Remove da lista em memória [cite: 90]
        perfilCandidatoRepository.save(perfil);
    }
    perfilCandidatoRepository.deleteByIdIdioma(idIdioma);
    }
    
    
}



