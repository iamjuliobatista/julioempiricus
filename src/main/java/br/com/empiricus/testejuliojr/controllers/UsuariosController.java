package br.com.empiricus.testejuliojr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.services.UsuariosServices;
import br.com.empiricus.testejuliojr.services.UuidServices;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosServices services;

	@Autowired
	private UuidServices uuidServices;

	@PostMapping
	public ResponseEntity<Object> register(@RequestBody Usuarios usuarios) {

		try {
			Usuarios result = services.criar(usuarios);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuarios>> findAll(@RequestHeader String header) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			List<Usuarios> result = services.findAll();
			return ResponseEntity.ok(result);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuarios> findById(@RequestHeader String header, @PathVariable Long id) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			Usuarios result = services.findById(id).get();
			return ResponseEntity.ok(result);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestHeader String header, @RequestBody Usuarios usuarios) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			try {
				Usuarios result = services.update(usuarios);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@RequestHeader String header, @PathVariable Long id) {
		if (uuidServices.verifyAcess(header) == "Aceito adm") {
			services.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return null;
	}

}
