package br.com.empiricus.testejuliojr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empiricus.testejuliojr.entities.Emails;
import br.com.empiricus.testejuliojr.entities.Usuarios;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Long>{
	
	public List<Emails> findByUsuarios(Usuarios usuarios);

}
