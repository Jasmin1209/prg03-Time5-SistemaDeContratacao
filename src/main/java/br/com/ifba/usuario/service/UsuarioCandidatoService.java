/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

/**
 * Camada de serviço responsável pelas regras de negócio
 * do usuário candidato, incluindo validações e
 * processamento de cadastro e login.
 * @author luiza
 */


import br.com.ifba.usuario.entity.UsuarioCandidato;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioCandidatoService {

    private final Validator validator;

    public UsuarioCandidatoService() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void cadastrar(UsuarioCandidato candidato) {
        validar(candidato);
        // futuramente: repository.save(candidato);
    }

    public boolean login(String email, String senha) {
        // simulação
        return email.equals("teste@email.com")
            && senha.equals("12345678a");
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
