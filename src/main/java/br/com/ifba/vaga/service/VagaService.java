package br.com.ifba.vaga.service;

import br.com.ifba.vaga.entity.Vaga;
import br.com.ifba.vaga.enums.ModeloContratacao;
import br.com.ifba.vaga.enums.TipoContratacao;
import br.com.ifba.vaga.repository.VagaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VagaService implements VagaIService {

    // Uso de @RequiredArgsConstructor para injeção de dependência via construtor
    private final VagaRepository vagaRepository;

    // Adicionado Logger para registrar operações do serviço
    private static final Logger log = LoggerFactory.getLogger(VagaService.class);

    @Override
    public List<Vaga> findAll() throws RuntimeException {
        log.info("Listando vagas.");
        return vagaRepository.findAll();
    }

    @Override
    public Vaga save(Vaga vaga) throws RuntimeException {
        if(vaga == null) {
            throw new RuntimeException ("Vaga informada não existe");
        }
        
        if(vaga.getId() != null) {
            throw new RuntimeException("Para salvar uma nova vaga, o ID deve ser nulo.");
        }
        
        log.info("Salvando o objeto Vaga");
        return vagaRepository.save(vaga);
    }

    @Override
    public Vaga update(Vaga vaga) throws RuntimeException {
        if(vaga.getId() == null) {
            throw new RuntimeException("Não é possível atualizar; vaga sem ID.");
        }

        log.info("Atualizando o objeto vaga.");
        return vagaRepository.save(vaga);
    
    }
    

    @Override
    public void delete(Vaga vaga) throws RuntimeException {
        if(vaga.getId() == null) {
            throw new RuntimeException("Vaga não existe no banco de dados.");
        }

    
        log.info("Deletando o objeto vaga.");
        vagaRepository.delete(vaga);
    }
    

    @Override
    public Vaga findById(Long id) throws RuntimeException {
        log.info("Buscando or ID o objeto curso.");
        return vagaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vaga> findByTituloContainingIgnoreCase(String titulo) {
        log.info("Buscando a vaga pelo titulo.");
        return vagaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Vaga> findByTipo(TipoContratacao tipo) {
        log.info("Buscando a vaga pelo o tipo.");
        return vagaRepository.findByTipo(tipo);
    }

    @Override
    public List<Vaga> findByModelo(ModeloContratacao modelo) {
        log.info("Buscando a vaga pelo modelo.");
        return vagaRepository.findByModelo(modelo);
    }

    @Override
    public List<Vaga> findByStatusTrue() {
        log.info("buscando a vaga pelo status.");
        return vagaRepository.findByStatusTrue();
    }
    
}
