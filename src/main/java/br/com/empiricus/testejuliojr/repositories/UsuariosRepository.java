package br.com.empiricus.testejuliojr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empiricus.testejuliojr.entities.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

	public List<Usuarios> findByCpfAndSenha(String cpf, String senha);
	
	public List<Usuarios> findByAdmin1(boolean bol);
	
}
