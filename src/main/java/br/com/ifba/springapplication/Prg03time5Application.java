package br.com.ifba.springapplication;

import br.com.ifba.perfil.candidato.view.TelaApresentacaoCandidato;
import br.com.ifba.usuario.view.LoginCandidatoView;
import java.awt.EventQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ifba")
@EnableJpaRepositories(basePackages = "br.com.ifba")
@EntityScan(basePackages = "br.com.ifba")
public class Prg03time5Application {

    public static void main(String[] args) {
        SpringApplication spring = new SpringApplication(Prg03time5Application.class);
        
        spring.setHeadless(false);
        ConfigurableApplicationContext context = spring.run(args);

        EventQueue.invokeLater(() -> {
            LoginCandidatoView tela =
                    context.getBean(LoginCandidatoView.class);
            tela.setVisible(true);
        });
    }
}

