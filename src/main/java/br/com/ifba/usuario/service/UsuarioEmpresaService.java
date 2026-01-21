/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

/**
 * Camada de serviço responsável pelas regras de negócio
 * do usuário do tipo empresa, centralizando validações
 * e operações relacionadas ao cadastro e login.
 * @author luiza
 */


import br.com.ifba.usuario.entity.UsuarioEmpresa;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UsuarioEmpresaService {

    private final Validator validator;

    public UsuarioEmpresaService() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void cadastrar(UsuarioEmpresa empresa) {
        validar(empresa);
        // futuramente: repository.save(empresa);
    }

    public boolean login(String email, String senha) {
        // simulação
        return email.equals("empresa@email.com")
            && senha.equals("12345678a");
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

