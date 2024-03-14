package com.aristezio.lampp.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aristezio.lampp.domain.model.EncurtadorUrl;

@Repository
public interface EncurtadorUrlRepository extends JpaRepository<EncurtadorUrl, Long>{
	
	Optional<EncurtadorUrl> findByCodigo(String codigo);
	EncurtadorUrl findByCodigoEquals(String codigo);
	
}
