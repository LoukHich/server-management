package com.nad.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nad.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Serveur {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	@NotEmpty(message = "IP impossible d'etre null ou vide !!!")
	@Column(unique = true)
	private String  adresseIp;
	private String nom;
	private String memoire ;
	private String imageUrl;
	@Enumerated(EnumType.STRING)
	private Status  status ;
	private String type;
	
	
	

}
