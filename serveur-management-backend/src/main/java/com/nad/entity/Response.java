package com.nad.entity;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;



@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
	
	protected LocalDateTime timeStamp ;
	protected int statusCode;
	protected HttpStatus status ;
	protected String message ;
	protected String reason ;
	protected String devMessage ;
	protected Map<?,?> data ;
	

}
