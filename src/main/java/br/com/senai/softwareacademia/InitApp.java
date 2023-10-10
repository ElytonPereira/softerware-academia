package br.com.senai.softwareacademia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}
	
	@Bean
	public Hibernate5JakartaModule jsonHibernate5Module() {
		return new Hibernate5JakartaModule();
	}
	
	@Bean
	public CommandLineRunner commandLineRuner(ApplicationContext ctx) {
		return args -> {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String p = bCryptPasswordEncoder.encode("SomeCoolPassword");
			System.out.println(bCryptPasswordEncoder.matches("SomeCoolPassword", p));
			
			System.out.println("Subiuuu "  + p);
		};
	}

}

