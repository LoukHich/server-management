package com.nad.service;

import java.io.IOException;
import java.util.Collection;

import com.nad.entity.Serveur;

public interface ServeurService {
	
	 Serveur createServeur(Serveur serveur);
	 Serveur ping (String ipAdresse) throws IOException;
	 Collection<Serveur> list (int size);
	 Serveur update(Serveur serveur);
	 Serveur get(Long id);
	 Boolean delete(Long id );

}
