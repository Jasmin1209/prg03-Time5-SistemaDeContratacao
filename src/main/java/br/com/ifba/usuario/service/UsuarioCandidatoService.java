/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;
import br.com.ifba.usuario.entity.UsuarioCandidato;
import br.com.ifba.usuario.repository.UsuarioCandidatoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Camada de serviço responsável pelas
 * regras de negócio do usuário candidato.
 * Realiza validações, cadastro e autenticação
 * utilizando persistência via JPA.
 * @author luiza
 */
@Service
public class UsuarioCandidatoService
        implements UsuarioCandidatoServiceInterface {

    private final UsuarioCandidatoRepository repository;
    private final Validator validator;

    public UsuarioCandidatoService(UsuarioCandidatoRepository repository) {
        this.repository = repository;

        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void cadastrar(UsuarioCandidato candidato) {
        validar(candidato);

        if (repository.findByEmail(candidato.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        repository.save(candidato);
    }

    @Override
    public boolean login(String email, String senha) {
        UsuarioCandidato usuario = repository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("E-mail não cadastrado."));

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha inválida.");
        }

        return true;
    }

    private void validar(UsuarioCandidato candidato) {
        Set<ConstraintViolation<UsuarioCandidato>> erros =
                validator.validate(candidato);

        if (!erros.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            erros.forEach(e ->
                    msg.append(e.getMessage()).append("\n")
            );
            throw new IllegalArgumentException(msg.toString());
        }
    }
}
