package br.com.empiricus.testejuliojr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empiricus.testejuliojr.entities.Emails;
import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.repositories.UsuariosRepository;

@Service
public class SendServices {
	
	@Autowired
	private MailServices mailServices;
	
	@Autowired
	private UsuariosRepository repository;
	
	
	public void sendEmail(Emails emails) {
		
		List<Usuarios> results = repository.findByAdmin1(true);
		
		
		if (results.isEmpty()) {
			//Nenhum email para ser enviado;
		} else {
			String email1 = results.get(0).getEmails().toString();
			mailServices.sendEmail(email1, 
					"O email " + emails.getEmail() + " foi criado/alterado para o usuario de CPF " + emails.getUsuarios().getCpf(),
					"Voce esta recebendo essa confirmacao");
		}
		
			
	}
}
