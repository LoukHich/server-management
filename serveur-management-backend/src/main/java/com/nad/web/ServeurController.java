package com.nad.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

import com.nad.service.ServeurService;
import net.bytebuddy.asm.TypeReferenceAdjustment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nad.entity.Response;
import com.nad.entity.Serveur;
import com.nad.enumeration.Status;

import com.nad.service.implementation.ServeurServiceImp;

import javax.validation.Valid;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
@RestController
@CrossOrigin()
public class ServeurController {
	
	
	@Autowired
	private ServeurService serveurServiceImp ;
	



	@GetMapping("/servers")
	public ResponseEntity<Response> getServer() throws InterruptedException {
		Thread.sleep(1000);
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(now())
						.message("Servers retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.data(Map.of("servers",serveurServiceImp.list(30)))
						.build()
		);
	}

	
	
	@GetMapping("servers/ping/{ipAdress}")
	public ResponseEntity<Response> pingServer( @PathVariable(value = "ipAdress") String IpAdress) throws IOException {
		Serveur server =serveurServiceImp.ping(IpAdress);
		return	ResponseEntity.ok(
				 Response.builder()
	             .timeStamp(now())
	             .data(Map.of("server", server))
	             .message(server.getStatus()==Status.SERVER_UP?"Ping seccess":"Ping failed")
	             .status(HttpStatus.OK)
	             .statusCode(HttpStatus.OK.value())
	             .build()
					
					
					);
		
		
		
	}

	
	@PostMapping("/servers")
	public ResponseEntity<Response> addServer(@RequestBody @Valid Serveur server) throws InterruptedException {
		Thread.sleep(2000);
		return	ResponseEntity.ok(
				 Response.builder()
	             .timeStamp(now())
	             .data(Map.of("server", serveurServiceImp.createServeur(server)))
	             .message("Serveur Added")
	             .status(HttpStatus.CREATED)
	             .statusCode(HttpStatus.CREATED.value())
	             .build()
					
					
					);
		
		
	}
	  @GetMapping("/servers/{id}")
	    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
	        return ResponseEntity.ok().header("nadori").body(  Response.builder()
					.timeStamp(now())
					.data(Map.of("server", serveurServiceImp.get(id)))
					.message("Server retrieved")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build());
	    }
	
	  @DeleteMapping(value = "/servers/{id}")
	    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
	        return ResponseEntity.ok(
	                Response.builder()
	                        .timeStamp(now())
	                        .data(Map.of("deleted", serveurServiceImp.delete(id)))
	                        .message("Server deleted")
	                        .status(HttpStatus.OK)
	                        .statusCode(HttpStatus.OK.value())
	                        .build()
	        );
	    }

	    @GetMapping(path = "servers/image/{fileName}", produces = IMAGE_JPEG_VALUE)
	    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
			System.out.println(System.getProperty("user.home") + "/Downloads/images/" + fileName);
	        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
	    }

	//	@GetMapping("/servers")
//	public Collection<Serveur> getServers (){
//
//		return serveurServiceImp.list(30);
//
//	}
}
