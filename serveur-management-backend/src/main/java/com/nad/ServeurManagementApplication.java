package com.nad;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nad.entity.Serveur;
import com.nad.enumeration.Status;
import com.nad.repository.ServeurRepository;
import com.nad.service.implementation.ServeurServiceImp;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Transactional
@SpringBootApplication
public class ServeurManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeurManagementApplication.class, args);
	}
	
	@Transactional
	@Bean
	CommandLineRunner start(ServeurRepository serveurRepository,ServeurServiceImp serveurServiceImp) {
		
		return args->{

		serveurRepository.save(Serveur.builder()
					                     .adresseIp("192.168.8.145")
					                     .nom("NADORI")
					                     .status(Status.SERVER_DOWN)
					                     .imageUrl("http://localhost:8080/servers/image/server2.jpg")
							             .memoire("64 Go")
						                  .type("HP ProBook")
					                     .build()


					     );
			serveurRepository.save(Serveur.builder()
					.adresseIp("149.123.0.150")
					.nom("ENSAH")
					.status(Status.SERVER_DOWN)
					.imageUrl("http://localhost:8080/servers/image/server1.jpg")
					.memoire("32 Go")
					.type("Dell Latitude")
					.build()


			);

	};

}
	
}
