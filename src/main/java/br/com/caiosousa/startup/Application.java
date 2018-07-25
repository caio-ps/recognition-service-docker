package br.com.caiosousa.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.caiosousa.recognition.Environment;

@SpringBootApplication
@ComponentScan("br.com.caiosousa.recognition")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Environment variables:");
            
            for (Environment env : Environment.values()) {
            	System.out.println(env.name() + " : " + env.value());
            }

        };
    }
	
}
