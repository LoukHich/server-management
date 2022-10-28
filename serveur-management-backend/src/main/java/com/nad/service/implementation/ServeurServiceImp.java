package com.nad.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;


import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nad.entity.Serveur;

import com.nad.repository.ServeurRepository;
import com.nad.service.ServeurService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.data.domain.PageRequest.of;
import static com.nad.enumeration.Status.SERVER_DOWN;
import static com.nad.enumeration.Status.SERVER_UP;



@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ServeurServiceImp implements ServeurService{
	
   private final ServeurRepository serviceRepository ;	

 
	@Override
	public Serveur createServeur(Serveur serveur) {
		
		 log.info("Nouveau Serveur Enregistré {}",serveur.getNom());
         serveur.setImageUrl(setImageUrl());
		 return this.serviceRepository.save(serveur);
	}

	@Override
	public Collection<Serveur> list(int size) {
		log.info("Liste des {} Serveur ",size);
		return serviceRepository.findAll( of(0,size)).toList();
	}

	@Override
	public Serveur update(Serveur serveur) {

		log.info("Mis a jour Serveur {}",serveur.getNom());
		return this.serviceRepository.save(serveur);
	}

	@Override
	public Boolean delete(Long id) {
		
		
		 log.info("Serveur {} Supprimé",id);
	     this.serviceRepository.deleteById(id);
	     return Boolean.TRUE;
	     
	}

	@Override
	public Serveur get(Long id) {
		 log.info(" Serveur {} ",id);
		return this.serviceRepository.findById(id).get();
	}

	@Override
	public Serveur ping(String ipAdresse) throws IOException {
		log.info("ping serveur {}",ipAdresse);
		Serveur serveur = serviceRepository.findByAdresseIp(ipAdresse);
		InetAddress adress = InetAddress.getByName(ipAdresse);
		serveur.setStatus(adress.isReachable(10000)?SERVER_UP:SERVER_DOWN);
		serviceRepository.save(serveur);
		return serveur;
	}
	/*private String setServerImageUrl() {
        String[] imageNames = { "server1.png", "server2.png", "server3.png", "server4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }*/
	public String setImageUrl() {
		int imgNumber =new Random().nextInt(4)+1;
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/servers/image/server"+imgNumber+".jpg").toUriString();
	}

}
