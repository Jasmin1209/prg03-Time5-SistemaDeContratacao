/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;
import br.com.ifba.usuario.entity.UsuarioEmpresa;
import br.com.ifba.usuario.repository.UsuarioEmpresaRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Camada de serviço responsável pelas
 * regras de negócio do usuário empresa.
 * Centraliza validações, cadastro e
 * autenticação utilizando JPA.
 * @author luiza
 */

@Service
public class UsuarioEmpresaService
        implements UsuarioEmpresaServiceInterface {

    private final UsuarioEmpresaRepository repository;
    private final Validator validator;

    public UsuarioEmpresaService(UsuarioEmpresaRepository repository) {
        this.repository = repository;

        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void cadastrar(UsuarioEmpresa empresa) {
        validar(empresa);

        if (repository.findByEmail(empresa.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        repository.save(empresa);
    }

    @Override
    public UsuarioEmpresa login(String email, String senha) {
        UsuarioEmpresa empresa = repository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("E-mail não cadastrado."));

        if (!empresa.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha inválida.");
        }

        return empresa;
    }

    private void validar(UsuarioEmpresa empresa) {
        Set<ConstraintViolation<UsuarioEmpresa>> erros =
                validator.validate(empresa);

        if (!erros.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            erros.forEach(e ->
                    msg.append(e.getMessage()).append("\n")
            );
            throw new IllegalArgumentException(msg.toString());
        }
    }
}
