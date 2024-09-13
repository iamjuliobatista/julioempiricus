package br.com.empiricus.testejuliojr.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.entities.Uuid;
import br.com.empiricus.testejuliojr.repositories.UuidRepository;

@Service
public class UuidServices {

	@Autowired
	private UuidRepository repository;

	public String generatorAcess(Usuarios usuarios) {
		UUID keyAcess = UUID.randomUUID();
		String token = keyAcess.toString();
		Uuid uuid = new Uuid();
		uuid.setToken(token);
		uuid.setUsuarios(usuarios);
		repository.save(uuid);
		return token;
	}

	public String verifyAcess(String string) {
		List<Uuid> result = repository.findByToken(string);
		boolean eh_adm = result.get(0).getUsuarios().isAdmin1();
		if (result.isEmpty()) {
			return "Token invalido";
		} else {
			if (eh_adm == true) {
				return "Aceito adm";
			}
		} return "Somente adms tem acesso";
	}
	
}
