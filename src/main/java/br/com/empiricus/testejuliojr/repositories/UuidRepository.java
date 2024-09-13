package br.com.empiricus.testejuliojr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empiricus.testejuliojr.entities.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {

	public List<Uuid> findByToken(String token);
}
