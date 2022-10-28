package com.nad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nad.entity.Serveur;

public interface ServeurRepository extends JpaRepository<Serveur, Long>{
	
	Serveur  findByAdresseIp(String adressIp);
	Serveur  findByNom(String nom);

}
