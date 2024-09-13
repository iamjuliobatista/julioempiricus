package br.com.empiricus.testejuliojr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empiricus.testejuliojr.entities.Emails;
import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.repositories.EmailsRepository;
import br.com.empiricus.testejuliojr.services.SendServices;
import br.com.empiricus.testejuliojr.services.UuidServices;

@RestController
@RequestMapping(value = "/emails")
public class EmailsController {

	@Autowired
	private EmailsRepository repository;

	@Autowired
	private SendServices sendServices;

	@Autowired
	private UuidServices uuidServices;

	@GetMapping
	public ResponseEntity<List<Emails>> findAll(@RequestHeader String header) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			List<Emails> result = repository.findAll();
			return ResponseEntity.ok(result);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@GetMapping(value = "/findbyuserid")
	public ResponseEntity<List<Emails>> filterByUsuario_Id(@RequestHeader String header,
			@RequestParam(name = "user_id", required = true) Usuarios usuarios) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			List<Emails> result = repository.findByUsuarios(usuarios);
			return ResponseEntity.ok(result);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@PostMapping
	public ResponseEntity<Emails> insert(@RequestHeader String header, @RequestBody Emails emails) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			Emails result = repository.save(emails);
			sendServices.sendEmail(emails);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@DeleteMapping(value = "/{id}")
	public void delete(@RequestHeader String header, @PathVariable Long id) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			repository.deleteById(id);
		}

	}

}
