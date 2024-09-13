package br.com.empiricus.testejuliojr.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empiricus.testejuliojr.entities.Usuarios;
import br.com.empiricus.testejuliojr.repositories.UsuariosRepository;

@Service
public class UsuariosServices {

	@Autowired
	private UsuariosRepository repository;

	public Usuarios criar(Usuarios usuarios) {

		validarUsuarios(usuarios);
		usuarios.setSenha(encryptString(usuarios.getSenha()));
		usuarios.setData_criacao(LocalDate.now());
		usuarios.setData_atualizacao(LocalDate.now());
		Usuarios result = repository.save(usuarios);
		return result;
	}

	public Usuarios update(Usuarios usuarios) {
		validarUsuarios(usuarios);
		Usuarios usuariosAntigo = repository.findById(usuarios.getId()).get();
		if (usuariosAntigo == null) {
			throw new IllegalStateException("Usuario nao existe");
		}
		usuariosAntigo.setData_atualizacao(LocalDate.now());
		usuariosAntigo.setNome(usuarios.getNome());
		usuariosAntigo.setSenha(encryptString(usuarios.getSenha()));
		usuariosAntigo.setCpf(usuarios.getCpf());
		usuariosAntigo.setAdmin1(usuarios.isAdmin1());
		return usuariosAntigo;
	}
	
	public Usuarios getByCpfAndSenha(String cpf, String senha) {
		List<Usuarios> results = repository.findByCpfAndSenha(cpf, encryptString(senha));
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public String encryptString(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger bigInt = new BigInteger(1, messageDigest);
			return bigInt.toString(16);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void validarUsuarios(Usuarios usuarios) {
		if (usuarios.getNome() == null) {
			throw new IllegalArgumentException("Usuario nao pode ter nome vazio");
		}
		if (usuarios.getCpf() == null) {
			throw new IllegalArgumentException("Usuario nao pode ter CPF vazio");
		}
		if (usuarios.getSenha() == null) {
			throw new IllegalArgumentException("Usuario nao pode ter senha vazia");
		}
		if (usuarios.getData_criacao() == null) {
			throw new IllegalArgumentException("Usuario nao pode ter data de criacao vazia");
		}
		if (usuarios.getData_atualizacao() == null) {
			throw new IllegalArgumentException("Usuario nao pode ter data de atualizacao vazia");
		}
	}
	
	public List<Usuarios> findAll() {
		  List<Usuarios> result = repository.findAll();
		  return result;
	}
	
	public Optional<Usuarios> findById(Long id) {
		Optional<Usuarios> result = repository.findById(id);
		return result;
	}
	
	public String deleteById(Long id) {
		repository.deleteById(id);
		return "Usuario com o id " + id + " deletado";
	}

}
