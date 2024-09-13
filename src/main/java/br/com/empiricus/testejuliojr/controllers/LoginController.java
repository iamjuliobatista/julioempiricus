package br.com.empiricus.testejuliojr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.services.UsuariosServices;
import br.com.empiricus.testejuliojr.services.UuidServices;

@RestController
@RequestMapping(value = "usuarios/entrar")
public class LoginController {
	
	@Autowired
	private UsuariosServices services;
	
	@Autowired
	private UuidServices token;
	
	@GetMapping
	public ResponseEntity<String> findByLogin(@RequestParam(required = true) String cpf, String senha) {
		Usuarios usuarios = services.getByCpfAndSenha(cpf, senha);
		if (usuarios == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario ou senha invalidos");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(token.generatorAcess(usuarios));
		}
	}
}
